package com.example.demo.Controller;

import com.example.demo.Models.Book;
import com.example.demo.Repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksController {

    @Autowired
    private BookRepo bookRepo;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/")
    public String getBook(){
        return "Welcome to the library database";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/books")
    public List<Book> getUsers(){
        return bookRepo.findAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/books/{id}")
    public Book getBook(@PathVariable long id){
        return bookRepo.findById(id).get();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/save")
    public String saveBook(@RequestBody Book book){
        bookRepo.save(book);
        return "Book added to the database!";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "update/{id}")
    public String updateBook(@PathVariable long id, @RequestBody Book book){
        Book updatedBook = bookRepo.findById(id).get();
        updatedBook.setTitle(book.getTitle());
        updatedBook.setAuthor(book.getAuthor());
        updatedBook.setGenre(book.getGenre());
        updatedBook.setCheckedOut(book.isCheckedOut());
        bookRepo.save(updatedBook);
        return "Book is updated!";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "/delete/{id}")
    public String deleteBook(@PathVariable long id){
        Book deleteBook = bookRepo.findById(id).get();
        bookRepo.delete(deleteBook);
        return "Book #" + id + " has been deleted!";
    }
}
