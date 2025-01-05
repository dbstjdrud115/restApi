package com.li.restApiStudy.global.initData;

import com.li.restApiStudy.article.service.ArticleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class InitData {
    //CommandLineRunner 는 extends run한 Class로,
    //이 인터페이스를 @Bean으로 구현한 대상은 앱 시작후 호출된다.
    @Bean
    CommandLineRunner InitData(ArticleService articleService) {
        return args -> {
            articleService.write("제목 1", "내용 1");
            articleService.write("제목 2", "내용 2");
            articleService.write("제목 3", "내용 3");
            articleService.write("제목 4", "내용 4");
            articleService.write("제목 5", "내용 5");
            articleService.write("제목 6", "내용 6");
        };
    }
}