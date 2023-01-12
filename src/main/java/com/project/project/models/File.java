package com.project.project.models;


import com.project.project.mappers.IdInterface;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "FILE")
public class File implements Serializable, IdInterface {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "GALLERY_ID", referencedColumnName = "ID")
    private Gallery galleryId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FAMILY_ID", referencedColumnName = "ID")
    private Family familyId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User userId;
    @Column(name = "FILE_URL")
    private String fileUrl;
    @Column(name = "FILE_TYPE")
    private String fileType;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return id.equals(file.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                '}';
    }
}
