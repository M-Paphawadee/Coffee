package com.coffeeline.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "records")
public class Record {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String type;
    
    @Column(nullable = false)
    private String title;
    
    @Column(name = "data_json", columnDefinition = "TEXT")
    private String dataJson;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Constructors
    public Record() {
    }
    
    public Record(String type, String title, String dataJson) {
        this.type = type;
        this.title = title;
        this.dataJson = dataJson;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDataJson() {
        return dataJson;
    }
    
    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}