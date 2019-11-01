package com.rakeshag.android.todo.repository;

import android.app.Application;
import android.content.Context;

import com.rakeshag.android.todo.dao.AppDatabase;
import com.rakeshag.android.todo.dao.TasksDao;
import com.rakeshag.android.todo.model.TaskItem;
import com.rakeshag.android.todo.utils.FakeDataUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Room;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class TasksRepo {
    private List<TaskItem> mTaskItems = new ArrayList<>();
    private TasksDao dao;

    public TasksRepo(Context applicationContext) {
        AppDatabase appDatabase = Room.databaseBuilder(applicationContext,
                AppDatabase.class, "my-tasks")
                .fallbackToDestructiveMigration()
                .build();
        dao = appDatabase.tasksDao();

        Single.<List<TaskItem>>create(emitter -> {
            emitter.onSuccess(dao.getAll());
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(t -> this.mTaskItems = t);
    }

    public void addTaskItem(TaskItem item) {
        mTaskItems.add(item);
        Completable.create(emitter -> {
            dao.insertAll(item);
            emitter.onComplete();
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(() -> {});

    }

    public TaskItem get(int position) {
        return mTaskItems.get(position);
    }

    public int size() {
        return mTaskItems.size();
    }

    public void remove(int position) {

        Completable.create(
                emitter -> {
                    dao.delete(mTaskItems.get(position));
                    emitter.onComplete();
                    mTaskItems.remove(position);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe();
    }
}
