package com.simple.mvc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse implements Serializable {
    private static final long serialVersionUID = 7138636080435478470L;

    /** 响应码 */
    private String retCode;
    /** 响应信息 */
    private String retMsg;

    private Object body;


    public ServiceResponse(String retCode, String retMsg) {
        super();
        this.retCode = retCode;
        this.retMsg = retMsg;
    }


    public ServiceResponse(Object body) {
        super();
        this.retCode = "SUCCESS";
        this.retMsg = "200";
        this.body = body;
    }

}
