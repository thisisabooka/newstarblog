package com.fuyao.newstarblog.repository.system;

import com.fuyao.newstarblog.beans.system.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 文章标签资源库
 */
public interface TagRepository extends JpaRepository<Tag,Integer> {

    public Page<Tag> findAllBy(Pageable pageable);


//    通过标签编号查询标签信息
    public Tag findByTagType(Integer tagType);


}
