package org.ziro;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class DataBean implements Serializable {
    private boolean r1 = true;  // по умолчанию R=1 выбрано
    private boolean r15 = false;
    private boolean r2 = false;
    private boolean r25 = false;
    private boolean r3 = false;
    private double value = 0d;
    private String text;


    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() { return value; }

    public boolean isR1() { return r1; }

    public void setR1(boolean r1) { this.r1 = r1; }

    public boolean isR15() { return r15; }
    public void setR15(boolean r15) { this.r15 = r15; }

    public boolean isR2() { return r2; }
    public void setR2(boolean r2) { this.r2 = r2; }

    public boolean isR25() { return r25; }
    public void setR25(boolean r25) { this.r25 = r25; }

    public boolean isR3() { return r3; }
    public void setR3(boolean r3) { this.r3 = r3; }


    public Double getSelectedR() {
        if (r1) return 1d;
        if (r15) return 1.5d;
        if (r2) return 2d;
        if (r25) return 2.5d;
        if (r3) return 3d;
        return null;
    }
}
