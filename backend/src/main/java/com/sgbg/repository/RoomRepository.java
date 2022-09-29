package com.sgbg.repository;


import com.sgbg.domain.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomId(Long roomId);
    List<Room> findAllByParentCategory(String parentCategory, Pageable pageable);

    List<Room> findAllByChildCategory(String childCategory, Pageable pageable);
    List<Room> findAllBy(Pageable pageable);
//    Optional<Room> findByUserId(Long userId);
//    List<Room> findByUserId(Long userId);

}
