package com.office.library.controller;

import com.office.library.service.HopeBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8800/")
public class HopeBookController {
    @Autowired
    private HopeBookService hopeBookService;

    @GetMapping("/getHopeBooks")
    public List<Map<String, String>> getHopeBooks() {
        List<Map<String, String>> list = hopeBookService.selectHopeBooks();
        return list;
    }

}
