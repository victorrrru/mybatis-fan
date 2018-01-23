package cn.interheart.rain.domain.smartdata.Mapper;

import cn.interheart.rain.domain.smartdata.SmartData;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Administrator
 * @date 2018/01/23 09:44
 */
public interface SmartDataMapper extends MongoRepository<SmartData, String> {
}
