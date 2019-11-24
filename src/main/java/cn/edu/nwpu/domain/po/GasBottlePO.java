package cn.edu.nwpu.domain.po;


import javax.persistence.*;

/**
 * @ClassName GasBottlePO
 * @Author: wkx
 * @Date: 2019/10/22 10:14
 * @Version: v1.0
 * @Description: 气瓶参数持久类
 */
@Entity
@Table(name = "tb_gasbottle")
public class GasBottlePO {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer pkId;
    @Column(name = "bottle_name")
    private String bottleName;
    @Column(name = "bottle_gas")
    private String bottleGas;//气瓶气体
    @Column(name = "bottle_vol")
    private double bottleVol;//气瓶体积
    @Column(name = "bottle_CA")
    private double bottleCA;//流量系数
    @Column(name = "bottle_pressure")
    private double bottlePressure;//初始压强
    @Column(name = "bottle_temperature")
    private double bottleTemperature;//初始温度

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public String getBottleName() {
        return bottleName;
    }

    public void setBottleName(String bottleName) {
        this.bottleName = bottleName;
    }

    public double getBottleVol() {
        return bottleVol;
    }

    public void setBottleVol(double bottleVol) {
        this.bottleVol = bottleVol;
    }

    public String getBottleGas() {
        return bottleGas;
    }

    public void setBottleGas(String bottleGas) {
        this.bottleGas = bottleGas;
    }

    public double getBottleCA() {
        return bottleCA;
    }

    public void setBottleCA(double bottleCA) {
        this.bottleCA = bottleCA;
    }

    public double getBottlePressure() {
        return bottlePressure;
    }

    public void setBottlePressure(double bottlePressure) {
        this.bottlePressure = bottlePressure;
    }

    public double getBottleTemperature() {
        return bottleTemperature;
    }

    public void setBottleTemperature(double bottleTemperature) {
        this.bottleTemperature = bottleTemperature;
    }
}
