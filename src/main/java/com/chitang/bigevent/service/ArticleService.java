package com.chitang.bigevent.service;

import com.chitang.bigevent.pojo.Article;
import com.chitang.bigevent.pojo.PageBean;

public interface ArticleService {
    //新增文章
    void add(Article article);

    //条件分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    //文章详情
    Article detail(Integer id);

    //文章更新
    void update(Article article);

    //删除文章
    void delete(Integer id);
}
