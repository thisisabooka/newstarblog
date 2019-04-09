package com.fuyao.newstarblog.enums;

import lombok.Getter;

/**
 * 文章状态枚举类
 */
@Getter
public enum ArticleStatusEnum {

    EXAMINE_AND_VERIFY(0,"审核"),
    NOT_EXAMINE_AND_VERIFY(1,"未审核"),
    ;

    private Integer id;
    private String msg;

    ArticleStatusEnum(Integer id, String msg) {
        this.id = id;
        this.msg = msg;
    }

}
