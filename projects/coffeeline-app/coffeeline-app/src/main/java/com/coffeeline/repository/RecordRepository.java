package com.coffeeline.repository;

import com.coffeeline.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    
    // ค้นหาตาม type
    List<Record> findByType(String type);
    
    // ค้นหาตาม type เรียงตามวันที่ล่าสุด
    List<Record> findByTypeOrderByCreatedAtDesc(String type);
    
    // ค้นหาทั้งหมดเรียงตามวันที่ล่าสุด
    List<Record> findAllByOrderByCreatedAtDesc();
}