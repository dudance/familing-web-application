package com.project.project.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.project.mappers.IdInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CollectionType;
import org.springframework.data.util.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "APP_USER")
public class User implements Serializable, org.springframework.security.core.userdetails.UserDetails, IdInterface {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_DETAILS_ID", referencedColumnName = "ID")
    private UserDetails userDetailsId;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    @JsonIgnore
    private String password;
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<UserFamily> families;
    @Transient
    @JsonIgnore
    private Collection<GrantedAuthority> authorities;

    public User() {
    }


    public User(Long id) {
        this.id = id;
    }

    public User(String email, String password, Collection<? extends GrantedAuthority> authorities) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
