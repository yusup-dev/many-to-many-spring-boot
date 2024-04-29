package com.manytomany.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 25)
    private String name;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.PERSIST)
    private Set<UserEntity> users = new HashSet<>();

    public RoleEntity(String name) {
        super();
        this.name = name;
    }

    public RoleEntity(Long id) {
        super();
        this.id = id;
    }


    @Override
    public String toString() {
        return this.name;
    }
}
