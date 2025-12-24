package com.example.librarymanagmentsystem.service.Impl;


import com.example.librarymanagmentsystem.Model.Book;
import com.example.librarymanagmentsystem.dto.BookDto;
import com.example.librarymanagmentsystem.mapper.BookMapper;
import com.example.librarymanagmentsystem.repository.BookRepository;
import com.example.librarymanagmentsystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> getAll() {
        return bookMapper.toDtoList(bookRepository.findAll());
    }

    @Override
    public BookDto getById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        return book != null ? bookMapper.toDto(book) : null;
    }

    @Override
    public void addBook(BookDto dto) {
        Book book = bookMapper.toEntity(dto);
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Long id, BookDto dto) {
        Book existing = bookRepository.findById(id).orElse(null);
        if (existing != null) {
            bookMapper.updateEntityFromDto(dto, existing);
            bookRepository.save(existing);
        }
    }

    @Override
    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}