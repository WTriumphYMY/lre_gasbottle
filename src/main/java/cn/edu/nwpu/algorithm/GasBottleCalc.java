package cn.edu.nwpu.algorithm;



import cn.edu.nwpu.domain.DO.GasBottleDO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName GasBottleCalc
 * @Author: wkx
 * @Date: 2019/3/29 10:38
 * @Version: v1.0
 * @Description: 气瓶计算类
 */
public class GasBottleCalc {
    private GasBottleDO gasBottleDO;
    private double vc;//气瓶体积
    private double k;//气体比热比
    private double Rg;//气体常数
    private double CA;//流量系数
    private double h;

    private List<Double> rho = new ArrayList<>();
    private List<Double> p = new ArrayList<>();
    private List<Double> q = new ArrayList<>();
    private List<Double> temp = new ArrayList<>();

    private double[] dp = new double[4];
    private double[] drho = new double[4];
    private double[] dtemp = new double[4];

    public GasBottleCalc(GasBottleDO gasBottleDO) {
        this.gasBottleDO = gasBottleDO;
        h = 0.0001;
        vc = gasBottleDO.getVc();
        k = gasBottleDO.getK();
        Rg = gasBottleDO.getRg();
        CA = gasBottleDO.getCA();
        p.add(gasBottleDO.getP0());
        temp.add(gasBottleDO.getTemp0());
        rho.add(gasBottleDO.getP0()/Rg/ gasBottleDO.getTemp0());
    }

    /**
     *
     * @param pe 气瓶出口压强，使用下一组件的上一时刻
     * @return
     */
    public void execute(double pe){
        int index = p.size()-1;
        if (q.isEmpty()){
            q.add(getQ(gasBottleDO.getP0(), rho.get(0), pe));
        }
        //气瓶组件
        dp[0] = getDp(rho.get(index), p.get(index), q.get(index));
        drho[0] = getDrho(q.get(index));
        dtemp[0] = getDtemp(p.get(index), temp.get(index), dp[0]);

        for (int i = 1; i < 4; i++){
            if (i < 3){
                //气瓶
//                dp[i] = getDp(rho.get(index) + 0.5*h*drho[i-1],
//                        p.get(index) + 0.5*h*dp[i-1],
//                        getQ(p.get(index) + 0.5*h*dp[i-1], pe, temp.get(index) + 0.5*h*dtemp[i-1]));
                dp[i] = getDp(rho.get(index) + 0.5*h*drho[i-1],
                        p.get(index) + 0.5*h*dp[i-1],
                        getQ_CA(p.get(index) + 0.5*h*dp[i-1],rho.get(index) + 0.5*h*drho[i-1], pe));
//                drho[i] = getDrho(getQ(p.get(index) + 0.5*h*dp[i-1], pe, temp.get(index) + 0.5*h*dtemp[i-1]));
                drho[i] = getDrho(getQ_CA(p.get(index) + 0.5*h*dp[i-1],rho.get(index) + 0.5*h*drho[i-1], pe));
                dtemp[i] = getDtemp(p.get(index) + 0.5*h*dp[i-1], temp.get(index) + 0.5*h*dtemp[i-1], dp[i-1]);

            }else {
                //气瓶
//                dp[i] = getDp(rho.get(index) + h*drho[i-1],p.get(index) + h*dp[i-1],
//                        getQ(p.get(index) + h*dp[i-1], pe, temp.get(index) + h*dtemp[i-1]));
                dp[i] = getDp(rho.get(index) + h*drho[i-1],p.get(index) + h*dp[i-1],
                        getQ_CA(p.get(index) + h*dp[i-1], rho.get(index) + h*drho[i-1], pe));
//                drho[i] = getDrho(getQ(p.get(index) + h*dp[i-1], pe, temp.get(index) + h*dtemp[i-1]));
                drho[i] = getDrho(getQ_CA(p.get(index) + h*dp[i-1],rho.get(index) + h*drho[i-1], pe));
                dtemp[i] = getDtemp(p.get(index) + h*dp[i-1], temp.get(index) + h*dtemp[i-1], dp[i-1]);

                }
        }
        //气瓶
        p.add(p.get(index) + h*(dp[0] + 2*dp[1] + 2*dp[2] + dp[3])/6);
        rho.add(rho.get(index) + h*(drho[0] + 2*drho[1] + 2*drho[2] + drho[3])/6);
//        temp.add(temp.get(index) + h*(dtemp[0] + 2*dtemp[1] + 2*dtemp[2] + dtemp[3])/6);
        temp.add(getTemp(p.get(index+1)));
//        q.add(getQ(p.get(index+1), pe,temp.get(index+1)));
        q.add(getQ_CA(p.get(index+1), rho.get(index+1), pe));
    }

    /**
     *
     * @param rho 气体密度
     * @param p 气瓶压强（出口压强）
     * @param q 出口流量
     * @return 压强导数
     */
    public double getDp(double rho, double p, double q){
        double dp;
        dp = -k*p/rho/vc*q;
        return dp;
    }

    /**
     *
     * @param q 出口流量
     * @return 流量导数
     */
    public double getDrho(double q){
        double drho;
        drho = -q/vc;
        return drho;
    }

    /**
     *
     * @param p 气瓶压强（出口压强）
     * @param t 气体温度
     * @param pe 出口压强（下一组件压强）
     * @return 出口气体流量
     */
    public double getQ(double p, double pe, double t){
        return CalculateUtils.getQ(p, pe, t, k, Rg);
    }

    /**
     * 使用流量系数求流量
     * @param p
     * @param rho
     * @param pe
     * @return
     */
    public double getQ_CA(double p, double rho, double pe){
        return CA*Math.sqrt(2*k*p*rho/(k-1)*(Math.pow(pe/p, 2/k)-Math.pow(pe/p, (k+1)/k)));
    }

    /**
     *使用微分计算的温度导数
     * @param p 气瓶压强
     * @param temp 气瓶温度
     * @param dp 压强导数
     * @return  温度导数
     */
    public double getDtemp(double p, double temp, double dp){
        double dt;
        dt = (k-1)*temp*dp/k/p;
        return dt;
    }

    /**
     * 不使用微分的温度
     * @param p 气瓶压强
     * @return 温度
     */
    public double getTemp(double p){
        return gasBottleDO.getTemp0()*Math.pow(p/ gasBottleDO.getP0(), (k-1)/k);
    }



    public void outPutToFile(String filePath){
        File resultFile = new File(filePath);
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(resultFile.toPath());
            bufferedWriter.write("index"+"\t"+"t"+"\t"+"p"+"\t"
                    +"q"+"\t"+"rho"+"\t"+"temp");
            bufferedWriter.newLine();
            for (int i = 0; i < p.size(); i++) {
                bufferedWriter.write((i+1)+"\t"+h*i+"\t"+p.get(i)+"\t"
                        +q.get(i)+"\t"+rho.get(i)+"\t"+temp.get(i));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Double> getRho() {
        return rho;
    }

    public List<Double> getP() {
        return p;
    }

    public List<Double> getQ() {
        return q;
    }

    public List<Double> getTemp() {
        return temp;
    }
}

