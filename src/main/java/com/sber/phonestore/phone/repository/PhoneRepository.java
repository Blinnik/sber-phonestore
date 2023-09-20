package com.sber.phonestore.phone.repository;

import com.sber.phonestore.phone.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
