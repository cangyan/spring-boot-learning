package com.huuinn.demo.controller;

import com.huuinn.demo.RequestMappings;
import com.huuinn.demo.ServiceStatusCode;
import com.huuinn.demo.common.Payload;
import com.huuinn.demo.sample.db.User;
import com.huuinn.demo.sample.db.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = RequestMappings.USER)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //curl -H "application/x-www-form-urlencoded" -d "name=First&email=someemail@someemaprovider.com" -X POST http://localhost:8080/user
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String add(@RequestParam String name, @RequestParam String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
        return "saved";
    }

    @RequestMapping(method = RequestMethod.GET)
    @Cacheable("userList")
    public @ResponseBody
    Payload<Iterable<User>> list() {
        Payload<Iterable<User>> payload = new Payload<>();
        payload.setStatus(ServiceStatusCode.OK);
        payload.setMessage("success");
        payload.setData(userRepository.findAll());

        return payload;
    }
}
