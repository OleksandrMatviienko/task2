package com.example.task2.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "articles", schema = "public")
@SQLDelete(sql = "UPDATE articles SET deleted = true WHERE id=?")
@Where(clause = "deleted = False")
@Data
@NoArgsConstructor
public class ArticleEntity{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, unique = true)
    private UUID id;

    private String name;
    private String text;
    private boolean deleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private UserEntity userEntity;

}
