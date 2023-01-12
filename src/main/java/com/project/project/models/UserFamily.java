package com.project.project.models;


import com.project.project.mappers.IdInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_FAMILY")
public class UserFamily implements Serializable, IdInterface {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private  Long id;
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private  User userId;
    @ManyToOne
    @JoinColumn(name = "FAMILY_ID", referencedColumnName = "ID")
    private Family familyId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFamily that = (UserFamily) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "UserFamily{" +
                "id=" + id +
                '}';
    }
}
