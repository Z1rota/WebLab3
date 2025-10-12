package org.ziro;

import java.util.ArrayList;
import java.util.List;

public class HitChecker {

    public boolean isHit(double r, double x, double y) {
        if (x >= 0 && y >= 0) {
            double radius = r / 2.0;
            if (x * x + y * y <= radius * radius) {
                return true;
            }
        }

        // 2. Треугольник (II четверть)
        if (x <= 0 && y >= 0) {
            // Прямая: y = x + R → точка под ней: y <= x + R
            if (y <= x + r) {
                return true;
            }
        }

        // 3. Прямоугольник (III/IV, но только y <= 0)
        if (x >= -r && x <= 0 && y >= -r / 2.0 && y <= 0) {
            return true;
        }

        return false;
    }
}
