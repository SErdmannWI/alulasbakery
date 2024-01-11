package com.serdmannwi.practiceprograms.alulasbakery.controller;

import com.serdmannwi.practiceprograms.alulasbakery.controller.model.TestResponse;
import com.serdmannwi.practiceprograms.alulasbakery.exceptions.TestNotFoundException;
import com.serdmannwi.practiceprograms.alulasbakery.repository.TestRecord;
import com.serdmannwi.practiceprograms.alulasbakery.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) { this.testService = testService; }

    @GetMapping("/allTests")
    public ResponseEntity<List<TestResponse>> getAllTestRecords() {
        List<TestResponse> testResponses = testService.getAllTests().stream()
            .map(testRecord -> recordToResponse(testRecord)).toList();
        return ResponseEntity.ok(testResponses);
    }

    @GetMapping("/getTest/{id}")
    public ResponseEntity<TestResponse> getTestById(@PathVariable("id") String testId) {
        TestRecord testRecord = testService.getTestById(testId);
        if (testRecord == null) {
            throw new TestNotFoundException("Test could not be found with id: " + testId);
        }
        return ResponseEntity.ok(recordToResponse(testRecord));
    }

    @PostMapping("/newTest")
    public ResponseEntity<TestResponse> addNewTest(@RequestBody TestResponse testResponse) {
        TestRecord testRecord = testService.addTest(responseToRecord(testResponse));
        if (testRecord == null) {
            throw new TestNotFoundException("Error creating test: " + testResponse.getId() + ", " + testResponse.getName());
        }
        return ResponseEntity.ok(recordToResponse(testRecord));
    }

    /**----------------------------------------------- Helper Methods -----------------------------------------------**/

    public TestRecord responseToRecord(TestResponse testResponse) {
        return new TestRecord(testResponse.getId(), testResponse.getName());
    }

    public TestResponse recordToResponse(TestRecord testRecord) {
        return new TestResponse(testRecord.getId(), testRecord.getName());
    }
}
