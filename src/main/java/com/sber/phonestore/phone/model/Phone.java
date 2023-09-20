package com.sber.phonestore.phone.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;

@Entity
@Table(name = "phones")
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "brand cannot be blank")
    @Length(message = "brand: the string length should be from 1 to 50 characters", min = 1, max = 50)
    String brand;

    @NotBlank(message = "series cannot be blank")
    @Length(message = "series: the string length should be from 1 to 50 characters", min = 1, max = 50)
    String series;

    @NotBlank(message = "model cannot be blank")
    @Length(message = "model: the string length should be from 1 to 50 characters", min = 1, max = 50)
    String model;

    @NotBlank(message = "color cannot be blank")
    @Length(message = "color: the string length should be from 1 to 50 characters", min = 1, max = 50)
    String color;

    @NotBlank(message = "os cannot be blank")
    @Length(message = "os: the string length should be from 1 to 50 characters", min = 1, max = 50)
    String os;

    @Column(name = "processor_model")
    @NotBlank(message = "processorModel cannot be blank")
    @Length(message = "processorModel: the string length should be from 1 to 50 characters", min = 1, max = 50)
    String processorModel;

    @Column(name = "release_date")
    @NotNull(message = "releaseDate cannot be blank")
    @Past(message = "releaseDate must be in the past")
    LocalDateTime releaseDate;
}
