package com.sber.phonestore.phone.controller;

import com.sber.phonestore.phone.model.Phone;
import com.sber.phonestore.phone.service.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phones")
@RequiredArgsConstructor
public class PhoneController {
    private final PhoneService phoneService;

    @PostMapping
    public Phone create(@RequestBody Phone phone) {
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
    public Phone update(@PathVariable Long phoneId, @RequestBody Phone phone) {
        return phoneService.update(phoneId, phone);
    }

    @DeleteMapping("/{phoneId}")
    public void delete(@PathVariable Long phoneId) {
       phoneService.delete(phoneId);
    }
}

