package com.sber.phonestore.phone.service;

import com.sber.phonestore.exception.NotFoundException;
import com.sber.phonestore.phone.model.Phone;
import com.sber.phonestore.phone.repository.PhoneRepository;
import com.sber.phonestore.phone.service.impl.PhoneServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class PhoneServiceImplTest {

    @Mock
    PhoneRepository phoneRepository;

    @InjectMocks
    PhoneServiceImpl phoneService;

    final Phone.PhoneBuilder phoneBuilder = Phone.builder()
            .brand("Huawei")
            .series("Nova")
            .model("2i")
            .color("Black")
            .os("Android")
            .processorModel("HiSilicon Kirin 659")
            .releaseDate(LocalDate.of(2017, 7, 28));

    Phone phone;

    @BeforeEach
    void setUp() {
        phone = phoneBuilder.build();
    }

    @Test
    void create_whenIdIsNull_thenReturnPhoneWithId() {
        when(phoneRepository.save(any(Phone.class))).thenReturn(phoneBuilder.id(1L).build());

        Phone actualPhone = phoneService.create(phone);
        Phone expectedPhone = phoneBuilder.id(1L).build();

        assertEquals(expectedPhone, actualPhone);
        verify(phoneRepository, times(1)).save(any(Phone.class));
        verify(phoneRepository, only()).save(any(Phone.class));
    }

    @Test
    void create_whenIdIsNotNull_thenReturnPhoneWithNewId() {
        phone.setId(-1L);
        when(phoneRepository.save(any(Phone.class))).thenReturn(phoneBuilder.id(1L).build());

        Phone actualPhone = phoneService.create(phone);
        Phone expectedPhone = phoneBuilder.id(1L).build();

        assertEquals(expectedPhone, actualPhone);
        verify(phoneRepository, times(1)).save(any(Phone.class));
        verify(phoneRepository, only()).save(any(Phone.class));
    }

    @Test
    void getById_whenPhoneIsFound_thenReturnPhone() {
        when(phoneRepository.findById(anyLong())).thenReturn(Optional.ofNullable(phoneBuilder.id(1L).build()));

        Phone actualPhone = phoneService.getById(1L);
        Phone expectedPhone = phoneBuilder.id(1L).build();

        assertEquals(expectedPhone, actualPhone);
        verify(phoneRepository, times(1)).findById(anyLong());
        verify(phoneRepository, only()).findById(anyLong());
    }

    @Test
    void getById_whenPhoneIsNotFound_thenReturnPhone() {
        when(phoneRepository.findById(anyLong())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> phoneService.getById(1L));

        assertEquals(exception.getMessage(), "Phone with id=1 not found");
        verify(phoneRepository, times(1)).findById(anyLong());
        verify(phoneRepository, only()).findById(anyLong());
    }

    @Test
    void getAll_whenPhonesCreated_thenReturnListOfPhones() {
        List<Phone> expectedPhones = List.of(
                phoneBuilder.id(1L).build(),
                phoneBuilder.id(2L).color("Red").build()
        );

        when(phoneRepository.findAll()).thenReturn(expectedPhones);

        List<Phone> actualPhones = phoneService.getAll();

        assertEquals(expectedPhones, actualPhones);
        verify(phoneRepository, times(1)).findAll();
        verify(phoneRepository, only()).findAll();
    }

    @Test
    void getAll_whenPhonesNotCreated_thenReturnEmptyList() {
        List<Phone> expectedPhones = Collections.emptyList();

        when(phoneRepository.findAll()).thenReturn(expectedPhones);

        List<Phone> actualPhones = phoneService.getAll();

        assertEquals(expectedPhones, actualPhones);
        verify(phoneRepository, times(1)).findAll();
        verify(phoneRepository, only()).findAll();
    }

    @Test
    void update_whenPhoneIsFound_thenReturnPhone() {
        when(phoneRepository.existsById(anyLong())).thenReturn(true);
        when(phoneRepository.save(any(Phone.class))).thenReturn(phoneBuilder.id(1L).build());

        Phone actualPhone = phoneService.update(1L, phone);
        Phone expectedPhone = phoneBuilder.id(1L).build();

        assertEquals(expectedPhone, actualPhone);
        verify(phoneRepository, times(1)).existsById(anyLong());
        verify(phoneRepository, times(1)).save(any(Phone.class));
    }

    @Test
    void update_whenPhoneIsNotFound_thenThrowError() {
        when(phoneRepository.existsById(anyLong())).thenReturn(false);

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> phoneService.update(1L, phone));

        assertEquals(exception.getMessage(), "Phone with id=1 not found");
        verify(phoneRepository, times(1)).existsById(anyLong());
        verify(phoneRepository, only()).existsById(anyLong());
    }

    @Test
    void delete_whenPhoneIsFound_thenDeletePhone() {
        when(phoneRepository.existsById(1L)).thenReturn(true);

        phoneService.delete(1L);

        verify(phoneRepository, times(1)).existsById(1L);
        verify(phoneRepository, times(1)).deleteById(1L);
    }

    @Test
    void delete_whenPhoneIsNotFound_thenThrowError() {
        when(phoneRepository.existsById(1L)).thenReturn(false);

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> phoneService.delete(1L));

        assertEquals(exception.getMessage(), "Phone with id=1 not found");
        verify(phoneRepository, times(1)).existsById(1L);
        verify(phoneRepository, only()).existsById(1L);
    }
}