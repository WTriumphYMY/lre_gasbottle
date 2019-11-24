package cn.edu.nwpu.domain.gasdata;

/**
 * @ClassName gasdata
 * @Author: Trium
 * @Date: 2019/2/25 18:50
 * @Version: v1.0
 * @Description:
 */
public abstract class GasData {
    double rho;//密度
    double mu;//动力粘度
    double nu;//运动粘度m2/s
    double beta;//体积模量
    double Cp;//定压比热容
    double Cv;//定容比热容
    double R;//气体常数
    double kappa;//比热比
    double p_vapour;//饱和蒸汽压，未提供

    public double getRho() {
        return rho;
    }

    public double getMu() {
        return mu;
    }

    public double getNu() {
        return nu;
    }

    public double getBeta() {
        return beta;
    }

    public double getCp() {
        return Cp;
    }

    public double getCv() {
        return Cv;
    }

    public double getR() {
        return R;
    }

    public double getKappa() {
        return kappa;
    }

    public double getP_vapour() {
        return p_vapour;
    }
}
