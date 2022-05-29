/** HotelDetailData
 *   - id : number or string;
 *   - name : string;
 *   - type : number ⇒ 0이면 호텔, 1이면 리조트, … 등으로 구분 ( 구체적인 사항은 논의 필요! )
 *   - rank : number;
 *   - classification : 4; ⇒ 4성급, 5성급.. 에 대한 자료
 *   - explanation : string;
 *   - detailExplanation : string;
 *   - review : number;
 *   - location : {
 *       longitude : number;
 *       latitude : number;
 *       position : string;
 *   }
 *   - roomType : [{   
 *       type:  string;
 *       criteria: number;
 *       max: number;
 *       review: number;
 *       images: [
 *               require('front/src/assets/images/hotelExample3.png'),
 *               require('front/src/assets/images/hotelExample2.png'),
 *               require('front/src/assets/images/hotelExample3.png'),
 *       ]
 *   }]
 */
package com.hoteldali.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hoteldali.model.domain.Hotel;

import lombok.Getter;

@Getter
public class HotelDetailDto {
    private Long id;
    private String name;
    private String type;
    private double rank;
    private int classification;
    private String explanation;
    private String detailExplanation;
    private Long review;
    @JsonProperty("location")
    private LocationDto location;
    @JsonProperty("roomType")
    private List<RoomTypeDto> roomType;

    public HotelDetailDto(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getNamekor();
        this.type = hotel.getCategory();
        this.rank = 5.0;                                          // 임시 평점
        this.classification = hotel.getStar();
        this.explanation = "임시 설명";                             // 임시 설명
        this.detailExplanation = "임시 상세 설명";                   // 임시 상세 설명
        this.review = 0L;                                         // 임시 리뷰 개수
        this.location = new LocationDto(hotel);
        this.roomType = new ArrayList<RoomTypeDto>();             // 임시 룸타입 생성
        roomType.add(new RoomTypeDto(hotel));        
    }
}
