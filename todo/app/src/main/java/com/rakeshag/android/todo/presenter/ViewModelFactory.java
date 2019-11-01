package com.rakeshag.android.todo.presenter;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Factory for ViewModels
 */
public class ViewModelFactory implements ViewModelProvider.Factory {
    private Context mContext;
    public ViewModelFactory(Context applicationContext) {
        mContext = applicationContext;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {
            return (T) new MainActivityViewModel(mContext);
        }

        if (modelClass.isAssignableFrom(AddTaskActivityViewModel.class)) {
            return (T) new AddTaskActivityViewModel(mContext);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}