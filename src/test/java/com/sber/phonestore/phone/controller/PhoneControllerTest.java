package com.sber.phonestore.phone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sber.phonestore.exception.NotFoundException;
import com.sber.phonestore.phone.model.Phone;
import com.sber.phonestore.phone.service.PhoneService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PhoneController.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class PhoneControllerTest {

    @Autowired
    ObjectMapper mapper;

    @MockBean
    PhoneService phoneService;

    @Autowired
    MockMvc mvc;

    static final String URL = "/phones";

    static final String PATH_VARIABLE_URL = "/phones/1";

    static final String PHONE_NOT_FOUND_ERROR = "Phone with id=1 not found";

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
    void create_whenFieldsCorrect_thenReturnPhoneWithIdAndStatusCreated() throws Exception {
        when(phoneService.create(phone))
                .thenReturn(phoneBuilder.id(1L).build());

        mvc.perform(post(URL)
                        .content(mapper.writeValueAsString(phone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1L), Long.class))
                .andExpect(jsonPath("$.brand", is(phone.getBrand())))
                .andExpect(jsonPath("$.series", is(phone.getSeries())))
                .andExpect(jsonPath("$.model", is(phone.getModel())))
                .andExpect(jsonPath("$.color", is(phone.getColor())))
                .andExpect(jsonPath("$.os", is(phone.getOs())))
                .andExpect(jsonPath("$.processorModel", is(phone.getProcessorModel())))
                .andExpect(jsonPath("$.releaseDate", is(phone.getReleaseDate().toString())));
    }

    @Test
    void create_whenBrandBlank_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.brand("").build();

        mvc.perform(post(URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_whenBrandIsMoreThan50Chars_thenThrowErrorAndStatusBadRequest() throws Exception {
        String badBrand = new String(new char[51]).replace('\0', 'N');
        Phone incorrectPhone = phoneBuilder.brand(badBrand).build();

        mvc.perform(post(URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_whenBrandIsNull_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.brand(null).build();

        mvc.perform(post(URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_whenSeriesBlank_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.series("").build();

        mvc.perform(post(URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_whenSeriesIsMoreThan50Chars_thenThrowErrorAndStatusBadRequest() throws Exception {
        String badSeries = new String(new char[51]).replace('\0', 'N');
        Phone incorrectPhone = phoneBuilder.series(badSeries).build();

        mvc.perform(post(URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_whenSeriesIsNull_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.series(null).build();

        mvc.perform(post(URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_whenModelBlank_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.model("").build();

        mvc.perform(post(URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_whenModelIsMoreThan50Chars_thenThrowErrorAndStatusBadRequest() throws Exception {
        String badModel = new String(new char[51]).replace('\0', 'N');
        Phone incorrectPhone = phoneBuilder.model(badModel).build();

        mvc.perform(post(URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_whenModelIsNull_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.model(null).build();

        mvc.perform(post(URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_whenColorBlank_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.color("").build();

        mvc.perform(post(URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_whenColorIsMoreThan50Chars_thenThrowErrorAndStatusBadRequest() throws Exception {
        String badColor = new String(new char[51]).replace('\0', 'N');
        Phone incorrectPhone = phoneBuilder.color(badColor).build();

        mvc.perform(post(URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_whenColorIsNull_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.color(null).build();

        mvc.perform(post(URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_whenOsBlank_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.os("").build();

        mvc.perform(post(URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_whenOsIsMoreThan50Chars_thenThrowErrorAndStatusBadRequest() throws Exception {
        String badOs = new String(new char[51]).replace('\0', 'N');
        Phone incorrectPhone = phoneBuilder.os(badOs).build();

        mvc.perform(post(URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_whenOsIsNull_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.os(null).build();

        mvc.perform(post(URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_whenProcessorModelBlank_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.processorModel("").build();

        mvc.perform(post(URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_whenProcessorModelIsMoreThan50Chars_thenThrowErrorAndStatusBadRequest() throws Exception {
        String badProcessorModel = new String(new char[51]).replace('\0', 'N');
        Phone incorrectPhone = phoneBuilder.processorModel(badProcessorModel).build();

        mvc.perform(post(URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_whenProcessorModelIsNull_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.processorModel(null).build();

        mvc.perform(post(URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getById_whenPhoneIsFound_thenReturnPhoneAndStatusOk() throws Exception {
        when(phoneService.getById(1L))
                .thenReturn(phoneBuilder.id(1L).build());

        mvc.perform(get(PATH_VARIABLE_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1L), Long.class))
                .andExpect(jsonPath("$.brand", is(phone.getBrand())))
                .andExpect(jsonPath("$.series", is(phone.getSeries())))
                .andExpect(jsonPath("$.model", is(phone.getModel())))
                .andExpect(jsonPath("$.color", is(phone.getColor())))
                .andExpect(jsonPath("$.os", is(phone.getOs())))
                .andExpect(jsonPath("$.processorModel", is(phone.getProcessorModel())))
                .andExpect(jsonPath("$.releaseDate", is(phone.getReleaseDate().toString())));
    }

    @Test
    void getById_whenPhoneIsNotFound_thenThrowErrorAndStatusNotFound() throws Exception {
        when(phoneService.getById(1L))
                .thenThrow(new NotFoundException(PHONE_NOT_FOUND_ERROR));

        mvc.perform(get(PATH_VARIABLE_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAll_whenPhonesCreated_thenReturnListOfPhonesAndStatusOk() throws Exception {
        Phone phone1 = phoneBuilder.id(1L).build();
        Phone phone2 = phoneBuilder.id(2L).color("Red").build();

        when(phoneService.getAll())
                .thenReturn(List.of(phone1, phone2));

        mvc.perform(get(URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(phone1.getId()), Long.class))
                .andExpect(jsonPath("$[0].brand", is(phone1.getBrand())))
                .andExpect(jsonPath("$[0].series", is(phone1.getSeries())))
                .andExpect(jsonPath("$[0].model", is(phone1.getModel())))
                .andExpect(jsonPath("$[0].color", is(phone1.getColor())))
                .andExpect(jsonPath("$[0].os", is(phone1.getOs())))
                .andExpect(jsonPath("$[0].processorModel", is(phone1.getProcessorModel())))
                .andExpect(jsonPath("$[0].releaseDate", is(phone1.getReleaseDate().toString())))
                .andExpect(jsonPath("$[1].id", is(phone2.getId()), Long.class))
                .andExpect(jsonPath("$[1].brand", is(phone2.getBrand())))
                .andExpect(jsonPath("$[1].series", is(phone2.getSeries())))
                .andExpect(jsonPath("$[1].model", is(phone2.getModel())))
                .andExpect(jsonPath("$[1].color", is(phone2.getColor())))
                .andExpect(jsonPath("$[1].os", is(phone2.getOs())))
                .andExpect(jsonPath("$[1].processorModel", is(phone2.getProcessorModel())))
                .andExpect(jsonPath("$[1].releaseDate", is(phone2.getReleaseDate().toString())));
    }

    @Test
    void getAll_whenPhonesNotCreated_thenReturnEmptyListAndStatusOk() throws Exception {
        when(phoneService.getAll())
                .thenReturn(List.of());

        mvc.perform(get(URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(0)));
    }

    @Test
    void update_whenFieldsCorrect_thenReturnPhoneWithIdAndStatusOk() throws Exception {
        when(phoneService.update(1L, phone))
                .thenReturn(phoneBuilder.id(1L).build());

        mvc.perform(patch(PATH_VARIABLE_URL)
                        .content(mapper.writeValueAsString(phone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1L), Long.class))
                .andExpect(jsonPath("$.brand", is(phone.getBrand())))
                .andExpect(jsonPath("$.series", is(phone.getSeries())))
                .andExpect(jsonPath("$.model", is(phone.getModel())))
                .andExpect(jsonPath("$.color", is(phone.getColor())))
                .andExpect(jsonPath("$.os", is(phone.getOs())))
                .andExpect(jsonPath("$.processorModel", is(phone.getProcessorModel())))
                .andExpect(jsonPath("$.releaseDate", is(phone.getReleaseDate().toString())));
    }

    @Test
    void update_whenPhoneNotFound_thenThrowErrorAndStatusNotFound() throws Exception {
        when(phoneService.update(1L, phone))
                .thenThrow(new NotFoundException(PHONE_NOT_FOUND_ERROR));

        mvc.perform(patch(PATH_VARIABLE_URL)
                        .content(mapper.writeValueAsString(phone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void update_whenBrandBlank_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.brand("").build();

        mvc.perform(patch(PATH_VARIABLE_URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_whenBrandIsMoreThan50Chars_thenThrowErrorAndStatusBadRequest() throws Exception {
        String badBrand = new String(new char[51]).replace('\0', 'N');
        Phone incorrectPhone = phoneBuilder.brand(badBrand).build();

        mvc.perform(patch(PATH_VARIABLE_URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_whenBrandIsNull_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.brand(null).build();

        mvc.perform(patch(PATH_VARIABLE_URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_whenSeriesBlank_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.series("").build();

        mvc.perform(patch(PATH_VARIABLE_URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_whenSeriesIsMoreThan50Chars_thenThrowErrorAndStatusBadRequest() throws Exception {
        String badSeries = new String(new char[51]).replace('\0', 'N');
        Phone incorrectPhone = phoneBuilder.series(badSeries).build();

        mvc.perform(patch(PATH_VARIABLE_URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_whenSeriesIsNull_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.series(null).build();

        mvc.perform(patch(PATH_VARIABLE_URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_whenModelBlank_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.model("").build();

        mvc.perform(patch(PATH_VARIABLE_URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_whenModelIsMoreThan50Chars_thenThrowErrorAndStatusBadRequest() throws Exception {
        String badModel = new String(new char[51]).replace('\0', 'N');
        Phone incorrectPhone = phoneBuilder.model(badModel).build();

        mvc.perform(patch(PATH_VARIABLE_URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_whenModelIsNull_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.model(null).build();

        mvc.perform(patch(PATH_VARIABLE_URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_whenColorBlank_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.color("").build();

        mvc.perform(patch(PATH_VARIABLE_URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_whenColorIsMoreThan50Chars_thenThrowErrorAndStatusBadRequest() throws Exception {
        String badColor = new String(new char[51]).replace('\0', 'N');
        Phone incorrectPhone = phoneBuilder.color(badColor).build();

        mvc.perform(patch(PATH_VARIABLE_URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_whenColorIsNull_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.color(null).build();

        mvc.perform(patch(PATH_VARIABLE_URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_whenOsBlank_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.os("").build();

        mvc.perform(patch(PATH_VARIABLE_URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_whenOsIsMoreThan50Chars_thenThrowErrorAndStatusBadRequest() throws Exception {
        String badOs = new String(new char[51]).replace('\0', 'N');
        Phone incorrectPhone = phoneBuilder.os(badOs).build();

        mvc.perform(patch(PATH_VARIABLE_URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_whenOsIsNull_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.os(null).build();

        mvc.perform(patch(PATH_VARIABLE_URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_whenProcessorModelBlank_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.processorModel("").build();

        mvc.perform(patch(PATH_VARIABLE_URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_whenProcessorModelIsMoreThan50Chars_thenThrowErrorAndStatusBadRequest() throws Exception {
        String badProcessorModel = new String(new char[51]).replace('\0', 'N');
        Phone incorrectPhone = phoneBuilder.processorModel(badProcessorModel).build();

        mvc.perform(patch(PATH_VARIABLE_URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_whenProcessorModelIsNull_thenThrowErrorAndStatusBadRequest() throws Exception {
        Phone incorrectPhone = phoneBuilder.processorModel(null).build();

        mvc.perform(patch(PATH_VARIABLE_URL)
                        .content(mapper.writeValueAsString(incorrectPhone))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void delete_whenPhoneIsFound_thenStatusNoContent() throws Exception {
        mvc.perform(delete(PATH_VARIABLE_URL)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_whenPhoneIsNotFound_thenThrowErrorAndStatusNotFound() throws Exception {
        doThrow(new NotFoundException(PHONE_NOT_FOUND_ERROR)).when(phoneService).delete(1L);

        mvc.perform(delete(PATH_VARIABLE_URL)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}