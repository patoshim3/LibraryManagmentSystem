package com.example.librarymanagmentsystem.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {

    private Long id;

    private String title;

    private String isbn;

    private Integer publicationYear;

    private Long authorId;
    private String authorName;
}
