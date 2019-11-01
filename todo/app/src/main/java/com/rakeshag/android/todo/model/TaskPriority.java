package com.rakeshag.android.todo.model;

import androidx.room.TypeConverter;

public enum TaskPriority {
    Unset,
    Low,
    Medium,
    High;

    @TypeConverter
    public static TaskPriority getPriority(String value) {
        if (value == null) throw new IllegalArgumentException("priority can't be null");

        switch(value.toLowerCase()) {
            case "unset": return Unset;
            case "low": return Low;
            case "medium": return Medium;
            case "high": return High;
            default: throw new IllegalArgumentException("no such pririty exists");
        }
    }

    @TypeConverter
    public static String getPriority(TaskPriority value) {
        return value.toString();
    }
}
