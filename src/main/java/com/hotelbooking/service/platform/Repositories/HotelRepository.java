package com.hotelbooking.service.platform.Repositories;

import com.hotelbooking.service.platform.Entities.HotelEntity;
import com.hotelbooking.service.platform.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
    List<HotelEntity> findByOwner(UserEntity user);
}