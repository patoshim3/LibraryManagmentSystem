package com.example.librarymanagmentsystem.service.Impl;

import com.example.librarymanagmentsystem.Model.Author;
import com.example.librarymanagmentsystem.Model.Book;
import com.example.librarymanagmentsystem.dto.BookDto;
import com.example.librarymanagmentsystem.mapper.BookMapper;
import com.example.librarymanagmentsystem.repository.AuthorRepository;
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
    private final AuthorRepository authorRepository;

    @Override
    public List<BookDto> getAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getBookById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id).orElse(null));
    }

    @Override
    public void addBook(BookDto dto) {
        Book book = bookMapper.toEntity(dto);
        if (dto.getAuthorId() != null) {
            Author author = authorRepository.findById(dto.getAuthorId())
                    .orElseThrow(() -> new IllegalArgumentException("Автор с id " + dto.getAuthorId() + " не найден"));
            book.setAuthor(author);
        } else {
            book.setAuthor(null);
        }

        bookRepository.save(book);
    }

    @Override
    public void updateBook(Long id, BookDto dto) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Книга с id " + id + " не найдена"));
        existing.setTitle(dto.getTitle());
        existing.setIsbn(dto.getIsbn());
        existing.setPublicationYear(dto.getPublicationYear());
        if (dto.getAuthorId() != null) {
            Author author = authorRepository.findById(dto.getAuthorId())
                    .orElseThrow(() -> new IllegalArgumentException("Автор с id " + dto.getAuthorId() + " не найден"));
            existing.setAuthor(author);
        } else {
            existing.setAuthor(null);
        }
        bookRepository.save(existing);
    }

    @Override
    public boolean deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            book.setAuthor(null);
            bookRepository.delete(book);
            return true;
        }
        return false;
    }
}