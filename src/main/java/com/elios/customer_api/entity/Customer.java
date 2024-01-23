package com.elios.customer_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false)
    private Long created;
    private Long updated;
    @Size(min = 2, max = 50)
    private String fullName;
    @Size(min = 2, max = 100)
    @jakarta.validation.constraints.Email
    @Pattern(regexp = ".+@.+\\..+", message = "Email should include exactly one @")
    private String Email;
    @Size(min = 6, max = 14)
    @Pattern(regexp = "\\+[0-9]+", message = "Phone should start with + and contain only digits")
    private String phone;
    @Column(name = "is_active")
    private Boolean isActive;
}
