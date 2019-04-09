package com.fuyao.newstarblog.service.system;

import com.fuyao.newstarblog.beans.system.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {

    public Page<Article> list(Pageable pageable);

//    添加
    public Article add(Article article);


//    查看某一篇具体博文
    public Article look(Integer id);


//    修改：审核，未审核
    public void update(Integer id);



//    public Page<Article> findArticlesByAuthorLike(Pageable pageable,String author);
    public Page<Article> fuzzysearch(Pageable pageable,String author);  //fuzzysearch：模糊查询


//    修改文章
    public Article update(Article article);


//    单个删除
    public void delete(Integer id);


//    批量删除
    public void delSelectedArticle(String[] ids);


    public List<Article> list();


}
