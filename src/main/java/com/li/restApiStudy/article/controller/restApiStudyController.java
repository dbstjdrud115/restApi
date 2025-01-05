package com.li.restApiStudy.article.controller;

import com.li.restApiStudy.article.dto.ArticleDTO;
import com.li.restApiStudy.article.entity.ArticleEntity;
import com.li.restApiStudy.article.service.ArticleService;
import com.li.restApiStudy.global.resultData.ResultData;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* RestApi의 주요특징
* 1. 일반적으론 데이터 교신에 주안점을 두고, 화면에 데이터를 뿌리지 않음
* 2. URL만으로 처리될 정보를 대략 알 수 있음.
* 3. 처리방식(@get,put,push,delete 등..)으로 구분지어 CRUD수행
* */

@RestController//RestApi는 데이터 전송이 주목적이기에, @ResponseBody기능을 겸한 @RestController사용
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
@Slf4j
public class restApiStudyController {

    private final ArticleService articleService;

    @AllArgsConstructor
    @Getter
    public static class ArticleResponse {
        private final List<ArticleDTO> ArticleResponse;
    }

    @GetMapping("")
    public ResultData<ArticleEntity> readAllArticles(){
        try {
            List<ArticleDTO> result = articleService.getArticleList();
            return new ResultData("200", "성공", result);
        } catch (Exception e) {
            log.error("에러발생", e);
            return new ResultData("400", "실패", null);
        }
    }

    @GetMapping("{id}")
    public ArticleDTO readOneArticle(@PathVariable Long id) {
        ArticleDTO articleDTO = articleService.getOneArticle(id);
        //ArticleDTO articleDTO = new ArticleDTO(new ArticleEntity("제목", "내용"));
        return articleDTO;
    }

    @PostMapping("")
    public String createArticle(@Valid @RequestBody ArticleDTO req) {
        /*PostMan
            1차 {"subject":"테스트"
                 ,"content" : "내용"
                } = 성공
            2차 {"subject":"테스트"
                 ,"content" : ""
                } = Validation failed for argument... 체크 성공
            3차 {"subject":"     "
             ,"content" : "공백테스트"
                } = Validation failed for argument... 체크 성공
         */
        System.out.println(req.getSubject());
        System.out.println(req.getContent());
        return "생성완료";
    }

    @PutMapping("{id}")
    public String updateArticle(@PathVariable("id") Long id, @Valid @RequestBody ArticleDTO req) {
        System.out.println(req.getSubject());
        System.out.println(req.getContent());
        return "수정완료";
    }

    @DeleteMapping("{id}")
    public String deleteArticle(@PathVariable Long id) {
        System.out.println(id);
        return "삭제완료";
    }


}
