package com.example.librarymanagmentsystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String isbn;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = true)
    private Author author;
}