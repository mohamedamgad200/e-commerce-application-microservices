package com.ecommerce.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ResoponseMessage {
    private String message;
    private LocalDateTime dateTime;
}
