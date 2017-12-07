package com.huuinn.demo.controller;

import com.huuinn.demo.RequestMappings;
import com.huuinn.demo.ServiceStatusCode;
import com.huuinn.demo.common.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class EnvController {

    @Value("${app.env}")
    private String envStr;

    @Autowired
    private Environment env;

    @RequestMapping(value = RequestMappings.TEST_ENV)
    @ResponseBody
    @Cacheable(value = "env")
    public Payload<Object> testEnv() {
        Payload<Object> payload = new Payload<>();
        payload.setStatus(ServiceStatusCode.OK);
        payload.setMessage("success");
        HashMap<String, String> map = new HashMap<>();
        map.put("env", envStr);
        map.put("app.env", env.getProperty("app.env"));
        payload.setData(map);

        return payload;
    }
}
