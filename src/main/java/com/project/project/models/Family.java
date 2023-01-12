package com.project.project.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.project.mappers.IdInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "FAMILY")
public class Family implements Serializable, IdInterface {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "ID")
    private User ownerId;
    @Column(name = "IMAGE")
    private String image;
    @Column(name = "DESCRIPTION")
    private  String description;
    @OneToMany(mappedBy = "familyId")
    @JsonIgnore
    private  Collection<UserFamily> users;
    @OneToMany(mappedBy = "familyId")
    @JsonIgnore
    private Collection<Chronicle> chronicles;
    @JsonIgnore
    @OneToMany(mappedBy = "familyId")
    private  Collection<Gallery> galleries;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return id.equals(family.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Family{" +
                "id=" + id +
                '}';
    }
}
