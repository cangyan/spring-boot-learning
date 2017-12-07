package com.huuinn.demo.controller;

import com.huuinn.demo.RequestMappings;
import com.huuinn.demo.ServiceStatusCode;
import com.huuinn.demo.common.CustomException;
import com.huuinn.demo.common.Payload;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SimpleController {

    @RequestMapping(value = RequestMappings.HELLO, method = RequestMethod.GET)
    @ResponseBody
    @Cacheable("say-hello")
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
    public @ResponseBody
    Map<String, String> testPathParams(@PathVariable(value = "name") String name) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", name);

        return map;
    }

    @RequestMapping(value = RequestMappings.TEST_EXCEPTION, method = RequestMethod.GET)
    public @ResponseBody
    Map<String, String> testException() throws CustomException {
        throw new CustomException();
    }

    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public Payload<Object> errorHandler(HttpServletRequest request, CustomException e) throws Exception {
        Payload<Object> payload = new Payload<>();
        payload.setStatus(ServiceStatusCode.TEST_ERROR);
        payload.setMessage(e.getMessage());
        HashMap<String, String> map = new HashMap<>();
        map.put("url", request.getRequestURL().toString());
        payload.setData(map);

        return payload;
    }


    @RequestMapping(value = RequestMappings.TEST_REQUEST_PARAMS, method = RequestMethod.GET)
    public @ResponseBody
    Map<String, String> testRequestParams(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "age", required = false, defaultValue = "10") String age) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);

        return map;
    }

    @CrossOrigin
    @RequestMapping(value = RequestMappings.TEST_CORS)
    public @ResponseBody Map<String, String> testCORS() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("content", "test cors");

        return map;
    }

    @RequestMapping(value = RequestMappings.TEST_GLOBAL_CORS)
    public @ResponseBody Map<String, String> testGlobalCORS() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "2");
        map.put("content", "test global cors");

        return map;
    }
}
