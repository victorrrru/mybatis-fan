package cn.interheart.rain;

import cn.interheart.rain.domain.second.entity.Student;
import cn.interheart.rain.service.SchoolService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RainApplicationTests {

	@Resource
	private SchoolService schoolService;
	@Test
	public void contextLoads1() {
		long a = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			Student student1 = new Student().setId(1);
			schoolService.newSelect(student1);
		}
		System.out.println(System.currentTimeMillis() - a);
	}
	@Test
	public void contextLoads() {
		long a = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			Student student = new Student().setId(1);
			schoolService.jiuSelect(student);
		}
		System.out.println(System.currentTimeMillis() - a);
	}

}
