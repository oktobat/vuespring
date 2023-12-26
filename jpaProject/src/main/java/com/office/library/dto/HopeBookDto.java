package com.office.library.dto;

import lombok.Data;

@Data
public class HopeBookDto {
    private int hb_no;
    private int u_m_no;
    private String hb_name;
    private String hb_author;
    private String hb_publisher;
    private int hb_publish_year;
    private String hb_reg_date;
    private String hb_mod_date;
    private int hb_result;
    private String hb_result_last_date;
}
