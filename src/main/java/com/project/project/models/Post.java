package com.project.project.models;

import com.project.project.mappers.IdInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Getter
@Setter
@Entity
@Table(name = "POST")
public class Post implements Serializable, IdInterface {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "ID")
    private User ownerId;
    @ManyToOne()
    @JoinColumn(name = "DISCUSSION_ID", referencedColumnName = "ID")
    private  Discussion discussionId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE")
    private  Date date;
    @Column(name = "CONTENT")
    private  String content;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                '}';
    }
}
