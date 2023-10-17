package com.zinkworks.gradpetstore.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "pets")
@Data
public class Pet {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "category", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @Column(name = "photoUrls")
    @ElementCollection(targetClass=String.class)
    private List<String> photoUrls;

    @Column(name = "tags")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Tag> tags;

    @Column(name = "status")
    private Status status;

    @Getter
    public enum Status{
        available,
        pending,
        sold

    }
}
