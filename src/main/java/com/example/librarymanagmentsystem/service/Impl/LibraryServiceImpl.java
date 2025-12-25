package com.example.librarymanagmentsystem.service.Impl;

import com.example.librarymanagmentsystem.Model.Library;
import com.example.librarymanagmentsystem.dto.LibraryDto;
import com.example.librarymanagmentsystem.mapper.LibraryMapper;
import com.example.librarymanagmentsystem.repository.LibraryRepository;
import com.example.librarymanagmentsystem.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;
    private final LibraryMapper libraryMapper;

    @Override
    public List<LibraryDto> getAll() {
        return libraryMapper.toDtoList(libraryRepository.findAll());
    }

    @Override
    public LibraryDto getById(Long id) {
        return libraryRepository.findById(id)
                .map(libraryMapper::toDto)
                .orElse(null);
    }

    @Override
    public void addLibrary(LibraryDto dto) {
        Library library = libraryMapper.toEntity(dto);
        libraryRepository.save(library);
    }

    @Override
    public void updateLibrary(Long id, LibraryDto dto) {
        Library existing = libraryRepository.findById(id).orElse(null);
        if (existing != null) {
            libraryMapper.updateEntityFromDto(dto, existing);
            libraryRepository.save(existing);
        }
    }

    @Override
    public boolean deleteLibrary(Long id) {
        if (libraryRepository.existsById(id)) {
            libraryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}