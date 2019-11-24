package cn.edu.nwpu.service;


import cn.edu.nwpu.domain.dto.GasBottleDTO;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName GasBottleService
 * @Author: wkx
 * @Date: 2019/10/20 16:56
 * @Version: v1.0
 * @Description:
 */
public interface GasBottleService {

    Map<String, List<Double>> gasBottleSim(GasBottleDTO gasBottleDTO);

    void saveGasBottle(GasBottleDTO gasBottleDTO);

    GasBottleDTO findGasBottleByName(String bottleName);

    List<GasBottleDTO> findAllGasBottle();
}
