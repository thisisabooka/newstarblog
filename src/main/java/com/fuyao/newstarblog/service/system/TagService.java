package com.fuyao.newstarblog.service.system;

import com.fuyao.newstarblog.beans.system.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    public Page<Tag> list(Pageable pageable);


    public List<Tag> list();


//    通过标签编号查询标签信息
    public Tag findByTagType(Integer tagType);


}
