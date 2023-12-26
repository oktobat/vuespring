package com.office.library.service;

import com.office.library.dao.HopeBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HopeBookService {
    @Autowired
    HopeBookDao hopeBookDao;
    public List<Map<String, String>> selectHopeBooks(){
        List<Map<String, String>> list = hopeBookDao.selectHopeBooks();
        return list;
    }
}
