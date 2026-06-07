package com.sigith.feelink.dto;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseUserDTO {
    private String id;
    private String clerkId;
    private String username;
    private LocalDate birthday;
    private String phoneNumber;
    private String profileImageUrl;
}
