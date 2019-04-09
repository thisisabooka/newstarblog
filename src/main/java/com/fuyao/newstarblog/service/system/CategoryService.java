package com.fuyao.newstarblog.service.system;

import com.fuyao.newstarblog.beans.system.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    public Page<Category> list(Pageable pageable);


//    查询所有的类目
    public List<Category> list();


//    通过类目编号查询类目信息
    public Category findByCategoryType(Integer categoryType);


    public Page<Category> findCategoriesByCategoryNameLike(Pageable pageable,String categoryName);


//    添加
    public Category add(Category category);


//    修改
    public Category update(Category category);


//    查一个
    public Category findCategoryById(Integer id);


//    删除一个分类
    public void delete(Integer id);


//    批量删除
    public void delSelectedCategory(String[] ids);




}
