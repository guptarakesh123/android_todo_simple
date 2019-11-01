package com.rakeshag.android.todo.utils;

import com.rakeshag.android.todo.model.TaskPriority;
import com.rakeshag.android.todo.model.TaskItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class FakeDataUtils {
    @NonNull
    public static List<TaskItem> getFakeData() {
        List<TaskItem> op = new ArrayList<>();
        op.add(new TaskItem("study", "study study", "20190811", "20191116", TaskPriority.High));
        op.add(new TaskItem("wash clothes", "wash wash", "20190915", "20191030", TaskPriority.Medium));
        op.add(new TaskItem("eat", "eat eat", "20190101", "20191116", TaskPriority.High));
        op.add(new TaskItem("throw trash", "throw throw", "20190722", "20191025", TaskPriority.Low));
        op.add(new TaskItem("solve puzzles", "solve solve", "20190731", "20190804", TaskPriority.Low));
        op.add(new TaskItem("call dr. shetty", "call call", "20190907", "20191029", TaskPriority.Low));

        op.add(new TaskItem("drink", "drink drink", "20190401", "20191116", TaskPriority.High));
        op.add(new TaskItem("exercise", "exercise exercise", "20190717", "20191030", TaskPriority.Medium));
        op.add(new TaskItem("take k-citrates", "take take", "20190303", "20191116", TaskPriority.High));
        op.add(new TaskItem("do dishes", "do do", "20190309", "20191025", TaskPriority.Low));
        op.add(new TaskItem("watch netflix", "watch watch", "20190731", "20190804", TaskPriority.Low));
        op.add(new TaskItem("change bulbs", "change change", "20190907", "20191029", TaskPriority.Low));
        return op;
    }
}
