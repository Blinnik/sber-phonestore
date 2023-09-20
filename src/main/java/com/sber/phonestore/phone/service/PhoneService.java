package com.sber.phonestore.phone.service;

import com.sber.phonestore.phone.model.Phone;

import java.util.List;

public interface PhoneService {
    Phone create(Phone phone);

    Phone getById(Long phoneId);

    List<Phone> getAll();

    Phone update(Long phoneId, Phone phone);

    void delete(Long phoneId);
}
