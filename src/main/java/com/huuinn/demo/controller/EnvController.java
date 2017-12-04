package com.huuinn.demo.controller;

import com.huuinn.demo.RequestMappings;
import com.huuinn.demo.ServiceStatusCode;
import com.huuinn.demo.common.Payload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class EnvController {

    @Value("${app.env}")
    private String env;

    @RequestMapping(value = RequestMappings.TEST_ENV)
    @ResponseBody
    public Payload<Object> testEnv() {
        Payload<Object> payload = new Payload<>();
        payload.setStatus(ServiceStatusCode.OK);
        payload.setMessage("success");
        HashMap<String, String> map = new HashMap<>();
        map.put("env", env);
        payload.setData(map);

        return payload;
    }
}
