package cn.interheart.rain.domain.second.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
@Data
@Accessors(chain = true)
public class Student implements Serializable {
    private Integer id;

    /**
     * 学号（登录名）
     */
    private String number;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String logo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 班级id
     */
    private Integer schoolClassId;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性别  0女 1男
     */
    private Byte sex;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 民族
     */
    private String nation;

    /**
     * 家住地址
     */
    private String address;

    /**
     * 身高cm
     */
    private Short height;

    /**
     * 体重kg
     */
    private BigDecimal weight;

    private Date gmtCreate;

    private Date gmtModified;

}