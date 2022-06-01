/**
 * - id : number or string;
 * - hotelId : number or string;
 * - placeName : string;
 * - comment : number;
 * - placeImage : image;
 * - score : number;
 */

package com.hoteldali.model;

import com.hoteldali.model.domain.Hotel;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HotelTopDto {
    private int id;                                         // ?? 필요 이유
    private Long hotelId;
    private String placeName;
    private Long comment;
    private String placeImage;
    private double score;

    public HotelTopDto(Hotel hotel, int rank) {
        this.id = rank;                                     // 임시 코드
        this.hotelId = hotel.getId();
        this.placeName = hotel.getNamekor();
        this.comment = 0L;                                  // 임시 코맨트 값
        this.placeImage = "https://dali-contents.s3.ap-northeast-2.amazonaws.com/hotel_imgs/24guesthouse_Sinchon/0.jpg";        // 임시 사진
        this.score = 4.8;                                   // 임시 점수
    }
}
