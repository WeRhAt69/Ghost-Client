package com.example.module;

public class SliderSetting extends Setting {
    private double value;
    private final double min, max, increment;

    public SliderSetting(String name, double defaultValue, double min, double max, double increment) {
        super(name);
        this.value = defaultValue;
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    public double getValue() { return value; }
    public void setValue(double value) { 
        this.value = Math.max(min, Math.min(max, Math.round(value / increment) * increment)); 
    }
    public double getMin() { return min; }
    public double getMax() { return max; }
}
