package com.fuyao.newstarblog.beans.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@ToString
@DynamicUpdate  //自动更新
@Entity  //指明这是一个和数据表对应的实体类
@Table(name = "b_tag")
public class Tag {

    @Id  //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //主键自动增长
    private Integer id;

    @Column(name = "tag_name")
    private String tagName;

    @Column(name = "tag_type")
    private Integer tagType;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;


}
