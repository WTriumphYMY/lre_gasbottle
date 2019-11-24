package cn.edu.nwpu.domain.gasdata;

/**
 * @ClassName N2GasData
 * @Author: Trium
 * @Date: 2019/2/27 14:35
 * @Version: v1.0
 * @Description:
 */
public class N2GasData extends GasData {
    public N2GasData() {
        rho = 1.2507;//密度
        mu = 1.2947e-5;//动力粘度
        nu = 1.045e-5;//运动粘度m2/s
        beta = 1.42e5;//体积模量
        Cp = 1038;//定压比热容
        Cv = 741;//定容比热容
        R = 296.93;//气体常数J/kg`K
        kappa = 1.4;//比热比
        p_vapour = 0;//饱和蒸汽压，未提供
    }
}
