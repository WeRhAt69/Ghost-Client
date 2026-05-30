package com.example.module;

public enum Category {
    FAVORITES("Favorites"),
    COMBAT("Combat"),
    RENDER("Render"),
    UTILITY("Utility");

    public final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }
}
