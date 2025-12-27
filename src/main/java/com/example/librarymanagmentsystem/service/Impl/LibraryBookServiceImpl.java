package com.example.librarymanagmentsystem.service.Impl;

import com.example.librarymanagmentsystem.Model.Book;
import com.example.librarymanagmentsystem.Model.Library;
import com.example.librarymanagmentsystem.Model.LibraryBook;
import com.example.librarymanagmentsystem.dto.LibraryBookDto;
import com.example.librarymanagmentsystem.mapper.LibraryBookMapper;
import com.example.librarymanagmentsystem.repository.BookRepository;
import com.example.librarymanagmentsystem.repository.LibraryBookRepository;
import com.example.librarymanagmentsystem.repository.LibraryRepository;
import com.example.librarymanagmentsystem.service.LibraryBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryBookServiceImpl implements LibraryBookService {

    private final LibraryBookRepository libraryBookRepository;
    private final LibraryBookMapper libraryBookMapper;
    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;

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
        LibraryBook entity = new LibraryBook(); // Лучше создать вручную

        // Загружаем реальные сущности из БД
        Library library = libraryRepository.findById(dto.getLibraryId())
                .orElseThrow(() -> new IllegalArgumentException("Библиотека с id " + dto.getLibraryId() + " не найдена"));

        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Книга с id " + dto.getBookId() + " не найдена"));

        entity.setLibrary(library);
        entity.setBook(book);
        entity.setQuantity(dto.getQuantity());
        entity.setShelfCode(dto.getShelfCode());

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