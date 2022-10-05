package com.zinkworks.gradpetstore.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ErrorDTO {

    private String message;
    private Long errorCode;
}
