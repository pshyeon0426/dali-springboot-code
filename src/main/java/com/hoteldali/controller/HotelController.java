package com.hoteldali.controller;

import java.util.List;

import com.hoteldali.model.HotelDetailDto;
import com.hoteldali.model.HotelDto;
import com.hoteldali.model.HotelTopDto;
import com.hoteldali.model.service.HotelService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/hotel")
@Api("호텔 컨트롤러 API")
public class HotelController {
    
    //private static final String SUCCESS = "success";
	//private static final String FAIL = "fail";
    //private static final String ERROR = "error case";

    private HotelService hotelService;

    @ApiOperation(value = "호텔리스트 한글명 포함 조회", notes = "입력받은 한글 포함을 포함한 호텔명 조회", response = List.class)
	@PostMapping("/list/kor")
    public ResponseEntity<List<String>> listHotelKor (
            @RequestBody @ApiParam(value = "한글 검색어", required = true) String name) {
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<List<String>>(hotelService.listHotelByNamekorContains(name), status);
    }

    @ApiOperation(value = "호텔리스트 상세 조회", notes = "입력받은 한글명 호텔리스트 상세 조회(중복 이름 조회)", response = List.class)
	@PostMapping("/list/kor/details")
    public ResponseEntity<List<HotelDto>> listHotelDetails (
            @RequestBody @ApiParam(value = "호텔 한글명", required = true) String hotelname) {
        HttpStatus status = HttpStatus.OK;
        List<HotelDto> hotel = hotelService.listHotelByNamekorDetails(hotelname);
        return new ResponseEntity<List<HotelDto>>(hotel, status);
    }

    @ApiOperation(value = "호텔 상세 조회", notes = "입력받은 호텔 id 호텔정보 조회", response = HotelDetailDto.class)
	@PostMapping("/details")
    public ResponseEntity<HotelDetailDto> getHotelDetails (
            @RequestBody @ApiParam(value = "호텔 id", required = true) Long id) {
        HttpStatus status = HttpStatus.OK;
        HotelDetailDto hotel = hotelService.getHotelById(id);
        return new ResponseEntity<HotelDetailDto>(hotel, status);
    }

    @ApiOperation(value = "호텔 Top10 조회", notes = "Top10 호텔리스트 조회", response = List.class)
	@PostMapping("/top10")
    public ResponseEntity<List<String>> listHotelTop10() {
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<List<String>>(hotelService.listHotelTop10(), status);
    }

    @ApiOperation(value = "호텔 Top10 리스트 조회", notes = "Top10 호텔리스트 상세조회", response = List.class)
	@PostMapping("/top10/details")
    public ResponseEntity<List<HotelTopDto>> listHotelTop10Details() {
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<List<HotelTopDto>>(hotelService.listHotelTop10Details(), status);
    }
    

}
