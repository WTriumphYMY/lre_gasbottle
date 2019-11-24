package cn.edu.nwpu.controller;

import cn.edu.nwpu.domain.dto.GasBottleDTO;
import cn.edu.nwpu.service.GasBottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ClientController
 * @Author: wkx
 * @Date: 2019/7/20 09:44
 * @Version: v1.0
 * @Description: 客户端Controller对外暴露接口
 */
@RestController
public class GasBottleSimController {

    @Autowired
    private GasBottleService gasBottleService;


    @PostMapping("gasBottleSim")
    public Map<String, List<Double>> gasBottleSim(@RequestBody GasBottleDTO gasBottleDTO){
        return gasBottleService.gasBottleSim(gasBottleDTO);
    }

    @PostMapping("/saveGasBottle")
    public void saveGasBottle(@RequestBody GasBottleDTO gasBottleDTO){
        gasBottleService.saveGasBottle(gasBottleDTO);
    }

    @PostMapping("findGasBottleByName")
    public GasBottleDTO findGasBottleByName(@RequestBody String bottleName){
        return gasBottleService.findGasBottleByName(bottleName);
    }

    @PostMapping("findAllGasBottle")
    public List<GasBottleDTO> findAllGasBottle(){
        return gasBottleService.findAllGasBottle();
    }

}
