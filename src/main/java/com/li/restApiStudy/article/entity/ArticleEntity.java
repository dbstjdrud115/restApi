package com.li.restApiStudy.article.entity;

import com.li.restApiStudy.global.jpa.baseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class ArticleEntity extends baseEntity {

    private String subject;
    private String content;

}
