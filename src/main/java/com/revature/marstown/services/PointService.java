package com.revature.marstown.services;

import org.springframework.stereotype.Service;

import com.revature.marstown.entities.Point;
import com.revature.marstown.entities.User;
import com.revature.marstown.repositories.PointRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PointService {
    private final PointRepository pointRepository;

    public Point createPoints(String userId) {
        User user = new User("", "", null);
        user.setId(userId);
        var points = new Point(user, 0L);
        return pointRepository.save(points);
    }

    public Point getPoints(String userId) {
        return pointRepository.findByUserId(userId).get();
    }

    public void addPoints(String userId, long amount) {
        var points = getPoints(userId);
        points.setAmount(points.getAmount() + amount);
        pointRepository.save(points);
    }

    public boolean canRedeemPoints(Point points, long amount) {
        return points.getAmount() - amount >= 0;
    }

    public void subtractPoints(String userId, long amount) {
        var points = getPoints(userId);
        if (canRedeemPoints(points, amount)) {
            points.setAmount(points.getAmount() - amount);
        }
        pointRepository.save(points);
    }
}
