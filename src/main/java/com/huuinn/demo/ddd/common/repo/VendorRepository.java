package com.huuinn.demo.ddd.common.repo;

import com.huuinn.demo.ddd.common.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
