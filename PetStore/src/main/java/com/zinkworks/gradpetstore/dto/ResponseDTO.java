package com.zinkworks.gradpetstore.dto;

import com.zinkworks.gradpetstore.util.AnimalTypes;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ResponseDTO<T> {
    private T response;
}
