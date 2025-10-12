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
    public boolean isHit;
    public String startTime;
    public double executionTime;

    public Point(double x, double y, double r, boolean isHit, String startTime, double executionTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isHit = isHit;
        this.startTime = startTime;
        this.executionTime = executionTime;
    }

    public Point() {

    }
}
