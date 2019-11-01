package com.rakeshag.android.todo;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rakeshag.android.todo.databinding.TaskItemBinding;
import com.rakeshag.android.todo.model.TaskItem;
import com.rakeshag.android.todo.presenter.MainActivityViewModel;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoItemViewHolder> {

    private MainActivityViewModel mViewModel;

    public TodoAdapter(MainActivityViewModel viewModel, LifecycleOwner owner) {
        mViewModel = viewModel;
    }

    @NonNull
    @Override
    public TodoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TaskItemBinding row =
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.task_item,
                        parent,
                        false);
        return new TodoItemViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoItemViewHolder holder, int position) {
        holder.bind(mViewModel.getTask(position));
    }

    @Override
    public int getItemCount() {
        return mViewModel.getDataSize();
    }

    private void deleteItem(int position) {
        mViewModel.removeTask(position);
        notifyItemRemoved(position);
        //notifyItemRangeChanged(position, mData.size());
    }

    class TodoItemViewHolder extends RecyclerView.ViewHolder {
        private TaskItemBinding mItemTaskBinding;

        public TodoItemViewHolder(@NonNull TaskItemBinding itemTaskBinding) {
            super(itemTaskBinding.getRoot());
            mItemTaskBinding = itemTaskBinding;
        }

        public void bind(TaskItem item) {
            mItemTaskBinding.taskName.setText(item.getName());
            mItemTaskBinding.startDate.setText(item.getStartDate());
            mItemTaskBinding.endDate.setText(item.getEndDate());
            mItemTaskBinding.priority.setText(item.getPriority().toString());
            mItemTaskBinding.deleteButton.setOnClickListener(
                    ignored -> deleteItem(getAdapterPosition()));
        }
    }
}
