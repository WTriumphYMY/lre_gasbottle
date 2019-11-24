package cn.edu.nwpu.domain.gasdata;

/**
 * @ClassName HeGasData
 * @Author: Trium
 * @Date: 2019/2/25 17:20
 * @Version: v1.0
 * @Description: 氦气物理化学常数及函数系统
 */
public class HeGasData extends GasData {
    public HeGasData() {
        rho = 0.1785;//密度
        mu = 3e-5;//动力粘度
        nu = 0.00016807;//运动粘度m2/s
        beta = 0;//体积模量
        Cp = 0;//定压比热容
        Cv = 0;//定容比热容
        R = 2077;//气体常数J/kg`K
        kappa = 1.63;//比热比
        p_vapour = 0;//饱和蒸汽压，未提供
    }


}
