package com.example.myBlog.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Entity
@Data
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

//    private BigDecimal price;

    @CreationTimestamp
    private Date createdAt;

//    @UpdateTimestamp
//    Instant updatedAt = Instant.now();

    @UpdateTimestamp
    Instant updatedAt = Instant.now();

}