package com.example.librarymanagmentsystem.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "library_books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "library_id", nullable = false)
    private Library library;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    private Integer quantity;
    private String shelfCode;
}