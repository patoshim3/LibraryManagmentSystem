package com.example.librarymanagmentsystem.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_permission")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long id;

    @Column(name = "t_name", unique = true, nullable = false)
    private String name;
}