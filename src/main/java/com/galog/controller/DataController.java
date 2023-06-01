package com.galog.controller;

import com.galog.dto.DefaultResponse;
import com.galog.dto.in.DataDTOIn;
import com.galog.dto.out.DataDTOOut;
import com.galog.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/galog/v1")
public class DataController {
    @Autowired
    private DataService dataService;

    @GetMapping
    public DefaultResponse getData(@RequestParam("code") String errCode) {
        return dataService.getData(errCode);
    }

    @PostMapping
    public String saveData(@RequestBody DataDTOIn req) {
        dataService.saveData(req);

        return "DATA BERHASIL DITAMBAHKAN";
    }

}
