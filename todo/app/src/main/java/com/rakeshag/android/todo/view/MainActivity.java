package com.rakeshag.android.todo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.rakeshag.android.todo.R;
import com.rakeshag.android.todo.TodoAdapter;
import com.rakeshag.android.todo.databinding.ActivityMainBinding;
import com.rakeshag.android.todo.presenter.MainActivityViewModel;
import com.rakeshag.android.todo.presenter.ViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mViewModel = ViewModelProviders.of(this, new ViewModelFactory(getApplicationContext())).get(MainActivityViewModel.class);

        mBinding.allTasks.setAdapter(new TodoAdapter(mViewModel, this));
        mBinding.allTasks.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean supportsPredictiveItemAnimations() {
                return false;
            }
        });
        mBinding.allTasks.setHasFixedSize(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.allTasks.getAdapter().notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.main_menu_add) {
            Intent addIntent = new Intent(this, AddTaskActivity.class);

            startActivity(addIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
