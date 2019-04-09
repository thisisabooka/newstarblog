package com.fuyao.newstarblog.repository;

import com.fuyao.newstarblog.beans.system.Article;
import com.fuyao.newstarblog.repository.system.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleRepositroyTest {

    @Autowired
    private ArticleRepository articleRepository;


    @Test
    public void testSave(){
        Article article = new Article().setId(4).setAuthor("李思思").setContent("text is text")
                .setArticleStatus(0).setTagType(1);
        Article result = articleRepository.save(article);
//        Assert.assertEquals("李思思",result.getAuthor());
    }


}
