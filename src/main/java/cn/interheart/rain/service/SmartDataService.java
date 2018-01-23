package cn.interheart.rain.service;

import cn.interheart.rain.domain.smartdata.Mapper.SmartDataMapper;
import cn.interheart.rain.domain.smartdata.SmartData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/01/23 09:47
 */
@Service
public class SmartDataService {
    @Resource
    private SmartDataMapper smartDataMapper;

    public void insert(SmartData smartData) {
        smartDataMapper.insert(smartData);
    }
    public List<SmartData> getInfo() {
        return smartDataMapper.findAll();
    }
}
