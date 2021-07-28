package com.example.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"author_id", "post_id"})
})
@Getter
@Setter
@NoArgsConstructor
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @ManyToOne(optional = false)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    /**
     * 1 - like,
     * -1 - dislike
     */
    @Column(columnDefinition = "INTEGER NOT NULL CHECK (like in ('-1', '1')")
    private Integer like;
}
