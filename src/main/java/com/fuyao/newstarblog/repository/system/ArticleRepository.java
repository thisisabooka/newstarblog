package com.fuyao.newstarblog.repository.system;

import com.fuyao.newstarblog.beans.system.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 博文资源库
 */
public interface ArticleRepository extends JpaRepository<Article,Integer> {

    public Page<Article> findAllBy(Pageable pageable);


    public Page<Article> findArticlesByAuthorLike(Pageable pageable,String author);


}
