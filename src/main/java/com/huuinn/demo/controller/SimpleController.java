package com.huuinn.demo.controller;

import com.huuinn.demo.RequestMappings;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class SimpleController {

    @RequestMapping(value = RequestMappings.HELLO, method = RequestMethod.GET)
    @ResponseBody
    public String sayHello() {
        return "hello";
    }
}
