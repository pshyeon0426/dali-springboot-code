package com.hoteldali.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity(name="hotelreview")
@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@AllArgsConstructor
@RequiredArgsConstructor
@ApiModel(value = "Hotelreview : 호텔 리뷰", description = "호텔 리뷰 정보를 나타낸다.")
public class HotelReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private final Long hotelid;
    @Column
    private final String nickname;
    @Column
    private final int star;
    @Column(columnDefinition = "text")
    private final String contents;
    
}
