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

    @NotNull(message = "用户名不为空")
    private String name;

    @NotNull(message = "office不为空")
    private String office;

    @NotNull(message = "email不为空")
    @Email
    private String email;

    @NotNull(message = "github不为空")
    private String github;

    @NotNull(message = "zoomId不为空")
    private String zoomId;

    @JsonIgnore
    private Boolean grouped = false;
}
