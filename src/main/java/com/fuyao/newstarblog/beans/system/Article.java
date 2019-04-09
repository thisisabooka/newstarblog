package com.fuyao.newstarblog.beans.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 文章实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
//@ToString
@Accessors(chain = true)  //链式访问
@DynamicUpdate  //自动更新
@Entity
@Table(name = "b_article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  //bigint

    private String title;

    private String author;

    private String content;  //longtext

    @Column(name = "category_type")
    private Integer categoryType;  //分类编号

    @Column(name = "tag_type")
    private Integer tagType;  //标签编号

    @Column(name = "article_status")
    private Integer articleStatus;  //文章状态；0已审核，1未审核

    private Integer views;  //默认0

    private Integer love;  //默认0

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;



}
