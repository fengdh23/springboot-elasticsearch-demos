package springdata.elasticsearch.controller;

import springdata.elasticsearch.dto.AddBookReqDTO;
import springdata.elasticsearch.dto.SearchBookRespDTO;
import springdata.elasticsearch.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    /**
     * 添加book，这里我直接使用了Entity，为了演示有点不规范！
     */
    @PostMapping("/book")
    public Map<String, String> addBook(@RequestBody AddBookReqDTO book) {
        bookService.addBook(book);
        Map<String, String> map = new HashMap<>();
        map.put("msg", "ok");
        return map;
    }

    /**
     * 从ES中搜索
     */
    @GetMapping("/book/search/es")
    public List<SearchBookRespDTO> searchES(String key) {
        return bookService.search(key);
    }

    @GetMapping("/book/search")
    public SearchHits<SearchBookRespDTO> search(String key) {
        return bookService.searchBookTitle(key);
    }

}
