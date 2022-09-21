package com.zinkworks.gradpetstore.model;

import com.zinkworks.gradpetstore.util.AnimalTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pets")
@Getter
@Setter
@Builder
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private AnimalTypes type;

    public Pet() {
    }

    public Pet(long id, String name, String description, AnimalTypes type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }
}
