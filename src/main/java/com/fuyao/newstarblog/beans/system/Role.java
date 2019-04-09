package com.fuyao.newstarblog.beans.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Accessors(chain = true)  //支持链式访问
@DynamicUpdate  //动态更新；SpringData-Jpa
//使用Jpa注解来配置映射关系
@Entity  //告诉Jpa这是一个实体类（和数据表所映射的类）
@Table(name = "sys_role")  //@Table来映射和哪个数据表对应
public class Role {

    @Id  //这是一个主键
//    @GeneratedValue
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //自增主键
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)  //SpringData-Jpa =》 MySQL不支持
    private Integer id;

    @Column(name = "english_name")
    private String englishName;

    @Column(name = "chinese_name")
    private String chineseName;

    private String description;  //varchar(256)

    @Column(name = "role_type")
    private Integer roleType;  //角色编号：int(11)

    private Date createTime;

    private Date updateTime;

    /*public static void main(String[] args){
        Role role = new Role().setId(1).setEnglishName("root").setChineseName("群主")
                .setDescription("拥有至高无上的权利").setRoleType(1)
                .setCreateTime(new Date()).setUpdateTime(new Date());
        System.out.println(role);
    }*/

}
