package com.example.es8noauth.service;

import com.example.es8noauth.dao.BookRepository;
import com.example.es8noauth.dto.AddBookReqDTO;
import com.example.es8noauth.dto.SearchBookRespDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    public void addBook(AddBookReqDTO esBook) {
        bookRepository.save(esBook);
    }

    public List<SearchBookRespDTO> search(String keyword) {
        return bookRepository.findByTitleOrAuthor(keyword, keyword);
    }

    public SearchHits<SearchBookRespDTO> searchBookTitle(String keyword) {
        SearchHits<SearchBookRespDTO> searchHits = bookRepository.find(keyword);
        return searchHits;
    }
}
