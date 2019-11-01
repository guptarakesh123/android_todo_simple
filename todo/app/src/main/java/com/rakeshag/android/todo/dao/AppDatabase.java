package com.rakeshag.android.todo.dao;

import com.rakeshag.android.todo.model.TaskItem;
import com.rakeshag.android.todo.model.TaskPriority;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {TaskItem.class}, version = 2)
@TypeConverters(TaskPriority.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TasksDao tasksDao();
}