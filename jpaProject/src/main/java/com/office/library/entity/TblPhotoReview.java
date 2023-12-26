package com.office.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TblPhotoReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pr_no;
    private Integer u_m_no;
    private String pr_name;
    private String pr_author;
    private String pr_publisher;
    private String pr_photo0;
    private String pr_photo1;
    private String pr_photo2;

}
