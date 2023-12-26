package com.office.library.service;

import com.office.library.entity.TblPhotoReview;
import com.office.library.repository.PhotoReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.io.File;


@Service
public class PhotoReviewService {

    @Autowired
    private PhotoReviewRepository photoReviewRepository;

    public List<TblPhotoReview> photoReviewList(){
        return photoReviewRepository.findAll();
    }

    public TblPhotoReview photoReviewDetail(Integer pr_no){

        return photoReviewRepository.findById(pr_no).get();
    }

    public TblPhotoReview write(TblPhotoReview tblPhotoReview){

        return photoReviewRepository.save(tblPhotoReview);
    }

    public void photoReviewDelete(Integer pr_no){
        photoReviewRepository.deleteById(pr_no);
    }

    public List<String> multiUpload(List<MultipartFile> files) {
        String root = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        File fileCheck = new File(root);

        if(!fileCheck.exists()) fileCheck.mkdirs();

        List<String> originFileList = new ArrayList<>();
        List<String> changeFileList = new ArrayList<>();

        for(int i = 0; i < files.size(); i++) {
            String originFile = files.get(i).getOriginalFilename();
            String ext = originFile.substring(originFile.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString() + ext;
            String changeFile = uuid.toString().replaceAll("-", "");

            originFileList.add(originFile);
            changeFileList.add(changeFile);
        }

        try {
            for(int i = 0; i < files.size(); i++) {
                File uploadFile = new File(root + "\\" + changeFileList.get(i));
                files.get(i).transferTo(uploadFile);
            }

        } catch (IllegalStateException | IOException e) {
            for(int i = 0; i < files.size(); i++) {
                new File(root + "\\" + changeFileList.get(i)).delete();
            }
            e.printStackTrace();
        }

        return changeFileList;
    }

}
