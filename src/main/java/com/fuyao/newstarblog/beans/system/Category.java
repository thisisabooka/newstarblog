package com.fuyao.newstarblog.beans.system;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain = true)  //支持链式访问
@DynamicUpdate  //自动更新
@Entity
@Table(name = "b_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_type")
    private Integer categoryType;  //分类编号

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;



}
