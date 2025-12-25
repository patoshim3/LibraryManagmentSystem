package com.example.librarymanagmentsystem;

import com.example.librarymanagmentsystem.dto.AuthorDto;
import com.example.librarymanagmentsystem.mapper.AuthorMapper;
import com.example.librarymanagmentsystem.Model.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AuthorMapperTest {

    @Autowired
    private AuthorMapper authorMapper;

    @Test
    void convertEntityToDtoTest() {
        Author entityAuthor = new Author(1L, "Author1", "Biography of Author1");
        AuthorDto dtoAuthor = authorMapper.toDto(entityAuthor);

        Assertions.assertNotNull(dtoAuthor);
        Assertions.assertNotNull(dtoAuthor.getId());
        Assertions.assertNotNull(dtoAuthor.getName());
        Assertions.assertNotNull(dtoAuthor.getAuthorBiography());

        Assertions.assertEquals(entityAuthor.getId(), dtoAuthor.getId());
        Assertions.assertEquals(entityAuthor.getName(), dtoAuthor.getName());
        Assertions.assertEquals(entityAuthor.getAuthorBiography(), dtoAuthor.getAuthorBiography());
    }

    @Test
    void convertDtoToEntityTest() {
        AuthorDto authorDto = AuthorDto.builder()
                .id(1L)
                .name("Author1")
                .authorBiography("Biography of Author1")
                .build();

        Author author = authorMapper.toEntity(authorDto);

        Assertions.assertNotNull(author);
        Assertions.assertNotNull(author.getId());
        Assertions.assertNotNull(author.getName());
        Assertions.assertNotNull(author.getAuthorBiography());

        Assertions.assertEquals(authorDto.getId(), author.getId());
        Assertions.assertEquals(authorDto.getName(), author.getName());
        Assertions.assertEquals(authorDto.getAuthorBiography(), author.getAuthorBiography());
    }

    @Test
    void convertEntityListToDtoList() {
        List<Author> authorList = new ArrayList<>();
        authorList.add(new Author(1L, "Author1", "Biography of Author1"));
        authorList.add(new Author(2L, "Author2", "Biography of Author2"));
        authorList.add(new Author(3L, "Author3", "Biography of Author3"));

        List<AuthorDto> authorDtoList = authorMapper.toDtoList(authorList);

        Assertions.assertNotNull(authorDtoList);
        Assertions.assertNotEquals(0, authorDtoList.size());
        Assertions.assertEquals(authorList.size(), authorDtoList.size());

        for (int i = 0; i < authorList.size(); i++) {
            Author author = authorList.get(i);
            AuthorDto authorDto = authorMapper.toDto(author);

            Assertions.assertNotNull(authorDto);
            Assertions.assertNotNull(authorDto.getId());
            Assertions.assertNotNull(authorDto.getName());
            Assertions.assertNotNull(authorDto.getAuthorBiography());

            Assertions.assertEquals(author.getId(), authorDto.getId());
            Assertions.assertEquals(author.getName(), authorDto.getName());
            Assertions.assertEquals(author.getAuthorBiography(), authorDto.getAuthorBiography());
        }
    }
}