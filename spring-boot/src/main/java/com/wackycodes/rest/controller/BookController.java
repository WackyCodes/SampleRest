package com.wackycodes.rest.controller;

import com.wackycodes.rest.controller.errors.exception.BookIdMismatchException;
import com.wackycodes.rest.controller.errors.exception.BookNotFoundException;
import com.wackycodes.rest.entity.response.ResponseObject;
import com.wackycodes.rest.repo.BookRepository;
import com.wackycodes.rest.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public Iterable findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/title/{bookTitle}")
    public List findByTitle(@PathVariable String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
    }

//    @PostMapping("")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Book create(@RequestBody Book book) {
//        return bookRepository.save(book);
//    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject create(@Param( value = "id" ) long id, @RequestParam( "title" ) String title, @Param( value = "author" ) String author) {
        ResponseObject responseObject = new ResponseObject();
        if ( title == null || author == null ){
           responseObject.setResponseCode( 0 );
           responseObject.setResponseMessage("Title or Author is empty!");
           return responseObject;
        }
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        bookRepository.save(book);

        responseObject.setData( book );
        responseObject.setResponseCode( 1 );
        responseObject.setResponseMessage("Data has been updated Successfully!!");
        return responseObject;
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id)
                .orElseThrow( BookNotFoundException::new );
        bookRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (book.getId() != id) {
            throw new BookIdMismatchException("Book Id is not matched!!", new Throwable());
        }
        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        return bookRepository.save(book);
    }


}