package com.sber.phonestore.phone.service.impl;

import com.sber.phonestore.exception.NotFoundException;
import com.sber.phonestore.phone.model.Phone;
import com.sber.phonestore.phone.repository.PhoneRepository;
import com.sber.phonestore.phone.service.PhoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the PhoneService interface
 *
 * @see PhoneService
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PhoneServiceImpl implements PhoneService {

    /** Interface for interacting with the Phone entity at the DB level */
    private final PhoneRepository phoneRepository;

    @Override
    public Phone create(Phone phone) {
        phone.setId(null);

        Phone newPhone = phoneRepository.save(phone);
        log.debug("A phone was created, id={}", newPhone.getId());

        return newPhone;
    }

    @Override
    public Phone getById(Long phoneId) {
        Phone foundPhone = phoneRepository.findById(phoneId).orElseThrow(
                () -> new NotFoundException(String.format("Phone with id=%s not found", phoneId))
        );
        log.debug("A phone was received, {}", foundPhone);

        return foundPhone;
    }

    @Override
    public List<Phone> getAll() {
        List<Phone> phones = phoneRepository.findAll();
        log.debug("A list of phones was received: {}", phones);

        return phones;
    }

    @Override
    public Phone update(Long phoneId, Phone phone) {
        if (!phoneRepository.existsById(phoneId)) {
            throw new NotFoundException(String.format("Phone with id=%s not found", phoneId));
        }

        phone.setId(phoneId);

        phoneRepository.save(phone);
        log.debug("A phone with an id {} was updated", phoneId);

        return phone;
    }

    @Override
    public void delete(Long phoneId) {
        if (!phoneRepository.existsById(phoneId)) {
            throw new NotFoundException(String.format("Phone with id=%s not found", phoneId));
        }

        phoneRepository.deleteById(phoneId);
        log.debug("A phone with an id {} was deleted", phoneId);
    }
}
