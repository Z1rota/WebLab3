package org.ziro;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public double x;
    public double y;
    public double r;
    public boolean hit;
    public String startTime;
    public double executionTime;

    public Point(double x, double y, double r, boolean hit, String startTime, double executionTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
        this.startTime = startTime;
        this.executionTime = executionTime;
    }

    public Point() {

    }

    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    // Для boolean — isHit()
    public boolean isHit() {
        return hit;
    }

    public String getStartTime() {
        return startTime;
    }

    public double getExecutionTime() {
        return executionTime;
    }
}
