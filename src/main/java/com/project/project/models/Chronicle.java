package com.project.project.models;

import com.project.project.mappers.IdInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Getter
@Setter
@Entity
@Table(name = "CHRONICLE")
public class Chronicle implements Serializable, IdInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @ManyToOne(optional = false)
    @JoinColumn(name = "FAMILY_ID", referencedColumnName = "ID")
    private Family familyId;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID")
    private User authorId;
    @Basic(optional = false)
    @Column(name = "DATE")
    private Date date;
    @Basic(optional = false)
    @Column(name = "CONTENT", columnDefinition = "TEXT")
    private String content;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "GALLERY_ID", referencedColumnName = "ID")
    private Gallery galleryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chronicle chronicle = (Chronicle) o;
        return id.equals(chronicle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Chronicle{" +
                "id=" + id +
                '}';
    }
}
