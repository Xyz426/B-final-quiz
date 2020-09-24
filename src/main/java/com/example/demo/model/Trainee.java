package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "name is not null!")
    private String name;

    @NotNull(message = "office is not null!")
    private String office;

    @NotNull(message = "email is not null!")
    @Email
    private String email;

    @NotNull(message = "github is not null!")
    private String github;

    @NotNull(message = "zoomId is not null!")
    private String zoomId;

    @JsonIgnore
    @Builder.Default
    private Boolean grouped = false;
}
