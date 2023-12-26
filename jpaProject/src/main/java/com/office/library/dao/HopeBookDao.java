package com.office.library.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HopeBookDao {
    public List<Map<String, String>> selectHopeBooks();
}
