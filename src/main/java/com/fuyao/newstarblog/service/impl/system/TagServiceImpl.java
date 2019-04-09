package com.fuyao.newstarblog.service.impl.system;

import com.fuyao.newstarblog.beans.system.Tag;
import com.fuyao.newstarblog.repository.system.TagRepository;
import com.fuyao.newstarblog.service.system.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional  //基于注解的事务控制
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Page<Tag> list(Pageable pageable) {
        return tagRepository.findAllBy(pageable);
    }


    @Override
    public List<Tag> list() {
        return tagRepository.findAll();
    }


//    通过标签编号查询标签信息
    @Override
    public Tag findByTagType(Integer tagType) {
        if(tagType==null){
//            throw new RuntimeException("标签编号为空，非法");
            return null;
        }else{
            return tagRepository.findByTagType(tagType);
        }
    }


}
