package com.li.restApiStudy.global.resultData;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResultData<T> {

    private String resultCode;
    private String msg;
    private T data;

    //static같은 상수를 쓴 이유 = 인스턴스화시키지 않고 바로 쓸 수 있음
    public static <T> ResultData<T> of(String resultCode, String msg, T data) {
        return new ResultData<>(resultCode, msg, data);
    }

    public static <T> ResultData<T> of(String resultCode, String msg) {
        return new ResultData<>(resultCode, msg, null);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return resultCode.startsWith("200");
    }

    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }

}
