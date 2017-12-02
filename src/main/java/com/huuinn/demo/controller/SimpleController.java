package com.huuinn.demo.controller;

import com.huuinn.demo.RequestMappings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SimpleController {

    @RequestMapping(value = RequestMappings.HELLO, method = RequestMethod.GET)
    @ResponseBody
    public String sayHello() {
        return "hello";
    }

    @RequestMapping(value = RequestMappings.HELLO_JSON, method = RequestMethod.GET)
    public @ResponseBody
    Map<String, String> sayHelloJson() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "hello");

        return map;
    }

    @RequestMapping(value = RequestMappings.TEST_PATH_PARAMS, method = RequestMethod.GET)
    public @ResponseBody Map<String, String> testPathParams(@PathVariable(value = "name") String name) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", name);

        return map;
    }
}
