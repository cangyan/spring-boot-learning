package com.huuinn.demo.ddd.common.service;

import com.huuinn.demo.ddd.common.model.Vendor;
import com.huuinn.demo.ddd.common.repo.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    @Transactional(readOnly = true)
    public Vendor findOne(Long id) {
        return  vendorRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<Vendor> findAll() {
        return vendorRepository.findAll();
    }

    @Transactional
    public void add (String name, int cost) {
        Vendor vendor = new Vendor();
        vendor.setName(name);
        vendor.setCost(cost);
        vendorRepository.saveAndFlush(vendor);
    }
}
