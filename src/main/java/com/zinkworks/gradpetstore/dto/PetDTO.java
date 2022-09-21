package com.zinkworks.gradpetstore.dto;

import com.zinkworks.gradpetstore.util.AnimalTypes;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PetDTO {
    private String name;
    private String description;
    private AnimalTypes type;
}
