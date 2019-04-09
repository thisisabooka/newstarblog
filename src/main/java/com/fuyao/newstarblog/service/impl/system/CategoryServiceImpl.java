package com.fuyao.newstarblog.service.impl.system;

import com.fuyao.newstarblog.beans.system.Category;
import com.fuyao.newstarblog.repository.system.CategoryRepository;
import com.fuyao.newstarblog.service.system.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional  //基于注解的事务控制
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


//    分页查询分类列表信息
    @Override
    public Page<Category> list(Pageable pageable) {
        return categoryRepository.findAllBy(pageable);
    }


    @Override
    public List<Category> list() {
        return categoryRepository.findAll();
    }


    @Override
    public Category findByCategoryType(Integer categoryType) {
        if(categoryType==null){
//            throw new RuntimeException("分类编号为空，非法！");
            return null;
        }else{
            return categoryRepository.findByCategoryType(categoryType);
        }
    }


    @Override
    public Page<Category> findCategoriesByCategoryNameLike(Pageable pageable, String categoryName) {
        return categoryRepository.findCategoriesByCategoryNameLike(pageable,categoryName);
    }


    @Override
    public Category add(Category category) {
        if(category==null){
//            throw new RuntimeException("小星提示：文章分类信息为空，非法！");
            return null;
        }else {
            Category c = categoryRepository.save(category);
            return c;
        }
    }


    @Override
    public Category update(Category category) {
        if(category.getId()==null||category.getId()<1){
//            throw new RuntimeException("小星提示：文章分类id不合法！");
            return null;
        }else{
            Category c = categoryRepository.save(category);
            return c;
        }
    }


    @Override
    public Category findCategoryById(Integer id) {
        return categoryRepository.findOne(id);
    }



    @Override
    public void delete(Integer id) {
       if(id==null){
           throw new RuntimeException("小星提示：文章分类为空，非法！");
       }else{
           categoryRepository.delete(id);
       }
    }


    @Override
    public void delSelectedCategory(String[] ids) {
        if(ids!=null&&ids.length>0){
//            遍历数组
            for(String id : ids){
//                调用dao删除！
                Integer temp = Integer.parseInt(id);
                categoryRepository.delete(temp);
            }
        }else{
            throw new RuntimeException("小星提示：发生未知错误，分类批量删除失败！");
        }
    }


}
