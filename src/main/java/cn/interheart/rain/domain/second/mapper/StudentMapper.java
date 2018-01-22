package cn.interheart.rain.domain.second.mapper;

import cn.interheart.rain.domain.second.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {

    @Select("select * from student where id=#{id}")
    Student getStudentInfo(@Param("id") Integer id);



    Student selectByPrimaryKey(Integer id);


}