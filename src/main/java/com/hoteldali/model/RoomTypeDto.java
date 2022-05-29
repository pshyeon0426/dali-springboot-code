package com.hoteldali.model;

import java.util.ArrayList;
import java.util.List;

import com.hoteldali.model.domain.Hotel;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoomTypeDto {
    private String type;
    private int criteria;
    private Long review;
    private List<String> images;

    // 임시 생성자
    public RoomTypeDto(Hotel hotel) {
        this.type = "";
        this.criteria = 0;
        this.review = 0L;
        this.images = new ArrayList<String>();          // 임시 룸 이미지 생성
        images.add("https://dali-contents.s3.ap-northeast-2.amazonaws.com/hotel_imgs/24guesthouse_Sinchon/0.jpg");
    }
}
