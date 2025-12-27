package com.example.librarymanagmentsystem.mapper;

import com.example.librarymanagmentsystem.Model.Book;
import com.example.librarymanagmentsystem.dto.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "authorName", source = "author.name")
    BookDto toDto(Book book);

    @Mapping(target = "author", ignore = true)
    Book toEntity(BookDto dto);

    List<BookDto> toDtoList(List<Book> books);

    void updateEntityFromDto(BookDto dto, @MappingTarget Book book);
}