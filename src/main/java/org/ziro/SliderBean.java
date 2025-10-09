package org.ziro;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class SliderBean implements Serializable {
    private double value = 0d;

    public SliderBean() {}

    public void setValue(double value) {
        this.value = value;
    }
    public double getValue() { return value; }
}
