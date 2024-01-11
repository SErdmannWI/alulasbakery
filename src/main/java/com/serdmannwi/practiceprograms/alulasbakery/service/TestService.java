package com.serdmannwi.practiceprograms.alulasbakery.service;

import com.serdmannwi.practiceprograms.alulasbakery.repository.TestRecord;
import com.serdmannwi.practiceprograms.alulasbakery.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) { this.testRepository = testRepository; }

    public List<TestRecord> getAllTests() { return testRepository.findAll(); }

    public TestRecord getTestById(String id) { return testRepository.findById(id).orElse(null); }

    public TestRecord addTest(TestRecord testRecord) {
        return testRepository.save(new TestRecord(testRecord.getId(), testRecord.getName()));
    }
}
