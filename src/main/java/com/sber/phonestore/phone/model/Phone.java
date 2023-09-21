package com.sber.phonestore.phone.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

/** Phone is the main entity of the application on which CRUD operations are performed */
@Entity
@Table(name = "phones")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Phone {

    /** Phone ID. Generated automatically when the phone is added to the database */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /** Phone brand name */
    @NotBlank(message = "brand cannot be blank")
    @Length(message = "brand: the string length should be from 1 to 50 characters", min = 1, max = 50)
    String brand;

    /** Phone series name */
    @NotBlank(message = "series cannot be blank")
    @Length(message = "series: the string length should be from 1 to 50 characters", min = 1, max = 50)
    String series;

    /** Phone model name */
    @NotBlank(message = "model cannot be blank")
    @Length(message = "model: the string length should be from 1 to 50 characters", min = 1, max = 50)
    String model;

    /** Phone color */
    @NotBlank(message = "color cannot be blank")
    @Length(message = "color: the string length should be from 1 to 50 characters", min = 1, max = 50)
    String color;

    /** Name of the phone's operating system */
    @NotBlank(message = "os cannot be blank")
    @Length(message = "os: the string length should be from 1 to 50 characters", min = 1, max = 50)
    String os;

    /** Processor model name */
    @Column(name = "processor_model")
    @NotBlank(message = "processorModel cannot be blank")
    @Length(message = "processorModel: the string length should be from 1 to 50 characters", min = 1, max = 50)
    String processorModel;

    /** Phone release date */
    @Column(name = "release_date")
    @NotNull(message = "releaseDate cannot be null")
    @Past(message = "releaseDate must be in the past")
    LocalDate releaseDate;

    /**
     * Builder implementation for creating Phone objects.
     * Must be defined to avoid a conflict between lombok and javadoc
     */
    public static class PhoneBuilder {}
}
