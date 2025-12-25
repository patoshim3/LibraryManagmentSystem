package com.example.librarymanagmentsystem.mapper;

import com.example.librarymanagmentsystem.Model.Library;
import com.example.librarymanagmentsystem.dto.LibraryDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LibraryMapper {
    LibraryDto toDto(Library library);
    Library toEntity(LibraryDto libraryDto);
    List<LibraryDto> toDtoList(List<Library> libraryList);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(LibraryDto dto, @MappingTarget Library library);

}

