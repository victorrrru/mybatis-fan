package cn.interheart.rain.service;

import cn.interheart.rain.domain.second.entity.Student;
import cn.interheart.rain.domain.smart.mapper.SchoolMapper;
import cn.interheart.rain.domain.second.mapper.StudentMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author Administrator
 * @date 2018/01/20 11:27
 */
@Service
public class SchoolService {
    @Resource
    private SchoolMapper schoolMapper;
    @Resource
    private StudentMapper studentMapper;

    public Student newSelect(Student student) {
        return schoolMapper.selectByPrimaryKey(student);
    }
    public Student jiuSelect(Student student) {
        return studentMapper.selectByPrimaryKey(student.getId());
    }
}
