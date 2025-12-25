package com.example.librarymanagmentsystem.mapper;

import com.example.librarymanagmentsystem.Model.Library;
import com.example.librarymanagmentsystem.dto.LibraryDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LibraryMapper {
    LibraryDto toDto(Library library);
    Library toEntity(LibraryDto libraryDto);
    List<LibraryDto> toDtoList(List<Library> libraryList);
}

