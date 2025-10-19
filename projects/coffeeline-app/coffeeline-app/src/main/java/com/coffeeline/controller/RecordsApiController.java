package com.coffeeline.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffeeline.model.Record;
import com.coffeeline.repository.RecordRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class RecordsApiController {
    
    private static final Logger logger = LoggerFactory.getLogger(RecordsApiController.class);
    
    @Autowired
    private RecordRepository recordRepository;
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * API สำหรับบันทึกข้อมูล
     * POST /api/records
     */
    @PostMapping("/records")
    public Map<String, Object> saveRecord(@RequestBody Map<String, Object> payload) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // ตรวจสอบข้อมูล
            String type = (String) payload.get("type");
            String title = (String) payload.get("title");
            @SuppressWarnings("unchecked")
            Map<String, Object> data = (Map<String, Object>) payload.get("data");
            
            if (type == null || data == null) {
                response.put("success", false);
                response.put("message", "ข้อมูลไม่ครบถ้วน");
                return response;
            }
            
            // แปลง data เป็น JSON string
            String dataJson = objectMapper.writeValueAsString(data);
            
            // สร้าง Record entity
            Record record = new Record(type, title, dataJson);
            
            // บันทึกลง Database
            Record savedRecord = recordRepository.save(record);
            
            // Log ข้อมูลที่บันทึก (ใช้ Logger แทน System.out.println)
            logger.info("บันทึกข้อมูลสำเร็จ - ID: {}, Type: {}, Title: {}", 
                       savedRecord.getId(), type, title);
            
            // ส่ง response กลับ
            response.put("success", true);
            response.put("message", "บันทึกข้อมูลสำเร็จ");
            response.put("id", savedRecord.getId());
            response.put("timestamp", savedRecord.getCreatedAt().toString());
            
        } catch (Exception e) {
            logger.error("เกิดข้อผิดพลาดในการบันทึกข้อมูล: {}", e.getMessage(), e);
            response.put("success", false);
            response.put("message", "เกิดข้อผิดพลาด: " + e.getMessage());
        }
        
        return response;
    }
    
    /**
     * API สำหรับดึงข้อมูลทั้งหมด
     * GET /api/records
     */
    @GetMapping("/records")
    public Map<String, Object> getAllRecords() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<Record> records = recordRepository.findAllByOrderByCreatedAtDesc();
            
            // แปลง Record entities เป็น Map
            List<Map<String, Object>> recordMaps = records.stream().map(record -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", record.getId());
                map.put("type", record.getType());
                map.put("title", record.getTitle());
                map.put("timestamp", record.getCreatedAt().toString());
                
                // แปลง JSON string กลับเป็น Map
                try {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> data = objectMapper.readValue(record.getDataJson(), Map.class);
                    map.put("data", data);
                } catch (Exception e) {
                    map.put("data", new HashMap<>());
                }
                
                return map;
            }).collect(Collectors.toList());
            
            response.put("success", true);
            response.put("total", recordMaps.size());
            response.put("records", recordMaps);
            
        } catch (Exception e) {
            logger.error("เกิดข้อผิดพลาดในการดึงข้อมูลทั้งหมด: {}", e.getMessage(), e);
            response.put("success", false);
            response.put("message", "เกิดข้อผิดพลาด: " + e.getMessage());
        }
        
        return response;
    }
    
    /**
     * API สำหรับดึงข้อมูลตาม type
     * GET /api/records/{type}
     */
    @GetMapping("/records/{type}")
    public Map<String, Object> getRecordsByType(@PathVariable String type) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<Record> records = recordRepository.findByTypeOrderByCreatedAtDesc(type);
            
            // แปลง Record entities เป็น Map
            List<Map<String, Object>> recordMaps = records.stream().map(record -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", record.getId());
                map.put("type", record.getType());
                map.put("title", record.getTitle());
                map.put("timestamp", record.getCreatedAt().toString());
                
                try {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> data = objectMapper.readValue(record.getDataJson(), Map.class);
                    map.put("data", data);
                } catch (Exception e) {
                    map.put("data", new HashMap<>());
                }
                
                return map;
            }).collect(Collectors.toList());
            
            response.put("success", true);
            response.put("type", type);
            response.put("total", recordMaps.size());
            response.put("records", recordMaps);
            
        } catch (Exception e) {
            logger.error("เกิดข้อผิดพลาดในการดึงข้อมูลตาม type: {}", e.getMessage(), e);
            response.put("success", false);
            response.put("message", "เกิดข้อผิดพลาด: " + e.getMessage());
        }
        
        return response;
    }
    
    /**
     * API สำหรับลบข้อมูลตาม ID
     * DELETE /api/records/{id}
     */
    @DeleteMapping("/records/{id}")
    public Map<String, Object> deleteRecord(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            if (recordRepository.existsById(id)) {
                recordRepository.deleteById(id);
                logger.info("ลบข้อมูลสำเร็จ - ID: {}", id);
                response.put("success", true);
                response.put("message", "ลบข้อมูลสำเร็จ");
            } else {
                response.put("success", false);
                response.put("message", "ไม่พบข้อมูล");
            }
        } catch (Exception e) {
            logger.error("เกิดข้อผิดพลาดในการลบข้อมูล ID {}: {}", id, e.getMessage(), e);
            response.put("success", false);
            response.put("message", "เกิดข้อผิดพลาด: " + e.getMessage());
        }
        
        return response;
    }
    
    /**
     * API สำหรับลบข้อมูลทั้งหมด (ใช้ในการทดสอบ)
     * POST /api/records/clear
     */
    @PostMapping("/records/clear")
    public Map<String, Object> clearAllRecords() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            long count = recordRepository.count();
            recordRepository.deleteAll();
            
            logger.info("ลบข้อมูลทั้งหมดสำเร็จ - จำนวน {} รายการ", count);
            response.put("success", true);
            response.put("message", "ลบข้อมูล " + count + " รายการสำเร็จ");
        } catch (Exception e) {
            logger.error("เกิดข้อผิดพลาดในการลบข้อมูลทั้งหมด: {}", e.getMessage(), e);
            response.put("success", false);
            response.put("message", "เกิดข้อผิดพลาด: " + e.getMessage());
        }
        
        return response;
    }
    
    /**
     * API สำหรับนับจำนวนข้อมูล
     * GET /api/records/count
     */
    @GetMapping("/records/count")
    public Map<String, Object> countRecords() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            long total = recordRepository.count();
            
            response.put("success", true);
            response.put("total", total);
        } catch (Exception e) {
            logger.error("เกิดข้อผิดพลาดในการนับจำนวนข้อมูล: {}", e.getMessage(), e);
            response.put("success", false);
            response.put("message", "เกิดข้อผิดพลาด: " + e.getMessage());
        }
        
        return response;
    }
}