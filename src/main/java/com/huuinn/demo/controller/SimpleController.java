package com.huuinn.demo.controller;

import com.huuinn.demo.RequestMappings;
import com.huuinn.demo.ServiceStatusCode;
import com.huuinn.demo.common.CustomException;
import com.huuinn.demo.common.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = RequestMappings.TEST_EXCEPTION, method = RequestMethod.GET)
    public @ResponseBody Map<String, String> testException() throws CustomException {
        throw new CustomException();
    }
}
