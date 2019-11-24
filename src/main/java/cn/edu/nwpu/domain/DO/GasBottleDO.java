package cn.edu.nwpu.domain.DO;

import cn.edu.nwpu.domain.gasdata.GasData;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GasBottleDO
 * @Author: Trium
 * @Date: 2019/2/25 17:27
 * @Version: v1.0
 * @Description: 高压气瓶, 输出项有气瓶内气体密度，出口压强， 出口气体质量流率，
 * 依据：6院，modelica仿真软件帮助文档，质量流率为白晶晶论文《002-2014200285-白晶晶.pdf》
 */
public class GasBottleDO {

    GasData gasData;
    private double vc;//气瓶体积
    private double k;//气体比热比
    private double Rg;//气体常数
    private double CA;//流量系数
    private double p0;//初始压强
    private double temp0;//初始温度

    private List<Double> rho = new ArrayList<>();
    private List<Double> p = new ArrayList<>();
    private List<Double> q = new ArrayList<>();
    private List<Double> temp = new ArrayList<>();

    public GasBottleDO(GasData gasData) {
        this.gasData = gasData;
        k = gasData.getKappa();
        Rg = gasData.getR();
    }

    public GasData getGasData() {
        return gasData;
    }

    public void setGasData(GasData gasData) {
        this.gasData = gasData;
    }

    public double getVc() {
        return vc;
    }

    public void setVc(double vc) {
        this.vc = vc;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getCA() {
        return CA;
    }

    public void setCA(double CA) {
        this.CA = CA;
    }

    public List<Double> getRho() {
        return rho;
    }

    public void setRho(List<Double> rho) {
        this.rho = rho;
    }

    public List<Double> getP() {
        return p;
    }

    public void setP(List<Double> p) {
        this.p = p;
    }

    public List<Double> getQ() {
        return q;
    }

    public void setQ(List<Double> q) {
        this.q = q;
    }

    public List<Double> getTemp() {
        return temp;
    }

    public void setTemp(List<Double> temp) {
        this.temp = temp;
    }

    public double getP0() {
        return p0;
    }

    public void setP0(double p0) {
        this.p0 = p0;
    }

    public double getTemp0() {
        return temp0;
    }

    public void setTemp0(double temp0) {
        this.temp0 = temp0;
    }

    public double getRg() {
        return Rg;
    }

    public void setRg(double rg) {
        Rg = rg;
    }
}
