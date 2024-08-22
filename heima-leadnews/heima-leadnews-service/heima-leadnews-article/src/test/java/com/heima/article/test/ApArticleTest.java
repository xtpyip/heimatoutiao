package com.heima.article.test;

import com.heima.article.ArticleApplication;
import com.heima.article.service.ApArticleService;
import com.heima.model.article.dtos.ArticleDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @ClassName: ApArticleTest
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 测试类
 **/
@SpringBootTest(classes = ArticleApplication.class)
@RunWith(SpringRunner.class)
public class ApArticleTest {
    @Autowired
    private ApArticleService apArticleService;

    @Test
    public void test01(){
        ArticleDto dto = new ArticleDto();
//        dto.setId(1821899857846210561l);
        dto.setTitle("黑马头条项目背景222222222222222");
        dto.setAuthorId(1102l);
        dto.setLayout((short)1);
        dto.setLabels("黑马头条");
        dto.setPublishTime(new Date());
        dto.setImages("http://192.168.200.130:9000/leadnews/2024/08/09/e53b5e4ca55f429caf253c5ca3e5c2b7.jpg");
        dto.setContent("22222222222黑马头条项目背景,黑马头条项目背景,黑马头条项目背景,黑马头条项目背景，黑马头条项目背景");

        apArticleService.saveArticle(dto);


    }
}
