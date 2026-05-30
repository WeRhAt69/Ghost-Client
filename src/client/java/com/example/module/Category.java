package com.example.module;

public enum Category {
    COMBAT("Combat"),
    RENDER("Render"),
    UTILITY("Utility");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
