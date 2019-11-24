package cn.edu.nwpu.repository;

import cn.edu.nwpu.domain.po.GasBottlePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @InterfaceName GasBottleRepository
 * @Author: wkx
 * @Date: 2019/10/21 21:58
 * @Version: v1.0
 * @Description:
 */
@Repository
public interface GasBottleRepository extends JpaRepository<GasBottlePO, Integer> {

    GasBottlePO findGasBottlePOByBottleName(String bottleName);

    List<GasBottlePO> findAll();
}
