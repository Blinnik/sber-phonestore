package com.sber.phonestore.phone.service;

import com.sber.phonestore.phone.model.Phone;

import java.util.List;

/**
 * Interface defining the functionality of the business layer
 * of the application for working with the Phone entity
 */
public interface PhoneService {

    /**
     * Creates a new phone, regardless of the transmitted ID
     *
     * @param phone Phone without a specified ID
     * @return Phone with assigned ID
     */
    Phone create(Phone phone);

    /**
     * Gets the phone by ID
     *
     * @param phoneId ID of the phone to be found
     * @return Requested phone
     * @throws com.sber.phonestore.exception.NotFoundException If the phone was not found
     */
    Phone getById(Long phoneId);

    /**
     * Gets all phones.
     * If there are no phones, it returns an empty list
     *
     * @return Phone list
     */
    List<Phone> getAll();

    /**
     * Updates the phone
     *
     * @param phoneId ID of the phone that needs to be updated
     * @param phone   Phone with new data
     * @return Updated phone
     * @throws com.sber.phonestore.exception.NotFoundException If the phone was not found
     */
    Phone update(Long phoneId, Phone phone);

    /**
     * Deletes the phone
     *
     * @param phoneId ID of the phone that needs to be deleted
     * @throws com.sber.phonestore.exception.NotFoundException If the phone was not found
     */
    void delete(Long phoneId);
}
