package com.li.restApiStudy.article.service;


import com.li.restApiStudy.article.dto.ArticleDTO;
import com.li.restApiStudy.article.entity.ArticleEntity;
import com.li.restApiStudy.article.repository.ArticleRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<ArticleDTO> getArticleList() {

        /*
        * Entity만 사용해도 분기처리까지 가능할 듯 하지만,
        * 아래의 이유들을 고려했을떄, DTO와 Entity를 분리해서 사용하는것이 권장되는것으로 보인다.
        *
        * 1. 민감한 정보를 조회하더라도, 가공과정이나 첨삭을 거쳐야 하는경우
        * 2. 포맷팅을 해야하는 경우 ("yyyy-MM-dd")
        * 3. 유지보수와 확장용이(entity 조회결과를 기반으로 다양한 확장 가능)
         */
        List<ArticleEntity> getArticleList = articleRepository.findAll();

        /*
           stream()은 데이터 컬렉션에 대한 작업 수행시 유용.
           아래 코드의 경우, Entity List에 대하여(getArticleList.stream())
           List의 각 구성물을 DTO로 변경하고(.map(eachResult ->)
           새로운 List로 반환해준다.(.collect())
        */
        List<ArticleDTO>articleDTOList = getArticleList.stream()
                                .map(articleEntity -> new ArticleDTO(articleEntity))
                                .collect(Collectors.toList());
        return articleDTOList;
    }

    public ArticleDTO getOneArticle(Long id) {
        /*
        * 전체 조회인 getArticleList은 Optional을 사용하지 않는데,
        * 단건 조회인 getOneArticle은  Optional을 사용하는 이유는,
        * 전자의 반환값인 List는 값이 없더라도 빈 데이터콜렉션을 반환하지만,
        * 후자는 값이 없을경우 Optional.empty()를 반환하기 떄문이다.
        *  따라서, 값이 없는 경우에 대한 조치사안을 작성해두어야 한다.
        * */
        Optional<ArticleEntity> getOneArticle = articleRepository.findById(id);
        return getOneArticle.map(result->new ArticleDTO(result)).orElse(null);
    }

    public ArticleEntity write(String content, String subject) {
        ArticleEntity article = ArticleEntity.builder()
                .subject(subject)
                .content(content)
                .build();
        articleRepository.save(article);
        return article;
    }

    public ArticleEntity update(Long id, @NotBlank String subject, @NotBlank String content) {
        ArticleEntity article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 데이터가 없습니다. id=" + id));
        article.setSubject(subject);
        article.setContent(content);
        articleRepository.save(article);
        return article;
    }

    public ArticleEntity deleteArticle(Long id) {

        ArticleEntity article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 데이터가 없습니다. id=" + id));
        articleRepository.deleteById(id);
        return article;
    }
}
