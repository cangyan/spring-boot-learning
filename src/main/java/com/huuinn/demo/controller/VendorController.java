package com.huuinn.demo.controller;

import com.huuinn.demo.RequestMappings;
import com.huuinn.demo.ServiceStatusCode;
import com.huuinn.demo.common.Payload;
import com.huuinn.demo.ddd.common.model.Vendor;
import com.huuinn.demo.ddd.common.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = RequestMappings.TEST_MULTI_DB)
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Payload<String> add(@RequestParam String name, @RequestParam int cost) {
        vendorService.add(name, cost);
        Payload<String> payload = new Payload<>();
        payload.setStatus(ServiceStatusCode.OK);
        payload.setMessage("success");
        payload.setData("saved");

        return payload;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Payload<Iterable<Vendor>> list() {
        Payload<Iterable<Vendor>> payload = new Payload<>();
        payload.setStatus(ServiceStatusCode.OK);
        payload.setMessage("success");
        payload.setData(vendorService.findAll());
        return payload;
    }
}
