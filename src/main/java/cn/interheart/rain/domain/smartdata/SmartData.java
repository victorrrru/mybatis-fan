package cn.interheart.rain.domain.smartdata;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * @author Administrator
 * @date 2018/01/23 09:41
 */
@Data
@Accessors(chain = true)
@Document(collection = "smart_1111")
public class SmartData {
    @Id
    private String _id;
    private String name;
    private String[] hr;
}
