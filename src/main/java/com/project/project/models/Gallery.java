package com.project.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.project.mappers.IdInterface;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "GALLERY")
public class Gallery implements Serializable, IdInterface {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "FAMILY_ID")
    private Family familyId;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "ID")
    private User ownerId;
    @NotNull(message = "name is required field")
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy = "galleryId")
    @JsonIgnore
    private Collection<File> photos;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gallery gallery = (Gallery) o;
        return id.equals(gallery.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Gallery{" +
                "id=" + id +
                '}';
    }
}
