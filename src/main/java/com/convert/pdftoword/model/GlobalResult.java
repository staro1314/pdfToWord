package com.convert.pdftoword.model;

import com.convert.pdftoword.config.enums.ResultEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public  class GlobalResult<T> {
    private Integer code;
    private T data;
    private String msg;
    private String errMsg;

    public GlobalResult(T data){
        this.code = ResultEnum.SUCCESS.getCode();
        this.data = data;
        this.msg = ResultEnum.SUCCESS.getMsg();
    }

    public GlobalResult(Integer code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    public static GlobalResult of(Integer code, String errMsg){
        return new GlobalResult<>(code,errMsg);
    }
}
