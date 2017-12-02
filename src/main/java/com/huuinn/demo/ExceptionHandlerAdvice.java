package com.huuinn.demo;

import com.huuinn.demo.common.Payload;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Payload<String> errorHandler(HttpServletRequest request, Exception e) throws Exception {
        Payload<String> payload = new Payload<>();
        payload.setStatus(ServiceStatusCode.UNKNOWN_ERROR);
        payload.setMessage(e.getMessage());
        payload.setData("");

        return payload;
    }
}
