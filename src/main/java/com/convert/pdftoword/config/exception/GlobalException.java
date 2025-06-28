package com.convert.pdftoword.config.exception;

import com.convert.pdftoword.config.enums.ResultEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalException extends RuntimeException{

    private ResultEnum resultEnum;

    private Integer errCode;

    public GlobalException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.errCode = resultEnum.getCode();
    }

    public GlobalException(String message, ResultEnum resultEnum) {
        super(message);
        this.errCode = resultEnum.getCode();
    }

    public GlobalException(Integer code,String message) {
        super(message);
        this.errCode = code;
    }

    public static GlobalException of(Integer errCode,String errMsg){
        return new GlobalException(errCode, errMsg);
    }

    public  static GlobalException of(String errMsg){
        return new GlobalException(errMsg,ResultEnum.SYS_ERROR);
    }
}
