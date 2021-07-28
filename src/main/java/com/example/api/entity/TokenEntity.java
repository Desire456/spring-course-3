package com.example.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tokens")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TokenEntity {
    @Id
    private String token;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Column(insertable = false, updatable = false)
    private Instant created;
}
