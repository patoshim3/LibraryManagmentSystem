package com.example.librarymanagmentsystem.mapper;

import com.example.librarymanagmentsystem.dto.AuthorDto;
import com.example.librarymanagmentsystem.Model.Author;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper{
    AuthorDto toDto(Author author);
    Author toEntity(AuthorDto authorDto);
    List<AuthorDto> toDtoList(List<Author> authorList);

}

