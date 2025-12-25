package com.example.librarymanagmentsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibraryBookDto {
    private Long id;

    private Long libraryId;
    private String libraryName;

    private Long bookId;
    private String bookTitle;

    private Integer quantity;
    private String shelfCode;

}
