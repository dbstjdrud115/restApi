package com.li.restApiStudy.article.dto;


import com.li.restApiStudy.article.entity.ArticleEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(force = true)//기본생성자를 만들면서도, final field를 적용시키기 위해 사용
public class ArticleDTO {

    private final Long id;
    @NotBlank
    private final String subject;
    @NotBlank
    private final String content;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;


    // ArticleEntity를 ArticleDTO로 변환
    public ArticleDTO(ArticleEntity article) {
        this.id = article.getId();
        this.subject = article.getSubject();
        this.content = article.getContent();
        this.createdDate = article.getCreatedDate();
        this.modifiedDate = article.getModifiedDate();
    }

}
