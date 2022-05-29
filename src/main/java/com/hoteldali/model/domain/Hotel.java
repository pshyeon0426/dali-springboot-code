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

@Entity(name="hotel")
@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@AllArgsConstructor
@RequiredArgsConstructor
@ApiModel(value = "Hotel : 호텔정보", description = "호텔의 상세 정보를 나타낸다.")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private final String namekor;
    @Column(length = 100)
    private final String nameeng;
    @Column(length = 255)
    private final String location;
    @Column
    private final int star;
    @Column(columnDefinition = "text")
    private final String details;
    @Column(length = 20)
    private final String category;
    @Column
    private final Long viewcount;
    @Column
    private final Long recentcount;
    @Column
    private final double lat;
    @Column
    private final double lng; 

}
