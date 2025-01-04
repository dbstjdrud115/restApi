package com.li.restApiStudy.article.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
* RestApi의 주요특징
*
* 1. 일반적으론 데이터 교신에 주안점을 두고, 화면에 데이터를 뿌리지 않음
* 2. URL만으로 처리될 정보를 대략 알 수 있음.
* 3. 처리방식(@get,put,push,delete 등..)으로 구분지어 CRUD수행
* */

@RestController//RestApi는 데이터 전송이 주목적이기에, @ResponseBody기능을 겸한 @RestController사용
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
public class restApiStudyController {


    @GetMapping("")
    public String readAllData() {
        return "전체 조회";
    }

    @GetMapping("#{id}")
    public String readIdData() {
        return "단건 조회";
    }


}
