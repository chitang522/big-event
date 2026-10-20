package com.chitang.bigevent.mapper;

import com.chitang.bigevent.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //新增
    @Insert("insert into article(title, content, cover_img,state,category_id, create_user, create_time, update_time) values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);


    List<Article> list(@Param("userId")Integer userId,
                       @Param("categoryId")Integer categoryId,
                       @Param("state") String state);


    @Select("select * from article where id = #{id}")
    Article detail(Integer id);


    //更新文章
    @Update("update article set title = #{title}, content = #{content}, cover_img = #{coverImg}, " +
            "state = #{state}, category_id = #{categoryId}, update_time = now() where id = #{id}")
    void update(Article article);

    @Delete("delete from article where id=#{id} and create_user = #{userId}")
    void delete(@Param("id") Integer id, @Param("userId") Integer userId);
}
