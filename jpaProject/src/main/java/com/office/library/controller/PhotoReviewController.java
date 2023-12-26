package com.office.library.controller;

import com.office.library.entity.TblPhotoReview;
import com.office.library.service.PhotoReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestPart;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class PhotoReviewController {

    @Autowired
    private PhotoReviewService photoReviewService;

    @GetMapping("/photoReview")
    public String photoReview(){
        return "photo_review";
    }

    @GetMapping("/photoReviewList")
    public String photoReviewList(Model model){
        model.addAttribute("list", photoReviewService.photoReviewList());
        return "photo_review_list";
    }

    @PostMapping("/photoReviewFindAll")
    public List<TblPhotoReview> photoReviewFindAll(){
        return photoReviewService.photoReviewList();
    }

    @GetMapping("/photoReviewDetail")
    public String photoReviewDetail(Model model, @RequestParam("pr_no") Integer pr_no){
        model.addAttribute("article", photoReviewService.photoReviewDetail(pr_no));
        return "photo_review_detail";
    }

    @PostMapping("/photoReviewWrite")
    public String photoReviewWrite(@RequestPart("data") TblPhotoReview tblPhotoReview,
                                   @RequestPart(value="file", required=false) Optional<MultipartFile[]> files) {
        if (files != null && !files.isEmpty()) {
            List<String> savedFileNames = photoReviewService.multiUpload(Arrays.asList(files.get()));
            if (!savedFileNames.isEmpty()) {
                for (int i = 0; i < Math.min(savedFileNames.size(), 3); i++) {
                    switch (i) {
                        case 0:
                            tblPhotoReview.setPr_photo0(savedFileNames.get(i));
                            break;
                        case 1:
                            tblPhotoReview.setPr_photo1(savedFileNames.get(i));
                            break;
                        case 2:
                            tblPhotoReview.setPr_photo2(savedFileNames.get(i));
                            break;
                    }
                }
            }
        }
        TblPhotoReview savedPhotoReview = photoReviewService.write(tblPhotoReview);
        return "redirect:/photoReviewList";
    }

    @GetMapping("/photoReviewRemove")
    public String photoReviewRemove(@RequestParam("pr_no") Integer pr_no){
        photoReviewService.photoReviewDelete(pr_no);
        return "redirect:/photoReview";
    }

    @GetMapping("/photoReviewModifyForm")
    public String photoReviewModifyForm(Model model, @RequestParam("pr_no") Integer pr_no){
        model.addAttribute("article", photoReviewService.photoReviewDetail(pr_no));
        return "photo_review_modify";
    }

    @PostMapping("/photoReviewModify")
    public String photoReviewModify(@RequestPart("data") TblPhotoReview tblPhotoReview,
                                    @RequestPart(value="file", required=false) Optional<MultipartFile[]> files){
        TblPhotoReview tblPhotoReviewTemp = photoReviewService.photoReviewDetail(tblPhotoReview.getPr_no());
        tblPhotoReviewTemp.setPr_name(tblPhotoReview.getPr_name());
        tblPhotoReviewTemp.setPr_author(tblPhotoReview.getPr_author());
        tblPhotoReviewTemp.setPr_publisher(tblPhotoReview.getPr_publisher());
        if (files != null && !files.isEmpty()) {
            List<String> savedFileNames = photoReviewService.multiUpload(Arrays.asList(files.get()));
            if (!savedFileNames.isEmpty()) {
                for (int i = 0; i < Math.min(savedFileNames.size(), 3); i++) {
                    switch (i) {
                        case 0:
                            tblPhotoReviewTemp.setPr_photo0(savedFileNames.get(i));
                            break;
                        case 1:
                            tblPhotoReviewTemp.setPr_photo1(savedFileNames.get(i));
                            break;
                        case 2:
                            tblPhotoReviewTemp.setPr_photo2(savedFileNames.get(i));
                            break;
                    }
                }
            }
        }
        photoReviewService.write(tblPhotoReviewTemp);
        return "redirect:/photoReview";
    }

}
