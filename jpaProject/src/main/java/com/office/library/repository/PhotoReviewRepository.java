package com.office.library.repository;

import com.office.library.entity.TblPhotoReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoReviewRepository extends JpaRepository<TblPhotoReview, Integer> {
}
