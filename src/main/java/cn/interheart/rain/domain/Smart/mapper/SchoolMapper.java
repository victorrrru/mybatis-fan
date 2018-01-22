package cn.interheart.rain.domain.Smart.mapper;

import cn.interheart.rain.common.MyMapper;
import cn.interheart.rain.domain.second.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Administrator
 * @date 2018/01/19 18:06
 */
public interface SchoolMapper extends MyMapper<Student>{
        @Select("select * from student where id=#{id}")
        Student getStudentInfo(@Param("id") Integer id);
}
