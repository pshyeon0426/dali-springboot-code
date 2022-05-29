package com.hoteldali.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import javax.transaction.Transactional;

import com.hoteldali.model.domain.Hotel;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface HotelRepository extends JpaRepository<Hotel, String> {
    List<Hotel> findByNamekorContains(String name);
    List<Hotel> findByNameengContains(String name);
    List<Hotel> findByStar(int star);
    List<Hotel> findByLocationContains(String location);
    List<Hotel> findTop10ByOrderByViewcountDesc();
    List<Hotel> findByNamekor(String name);
    List<Hotel> findByNameeng(String name);
    Hotel findById(Long id);
}
