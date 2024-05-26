package com.atguigu.common.config.exception;

import lombok.Data;

@Data
public class GuiguException extends RuntimeException{
    private Integer code;
    private String msg;
    public GuiguException(Integer code,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
