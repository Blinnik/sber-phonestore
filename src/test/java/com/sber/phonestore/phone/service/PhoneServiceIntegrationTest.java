package com.sber.phonestore.phone.service;

import com.sber.phonestore.exception.NotFoundException;
import com.sber.phonestore.phone.model.Phone;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for PhoneService using a test database to work
 *
 * @see PhoneService
 */
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhoneServiceIntegrationTest {

    final PhoneService phoneService;

    final Phone.PhoneBuilder phoneBuilder = Phone.builder()
            .brand("Huawei")
            .series("Nova")
            .model("2i")
            .color("Black")
            .os("Android")
            .processorModel("HiSilicon Kirin 659")
            .releaseDate(LocalDate.of(2017, 7, 28));

    Phone phone;

    @Autowired
    public PhoneServiceIntegrationTest(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @BeforeEach
    void setUp() {
        phone = phoneBuilder.build();
    }

    @Test
    void create_whenIdIsNull_thenReturnPhoneWithId() {
        Phone actualPhone = phoneService.create(phone);

        assertNotNull(actualPhone.getId());
        assertEquals(phone.getOs(), actualPhone.getOs());
        assertEquals(phone.getModel(), actualPhone.getModel());
        assertEquals(phone.getColor(), actualPhone.getColor());
        assertEquals(phone.getSeries(), actualPhone.getSeries());
        assertEquals(phone.getReleaseDate(), actualPhone.getReleaseDate());
        assertEquals(phone.getBrand(), actualPhone.getBrand());
    }

    @Test
    void getById_whenPhoneIsFound_thenReturnPhone() {
        Phone createdPhone = phoneService.create(phone);
        Phone foundPhone = phoneService.getById(createdPhone.getId());

        assertEquals(createdPhone.getId(), foundPhone.getId());
        assertEquals(createdPhone.getOs(), foundPhone.getOs());
        assertEquals(createdPhone.getModel(), foundPhone.getModel());
        assertEquals(createdPhone.getColor(), foundPhone.getColor());
        assertEquals(createdPhone.getSeries(), foundPhone.getSeries());
        assertEquals(createdPhone.getReleaseDate(), foundPhone.getReleaseDate());
        assertEquals(createdPhone.getBrand(), foundPhone.getBrand());
    }

    @Test
    void getById_whenPhoneIsNotFound_thenReturnPhone() {
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> phoneService.getById(-1L));

        assertEquals(exception.getMessage(), "Phone with id=-1 not found");
    }

    @Test
    void getAll_whenPhonesCreated_thenReturnListOfPhones() {
        Phone createdPhone1 = phoneService.create(phone);
        Phone createdPhone2 = phoneService.create(phoneBuilder.color("Red").build());

        List<Phone> expectedPhones = List.of(createdPhone1, createdPhone2);
        List<Phone> actualPhones = phoneService.getAll();

        assertEquals(expectedPhones, actualPhones);
    }

    @Test
    void getAll_whenPhonesNotCreated_thenReturnEmptyList() {
        List<Phone> actualPhones = phoneService.getAll();

        assertEquals(Collections.emptyList(), actualPhones);
    }

    @Test
    void update_whenPhoneIsFound_thenReturnPhone() {
        Phone createdPhone = phoneService.create(phone);

        String newModel = "3";
        String newColor = "Yellow";
        Phone newPhone = phoneBuilder.model(newModel).color(newColor).build();

        Phone updatedPhone = phoneService.update(createdPhone.getId(), newPhone);

        assertEquals(createdPhone.getId(), updatedPhone.getId());
        assertEquals(newModel, updatedPhone.getModel());
        assertEquals(newColor, updatedPhone.getColor());
    }

    @Test
    void update_whenPhoneIsNotFound_thenThrowError() {
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> phoneService.update(-1L, phone));

        assertEquals(exception.getMessage(), "Phone with id=-1 not found");
    }

    @Test
    void delete_whenPhoneIsFound_thenDeletePhone() {
        Phone createdPhone = phoneService.create(phone);
        Long createdPhoneId = createdPhone.getId();

        phoneService.delete(createdPhoneId);

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> phoneService.getById(createdPhoneId));

        assertEquals(exception.getMessage(), "Phone with id=" + createdPhoneId + " not found");
    }

    @Test
    void delete_whenPhoneIsNotFound_thenThrowError() {
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> phoneService.delete(-1L));

        assertEquals(exception.getMessage(), "Phone with id=-1 not found");
    }
}
