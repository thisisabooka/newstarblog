package com.fuyao.newstarblog.beans.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 后台管理系统管理员
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)  //链式访问
@DynamicUpdate  //动态更新
@Entity
@Table(name = "sys_admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  //bigint

//    校验昵称在1到30个字符之间
//    message是提示校验出错显示的信息
//    @Size(min = 1,max = 30,message = "请输入1~30个字符的管理员昵称")

    @Length(min = 4,max = 30,message = "昵称只能在4~30位之间")
    private String nickname;

//    @JsonIgnore
    @Length(min = 4,max = 30,message = "密码只能在4~30位之间")
    private String password;

//    @NotBlank(message = "年龄不能为空")
//    @Pattern(regexp="^[0-9]{1,2}$",message="年龄不正确")

    private Integer age;

//    @Pattern(regexp = "^1[35678]\\d{9}$",message = "手机号格式不正确")
    private String phone;

//    非空校验
//    @NotNull(message = "请输入合法的邮箱地址")
    private String email;

    private String avatar;  //链接地址

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    private String monster;  //0账号正常，1账号被锁定

    @Column(name = "role_type")
    private Integer roleType;  //角色编号

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /*@JsonIgnore
    private String salt;  //盐值*/

}


