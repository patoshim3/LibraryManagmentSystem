package com.example.librarymanagmentsystem.mapper;

import com.example.librarymanagmentsystem.dto.LibraryBookDto;
import com.example.librarymanagmentsystem.Model.LibraryBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LibraryBookMapper {

    @Mapping(source = "library.id", target = "libraryId")
    @Mapping(source = "library.name", target = "libraryName")
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    LibraryBookDto toDto(LibraryBook libraryBook);

    @Mapping(target = "library", ignore = true)
    @Mapping(target = "book", ignore = true)
    LibraryBook toEntity(LibraryBookDto dto);

    List<LibraryBookDto> toDtoList(List<LibraryBook> libraryBooks);
}
