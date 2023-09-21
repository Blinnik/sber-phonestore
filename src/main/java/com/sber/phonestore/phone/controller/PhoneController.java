package com.sber.phonestore.phone.controller;

import com.sber.phonestore.phone.model.Phone;
import com.sber.phonestore.phone.service.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * A class associated with the Phone entity
 * and responsible for routing and validating input data.
 * By default, it uses the address "/phones" for endpoints.
 */
@RestController
@RequestMapping("/phones")
@RequiredArgsConstructor
public class PhoneController {

    /** Interface for interacting with the Phone entity at the business logic level */
    private final PhoneService phoneService;

    /**
     * Creates a new phone. Validates the fields of the transmitted object
     *
     * @param phone Phone
     * @return Phone with assigned ID
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Phone create(@RequestBody @Valid Phone phone) {
        return phoneService.create(phone);
    }

    /**
     * Gets the phone by ID
     *
     * @param phoneId ID of the phone to be found
     * @return Requested phone
     * @throws com.sber.phonestore.exception.NotFoundException If the phone was not found
     */
    @GetMapping("/{phoneId}")
    public Phone getById(@PathVariable Long phoneId) {
        return phoneService.getById(phoneId);
    }

    /**
     * Gets all phones.
     * If there are no phones, it returns an empty list
     *
     * @return Phone list
     */
    @GetMapping
    public List<Phone> getAll() {
        return phoneService.getAll();
    }

    /**
     * Updates the phone. Validates the fields of the transmitted object
     *
     * @param phoneId ID of the phone that needs to be updated
     * @param phone   Phone with new data
     * @return Updated phone
     * @throws com.sber.phonestore.exception.NotFoundException If the phone was not found
     */
    @PatchMapping("/{phoneId}")
    public Phone update(@PathVariable Long phoneId, @RequestBody @Valid Phone phone) {
        return phoneService.update(phoneId, phone);
    }

    /**
     * Deletes the phone
     *
     * @param phoneId ID of the phone that needs to be deleted
     * @throws com.sber.phonestore.exception.NotFoundException If the phone was not found
     */
    @DeleteMapping("/{phoneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long phoneId) {
       phoneService.delete(phoneId);
    }
}

