package com.elios.customer_api.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
}
