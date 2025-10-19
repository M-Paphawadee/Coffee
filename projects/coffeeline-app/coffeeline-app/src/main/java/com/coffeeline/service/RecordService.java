package com.coffeeline.service;

import com.coffeeline.model.Record;
import com.coffeeline.repository.RecordRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class RecordService {
    
    @Autowired
    private RecordRepository recordRepository;
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    public Record saveRecord(String type, String title, Map<String, Object> data) throws Exception {
        Record record = new Record();
        record.setType(type);
        record.setTitle(title);
        record.setDataJson(objectMapper.writeValueAsString(data));
        return recordRepository.save(record);
    }
}
