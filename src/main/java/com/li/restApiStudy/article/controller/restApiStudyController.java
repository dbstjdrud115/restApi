package com.li.restApiStudy.article.controller;

import com.li.restApiStudy.article.dto.ArticleDTO;
import com.li.restApiStudy.article.entity.ArticleEntity;
import com.li.restApiStudy.article.service.ArticleService;
import com.li.restApiStudy.global.resultData.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/*
* RestApi의 주요특징
* 1. 일반적으론 데이터 교신에 주안점을 두고, 화면에 데이터를 뿌리지 않음
* 2. URL만으로 처리될 정보를 대략 알 수 있음.
* 3. 처리방식(@get,put,push,delete 등..)으로 구분지어 CRUD수행
* */

@RestController//RestApi는 데이터 전송이 주목적이기에, @ResponseBody기능을 겸한 @RestController사용
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/articles", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "restFulAPI_BasicCRUD_Controller", description = "restfulApi기본 crud 테스트")
public class restApiStudyController {

    private final ArticleService articleService;

    @AllArgsConstructor
    @Getter
    public static class ArticleResponse {
        private final List<ArticleDTO> ArticleResponse;
    }

    @GetMapping("")
    @Operation(summary = "게시물 다건조회")
    public ResultData<ArticleEntity> readAllArticles(){
        try {
            List<ArticleDTO> result = articleService.getArticleList();
            return new ResultData("200", "성공", result);
        } catch (Exception e) {
            log.error("에러발생", e);
            return new ResultData("400", "실패", null);
        }
    }

    @GetMapping(value = "{id}")
    @Operation(summary = "게시물 단건조회")
    public ResultData readOneArticle(@PathVariable Long id) {
        ArticleDTO articleDTO = articleService.getOneArticle(id);
        return new ResultData("200", "단건조회성공~", articleDTO);
    }

    @PostMapping(value = "", consumes = ALL_VALUE)
    @Operation(summary = "게시물 생성")
    public ResultData createArticle(@Valid @RequestBody ArticleDTO req) {


        try {
            ArticleEntity createResult = articleService.write(req.getContent(), req.getSubject());
            return new ResultData("200", "성공", createResult);
        } catch (Exception e) {
            log.error("생성에러 발생: {}", e.getMessage(), e);
            return new ResultData("400", "실패", null);
        }
    }

    @PutMapping("{id}")
    @Operation(summary = "게시물 수정")
    public ResultData<ArticleEntity> updateArticle(@PathVariable("id") Long id, @Valid @RequestBody ArticleDTO req) {
        try {
            ArticleEntity articleEntity = articleService.update(id, req.getSubject(), req.getContent());
            return new ResultData<>("200", "수정완료", articleEntity);
        } catch (Exception e) {
            log.error("수정에러 발생: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("{id}")
    @Operation(summary = "게시물 삭제")
    public ResultData<ArticleEntity> deleteArticle(@PathVariable Long id) {
        try{
            ArticleEntity articleEntity = articleService.deleteArticle(id);
            return new ResultData<>("200", "삭제 성공", articleEntity);
        }catch (Exception e){
            log.error("생성에러 발생: {}", e.getMessage(), e);
            return new ResultData("400", "실패", null);
        }
    }


}
