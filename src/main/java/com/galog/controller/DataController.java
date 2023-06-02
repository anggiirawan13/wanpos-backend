package com.galog.controller;

import com.galog.dto.DefaultResponse;
import com.galog.dto.request.DataRequest;
import com.galog.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/galog/v1")
public class DataController {
    @Autowired
    private DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping
    public ResponseEntity<DefaultResponse> getDataByErrorCode(@RequestParam("code") String errorCode) {
        return dataService.getDataByErrorCode(errorCode);
    }

    @PostMapping
    public ResponseEntity<DefaultResponse> saveData(@RequestBody DataRequest req) {
        return dataService.saveData(req);
    }

}
