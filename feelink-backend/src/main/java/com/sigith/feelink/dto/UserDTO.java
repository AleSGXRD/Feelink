package com.sigith.feelink.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String clerkId;
    private String username;
    private LocalDate birthday;
    private String phoneNumber;
    private String profileImageUrl;
}
