package com.example.myBlog.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
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

    private BigDecimal date;

//    @CreationTimestamp
//    private Date createdAt;
//
//    @UpdateTimestamp
//    private Date updatedAt;

    Instant time = Instant.now();

    Timestamp createdAt = Timestamp.from(time);
    Timestamp updatedAt = Timestamp.from(time);

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdAt;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date updatedAt;
}

//@Entity
//@Data
//public class Product {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//
//    private String description;
//
//    private BigDecimal price;
//
//    @CreationTimestamp
//    private Date createdAt;
//
//    @UpdateTimestamp
//    private Date updatedAt;
//}