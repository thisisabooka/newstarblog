package com.fuyao.newstarblog.service.impl.system;

import com.fuyao.newstarblog.beans.system.Article;
import com.fuyao.newstarblog.enums.ArticleStatusEnum;
import com.fuyao.newstarblog.repository.system.ArticleRepository;
import com.fuyao.newstarblog.service.system.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Page<Article> list(Pageable pageable) {
        return articleRepository.findAllBy(pageable);
    }


//    添加
    @Override
    public Article add(Article article) {
        article.setArticleStatus(1);  //1未审核
        article.setViews(0);
        article.setLove(0);
        if(article==null){
//            throw new RuntimeException("小星提示：文章为空，非法！");
            return null;
        }else{
            return articleRepository.save(article);
        }
    }


//    查看某一篇具体博文
    @Override
    public Article look(Integer id) {
        if(id==null||id<=0){
//            throw new RuntimeException("小星提示：id非法");
            return null;
        }else {
            return articleRepository.findOne(id);
        }
    }


//    修改：审核，未审核
    @Override
    public void update(Integer id) {
        if(id==null||id<=0){
            throw new RuntimeException("小星提示：id非法");
        }else{
            Article a = articleRepository.findOne(id);  //before
            if(a.getArticleStatus()==ArticleStatusEnum.EXAMINE_AND_VERIFY.getId()){  //0
                a.setArticleStatus(ArticleStatusEnum.NOT_EXAMINE_AND_VERIFY.getId());
            }else{
                a.setArticleStatus(ArticleStatusEnum.EXAMINE_AND_VERIFY.getId());
            }
            articleRepository.save(a);
        }
    }


//    模糊查询
    @Override
    public Page<Article> fuzzysearch(Pageable pageable,String author) {
        return articleRepository.findArticlesByAuthorLike(pageable, author);
    }


//    修改文章
    @Override
    public Article update(Article article) {
        if(article.getId()==null||article.getId()<=0){
//            throw new RuntimeException("小星提示：文章id非法");
            return null;
        }else{
            Article one = articleRepository.findOne(article.getId());
            one.setTitle(article.getTitle());
            one.setContent(article.getContent());
            one.setCategoryType(article.getCategoryType());
            one.setTagType(article.getTagType());
            Article two = articleRepository.saveAndFlush(one);
            return two;
        }
    }


//    单个删除
    @Override
    public void delete(Integer id) {
        if(id==null||id<=0){
            throw new RuntimeException("小星提示：id非法！");
        }else{
            articleRepository.delete(id);
        }
    }


//    批量删除！
    @Override
    public void delSelectedArticle(String[] ids) {
        if(ids!=null&&ids.length>0){
//            遍历数组
            for(String id : ids){
//                调用dao删除！
                Integer temp = Integer.parseInt(id);
                articleRepository.delete(temp);
            }
        }else{
            throw new RuntimeException("小星提示：发生未知错误，文章批量删除失败！");
        }
    }


//    不分页文章列表
    @Override
    public List<Article> list() {
        return articleRepository.findAll();
    }


}
