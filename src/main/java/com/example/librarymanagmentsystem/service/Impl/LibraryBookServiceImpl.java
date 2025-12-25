package com.example.librarymanagmentsystem.service.Impl;

import com.example.librarymanagmentsystem.Model.LibraryBook;
import com.example.librarymanagmentsystem.dto.LibraryBookDto;
import com.example.librarymanagmentsystem.mapper.LibraryBookMapper;
import com.example.librarymanagmentsystem.repository.LibraryBookRepository;
import com.example.librarymanagmentsystem.service.LibraryBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryBookServiceImpl implements LibraryBookService {

    private final LibraryBookRepository libraryBookRepository;
    private final LibraryBookMapper libraryBookMapper;

    @Override
    public List<LibraryBookDto> getAll() {
        return libraryBookMapper.toDtoList(libraryBookRepository.findAll());
    }

    @Override
    public LibraryBookDto getById(Long id) {
        return libraryBookRepository.findById(id)
                .map(libraryBookMapper::toDto)
                .orElse(null);
    }

    @Override
    public void addLibraryBook(LibraryBookDto dto) {
        LibraryBook entity = libraryBookMapper.toEntity(dto);
        libraryBookRepository.save(entity);
    }

    @Override
    public void updateLibraryBook(Long id, LibraryBookDto dto) {
        LibraryBook existing = libraryBookRepository.findById(id).orElse(null);
        if (existing != null) {
            libraryBookMapper.updateEntityFromDto(dto, existing);
            libraryBookRepository.save(existing);
        }
    }

    @Override
    public boolean deleteLibraryBook(Long id) {
        if (libraryBookRepository.existsById(id)) {
            libraryBookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<LibraryBookDto> getBooksByLibrary(Long libraryId) {
        return libraryBookMapper.toDtoList(libraryBookRepository.findByLibraryId(libraryId));
    }

    @Override
    public List<LibraryBookDto> getLibrariesByBook(Long bookId) {
        return libraryBookMapper.toDtoList(libraryBookRepository.findByBookId(bookId));
    }
}