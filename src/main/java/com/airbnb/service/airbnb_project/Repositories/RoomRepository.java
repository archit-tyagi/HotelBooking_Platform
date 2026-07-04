package com.airbnb.service.airbnb_project.Repositories;

import com.airbnb.service.airbnb_project.Entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity,Long> {
}
