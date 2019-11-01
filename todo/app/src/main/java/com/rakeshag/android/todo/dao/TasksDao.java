package com.rakeshag.android.todo.dao;

import com.rakeshag.android.todo.model.TaskItem;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TasksDao {
    @Query("SELECT * FROM tasks")
    List<TaskItem> getAll();

    @Insert
    void insertAll(TaskItem... tasks);

    @Delete
    void delete(TaskItem task);
}
