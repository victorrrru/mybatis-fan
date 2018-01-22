package cn.interheart.rain.controller;

import cn.interheart.rain.domain.second.entity.Student;
import cn.interheart.rain.service.SchoolService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @date 2018/01/20 11:27
 */
@RestController
@RequestMapping("/school")
public class SchoolAction {
    @Resource
    private SchoolService schoolService;
    @RequestMapping("/det")
    public Student getStuDet(Student student1) {
        return schoolService.newSelect(student1);
    }
}
