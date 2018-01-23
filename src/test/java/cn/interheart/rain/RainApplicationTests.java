package cn.interheart.rain;

import cn.interheart.rain.domain.second.entity.Student;
import cn.interheart.rain.domain.smartdata.SmartData;
import cn.interheart.rain.service.SchoolService;
import cn.interheart.rain.service.SmartDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RainApplicationTests {

	@Resource
	private SchoolService schoolService;
	@Resource
	private SmartDataService smartDataService;
	@Test
	public void test1() {
		long a = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			Student student1 = new Student().setId(1);
			schoolService.newSelect(student1);
		}
		System.out.println(System.currentTimeMillis() - a);
	}
	@Test
	public void test2() {
		long a = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			Student student = new Student().setId(1);
			schoolService.jiuSelect(student);
		}
		System.out.println(System.currentTimeMillis() - a);
	}
	@Test
	public void test3() {
		SmartData smartData = new SmartData().setName("wangwu");
		smartDataService.insert(smartData);
	}
	@Test
	public void test4() {
//		SmartData smartData = new SmartData().setName("wangwu");
		List<SmartData> info = smartDataService.getInfo();
		System.out.println(info);
	}

}
