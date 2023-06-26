package com.revature.marstown.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.marstown.entities.Point;

public interface PointRepository extends JpaRepository<Point, String> {
    Optional<Point> findByUserId(String userId);
}
