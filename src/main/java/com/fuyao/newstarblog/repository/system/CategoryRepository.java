package com.fuyao.newstarblog.repository.system;

import com.fuyao.newstarblog.beans.system.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 文章分类资源库
 */
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    public Page<Category> findAllBy(Pageable pageable);


//    通过类目编号查询类目信息
    public Category findByCategoryType(Integer categoryType);


//    根据分类名称分页模糊查询分类列表
    public Page<Category> findCategoriesByCategoryNameLike(Pageable pageable,String categoryName);

}
