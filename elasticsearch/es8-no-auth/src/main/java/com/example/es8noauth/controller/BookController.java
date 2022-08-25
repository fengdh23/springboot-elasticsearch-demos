package com.example.es8noauth.controller;

import com.example.es8noauth.dto.AddBookReqDTO;
import com.example.es8noauth.dto.Employee;
import com.example.es8noauth.dto.SearchBookRespDTO;
import com.example.es8noauth.service.BookService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class BookController {
    private BookService bookService;
    /**
     * 从ES中搜索
     */
    @GetMapping("/book/test")
    @ResponseBody
    public Employee test(String key) {
        Employee employee = new Employee();
        employee.setAge(12);
        return employee;
    }

    /**
     * 添加book，这里我直接使用了Entity，为了演示有点不规范！
     */
    @PostMapping("/addBook")
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
        SearchHits<SearchBookRespDTO> searchHits = bookService.searchBookTitle(key);
        return searchHits;
    }
}
