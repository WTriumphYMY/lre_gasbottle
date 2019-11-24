package cn.edu.nwpu.service.impl;

import cn.edu.nwpu.algorithm.GasBottleCalc;
import cn.edu.nwpu.domain.DO.GasBottleDO;
import cn.edu.nwpu.domain.dto.GasBottleDTO;
import cn.edu.nwpu.domain.gasdata.GasData;
import cn.edu.nwpu.domain.po.GasBottlePO;
import cn.edu.nwpu.factories.MediumFactory;
import cn.edu.nwpu.repository.GasBottleRepository;
import cn.edu.nwpu.service.GasBottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName GasBottleServiceImpl
 * @Author: wkx
 * @Date: 2019/10/20 16:57
 * @Version: v1.0
 * @Description:
 */
@Service
public class GasBottleServiceImpl implements GasBottleService {

    @Autowired
    private GasBottleRepository gasBottleRepository;

    @Override
    public Map<String, List<Double>> gasBottleSim(GasBottleDTO gasBottleDTO) {
        double calTime = Double.parseDouble(gasBottleDTO.getGlobalParasTime());
        double timeStep = Double.parseDouble(gasBottleDTO.getGlobalParasStep());

        GasBottleDO gasBottleDO = transformDTOtoDO(gasBottleDTO);
        GasBottleCalc gasBottleCalc = new GasBottleCalc(gasBottleDO);

        double pe = Double.parseDouble(gasBottleDTO.getPe());
        double time = 0.0;
        List<Double> t = new ArrayList<>();
        t.add(time);
        while (time < calTime){
            gasBottleCalc.execute(pe);
            time += timeStep;
            t.add(time);
        }
        Map<String, List<Double>> resultMap = new HashMap<>();
        resultMap.put("t", t);
        resultMap.put("p", gasBottleCalc.getP());
        resultMap.put("q", gasBottleCalc.getQ());
        resultMap.put("rho", gasBottleCalc.getRho());
        resultMap.put("temp", gasBottleCalc.getTemp());
//        ResultShowUtil.showChart(resultMap);
        return resultMap;
    }

    @Override
    public void saveGasBottle(GasBottleDTO gasBottleDTO) {
        GasBottlePO gasBottlePO = transformDTOtoPO(gasBottleDTO);
        gasBottleRepository.save(gasBottlePO);
    }

    @Override
    public GasBottleDTO findGasBottleByName(String bottleName) {
        GasBottlePO gasBottlePO = gasBottleRepository.findGasBottlePOByBottleName(bottleName);
        GasBottleDTO gasBottleDTO = transformPOtoDTO(gasBottlePO);
        return gasBottleDTO;
    }

    @Override
    public List<GasBottleDTO> findAllGasBottle() {
        List<GasBottlePO> gasBottlePOList = gasBottleRepository.findAll();
        List<GasBottleDTO> gasBottleDTOList = new ArrayList<>();
        for (GasBottlePO gasBottlePO : gasBottlePOList) {
            gasBottleDTOList.add(transformPOtoDTO(gasBottlePO));
        }
        return gasBottleDTOList;
    }

    private GasBottleDTO transformPOtoDTO(GasBottlePO gasBottlePO) {
        GasBottleDTO gasBottleDTO = new GasBottleDTO();
        gasBottleDTO.setBottleCA(String.valueOf(gasBottlePO.getBottleCA()));
        gasBottleDTO.setBottleGas(gasBottlePO.getBottleGas());
        gasBottleDTO.setBottlePressure(String.valueOf(gasBottlePO.getBottlePressure()));
        gasBottleDTO.setBottleTemperature(String.valueOf(gasBottlePO.getBottleTemperature()));
        gasBottleDTO.setBottleVol(String.valueOf(gasBottlePO.getBottleVol()));
        gasBottleDTO.setComponentName(gasBottlePO.getBottleName());
        return gasBottleDTO;
    }

    private GasBottlePO transformDTOtoPO(GasBottleDTO gasBottleDTO){
        GasBottlePO gasBottlePO = new GasBottlePO();
        gasBottlePO.setBottleName((gasBottleDTO.getComponentName()));
        gasBottlePO.setBottleGas(gasBottleDTO.getBottleGas());
        gasBottlePO.setBottleCA(Double.parseDouble(gasBottleDTO.getBottleCA()));
        gasBottlePO.setBottleVol(Double.parseDouble(gasBottleDTO.getBottleVol()));//0.05
        gasBottlePO.setBottlePressure(Double.parseDouble(gasBottleDTO.getBottlePressure()));
        gasBottlePO.setBottleTemperature(Double.parseDouble(gasBottleDTO.getBottleTemperature()));
        return gasBottlePO;
    }

    private GasBottleDO transformDTOtoDO(GasBottleDTO gasBottleDTO){
        GasData pushGas = MediumFactory.getGasData(gasBottleDTO.getBottleGas());
        GasBottleDO gasBottleDO = new GasBottleDO(pushGas);
        gasBottleDO.setCA(Double.parseDouble(gasBottleDTO.getBottleCA()));
        gasBottleDO.setVc(Double.parseDouble(gasBottleDTO.getBottleVol()));//0.05
        gasBottleDO.setP0(Double.parseDouble(gasBottleDTO.getBottlePressure()));
        gasBottleDO.setTemp0(Double.parseDouble(gasBottleDTO.getBottleTemperature()));
        return gasBottleDO;
    }
}
