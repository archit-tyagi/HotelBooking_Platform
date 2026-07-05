package com.airbnb.service.airbnb_project.Repositories;


import com.airbnb.service.airbnb_project.Entities.GuestEntity;
import com.airbnb.service.airbnb_project.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<GuestEntity, Long> {
    List<GuestEntity> findByUser(UserEntity user);
}
