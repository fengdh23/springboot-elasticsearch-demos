package springdata.elasticsearch.service;

import springdata.elasticsearch.dao.BookRepository;
import springdata.elasticsearch.dto.AddBookReqDTO;
import springdata.elasticsearch.dto.SearchBookRespDTO;
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
        return bookRepository.find(keyword);
    }
}
