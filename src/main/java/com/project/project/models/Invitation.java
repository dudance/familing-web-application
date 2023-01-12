package com.project.project.models;

import com.project.project.mappers.IdInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "INVITATION")
public class Invitation implements Serializable, IdInterface {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @OneToOne()
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User userId;
    @OneToOne()
    @JoinColumn(name = "FAMILY_ID", referencedColumnName = "ID")
    private Family familyId;
    @NotNull(message = "type is required field")
    @Column(name = "TYPE")
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invitation that = (Invitation) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "Invitation{" +
                "id=" + id +
                ", userId=" + userId +
                ", familyId=" + familyId +
                ", type='" + type + '\'' +
                '}';
    }



}
