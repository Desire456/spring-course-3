package com.example.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;
    private String content;
    @Column(insertable = false, updatable = false)
    private Instant created;
    private Instant updated;
    private Instant deleted;
    @ManyToOne
    @JoinColumn(name = "deleted_by")
    private UserEntity deletedBy;
    @OneToMany(mappedBy = "post")
    List<LikeEntity> likes;
}
