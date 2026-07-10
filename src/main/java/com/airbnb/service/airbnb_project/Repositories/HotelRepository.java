package com.airbnb.service.airbnb_project.Repositories;

import com.airbnb.service.airbnb_project.Entities.HotelEntity;
import com.airbnb.service.airbnb_project.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
    List<HotelEntity> findByOwner(UserEntity user);
}