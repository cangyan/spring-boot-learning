package com.huuinn.demo.controller;

import com.huuinn.demo.RequestMappings;
import com.huuinn.demo.ServiceStatusCode;
import com.huuinn.demo.common.Payload;
import com.huuinn.demo.sample.db.User;
import com.huuinn.demo.sample.db.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DBTesterController {

    @Autowired
    @Qualifier("learningJdbcTemplate")
    protected JdbcTemplate learningJdbcTemplate;

    @RequestMapping(value = RequestMappings.TEST_MULTI_DATA_SOURCE)
    public @ResponseBody
    Payload<String> test() {
        Payload<String> payload = new Payload<>();
        payload.setStatus(ServiceStatusCode.OK);
        payload.setMessage("success");
        learningJdbcTemplate.update("INSERT INTO user (name, email) VALUES ('aa', 'bb')");
        payload.setData("ok");

        return payload;
    }

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(RequestMappings.TEST_JPA_MULTI_DB)
    public @ResponseBody Payload<String> testJPA() {
        Payload<String> payload = new Payload<>();
        payload.setStatus(ServiceStatusCode.OK);
        payload.setMessage("success");
        payload.setData("ok");
        User user = new User();
        user.setName("ccc");
        user.setEmail("ccc@outlook.com");

        userRepository.save(user);

        return payload;
    }
}
