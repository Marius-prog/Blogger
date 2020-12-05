package com.example.myBlog.service;


import com.example.myBlog.entity.Blog;
import com.example.myBlog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    public Blog save(Blog posten) {
        return blogRepository.save(posten);
    }

    public void deleteById(Long id) {
        blogRepository.deleteById(id);
    }

}
