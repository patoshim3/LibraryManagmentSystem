package com.example.librarymanagmentsystem.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long id;

    private String title;

    private String isbn;

    private Integer publicationYear;

    private Long authorId;
    private String authorName;
}
