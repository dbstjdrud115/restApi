package com.li.restApiStudy.article.repository;

import com.li.restApiStudy.article.entity.articleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface articleRepository extends JpaRepository<articleEntity, Long> {


}
