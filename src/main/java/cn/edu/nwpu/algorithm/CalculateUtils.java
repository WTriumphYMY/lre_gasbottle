package cn.edu.nwpu.algorithm;

/**
 * @ClassName CalculateUtils
 * @Author: wkx
 * @Date: 2019/7/4 10:32
 * @Version: v1.0
 * @Description:计算通用工具类，如流量计算等
 */
public class CalculateUtils {

    /**
     * 节流孔流量方程
     * @param p 孔前压强
     * @param pe 孔后压强
     * @param t 流体温度
     * @return 返回流量
     */
    public static double getQ(double p, double pe, double t, double k, double Rg){
        double q;
        double A = 0.005*0.005*Math.PI*0.25;//节流孔面积
        double pratio = pe/p;//压比
        double Cd, phi;//流量系数，一个比例
        if (t < 0) return 0.0;
        if (pratio < 1){
            Cd = ((((-1.6827 * pratio + 4.6) * pratio - 3.9) * pratio + 0.8415) * pratio - 0.1) * pratio + 0.8414;
            if (pratio < 0.528){
                phi = Math.pow(2/(k+1), 1/(k-1))*Math.sqrt(2*k/(k+1));
            }else {
                phi = Math.sqrt(2*k/(k-1)*(Math.pow(pratio, 2/k)-Math.pow(pratio, (k+1)/k)));
            }
        }else {
            Cd = 0;
            phi = 0;
        }
        q = Cd*A*p*phi/Math.sqrt(Rg*t);

        q = q<0 ? 0 : q;
        return q;
    }
}
