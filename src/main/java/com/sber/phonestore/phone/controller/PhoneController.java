package com.sber.phonestore.phone.controller;

import com.sber.phonestore.phone.model.Phone;
import com.sber.phonestore.phone.service.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/phones")
@RequiredArgsConstructor
public class PhoneController {

    private final PhoneService phoneService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Phone create(@RequestBody @Valid Phone phone) {
        return phoneService.create(phone);
    }

    @GetMapping("/{phoneId}")
    public Phone getById(@PathVariable Long phoneId) {
        return phoneService.getById(phoneId);
    }

    @GetMapping
    public List<Phone> getAll() {
        return phoneService.getAll();
    }

    @PatchMapping("/{phoneId}")
    public Phone update(@PathVariable Long phoneId, @RequestBody @Valid Phone phone) {
        return phoneService.update(phoneId, phone);
    }

    @DeleteMapping("/{phoneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long phoneId) {
       phoneService.delete(phoneId);
    }
}

