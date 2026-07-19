package com.hotelbooking.service.platform.Repositories;


import com.hotelbooking.service.platform.Entities.GuestEntity;
import com.hotelbooking.service.platform.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<GuestEntity, Long> {
    List<GuestEntity> findByUser(UserEntity user);
}
