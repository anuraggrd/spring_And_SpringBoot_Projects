package com.ecom.user.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import java.lang.annotation.Documented;
import java.time.LocalDateTime;


@Data
@Document(collection = "user")
public class User {
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private String phoneNum;
    private UserRole role=UserRole.CUSTOMER;
    private Address address;
 @CreatedDate
    private LocalDateTime createdDtm;
    @LastModifiedDate
    private LocalDateTime updatedDtm;

}
