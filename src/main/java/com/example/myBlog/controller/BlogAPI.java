package com.example.myBlog.controller;
import com.example.myBlog.entity.Blog;
import com.example.myBlog.service.BlogService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/blogs")
@Slf4j
@RequiredArgsConstructor
public class BlogAPI {

    @Autowired
    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<List<Blog>> findAll() {
        return ResponseEntity.ok(blogService.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Blog blog) {
        return ResponseEntity.ok(blogService.save(blog));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> findById(@PathVariable Long id) {
        Optional<Blog> posten = blogService.findById(id);
        if (!posten.isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(posten.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> update(@PathVariable Long id, @Valid @RequestBody Blog blog) {
        if (!blogService.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(blogService.save(blog));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!blogService.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        blogService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}