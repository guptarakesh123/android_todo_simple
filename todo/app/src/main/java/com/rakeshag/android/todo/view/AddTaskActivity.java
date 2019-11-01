package com.rakeshag.android.todo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.rakeshag.android.todo.R;
import com.rakeshag.android.todo.databinding.ActivityAddTaskBinding;
import com.rakeshag.android.todo.model.TaskPriority;
import com.rakeshag.android.todo.presenter.AddTaskActivityViewModel;
import com.rakeshag.android.todo.presenter.ViewModelFactory;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {
    private AddTaskActivityViewModel mViewModel;
    ActivityAddTaskBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_task);
        mViewModel = ViewModelProviders.of(this, new ViewModelFactory(getApplicationContext())).get(AddTaskActivityViewModel.class);
        setData();
        mBinding.startDate.setInputType(InputType.TYPE_NULL);
        mBinding.startDate.setOnFocusChangeListener((view, b) -> { if(b) onEditDateClicked(mBinding.startDate, "Start Date"); });
        mBinding.endDate.setOnFocusChangeListener((view, b) -> { if(b) onEditDateClicked(mBinding.endDate, "End Date"); });

        mBinding.addItemButton.setOnClickListener(ignored -> onSubmit());
    }
    private boolean onEditDateClicked(EditText dateView, String title) {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        DatePickerDialog picker = new DatePickerDialog(AddTaskActivity.this,
                (DatePicker view, int y, int monthOfYear, int dayOfMonth) -> {
                    dateView.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + y);
                }, year, month, day);
        picker.setTitle(title);
        picker.show();
        return false;
    }

    private void setData() {
        mBinding.taskName.setText(mViewModel.getName());
        mBinding.taskDetails.setText(mViewModel.getDetails());
        mBinding.startDate.setText(mViewModel.getStartDate());
        mBinding.endDate.setText(mViewModel.getEndDate());

        int prirityRes = getPriorityRes(mViewModel.getPriority());
        if (prirityRes != -1) {
            mBinding.priority.check(prirityRes);
        } else {
            mBinding.priority.clearCheck();
        }
    }

    private void gatherData() {
        mViewModel.setName(mBinding.taskName.getText().toString());
        mViewModel.setDetails(mBinding.taskDetails.getText().toString());
        mViewModel.setStartDate(mBinding.startDate.getText().toString());
        mViewModel.setEndDate(mBinding.endDate.getText().toString());

        int prirityRes = mBinding.priority.getCheckedRadioButtonId();
        if (prirityRes == -1) {
            mViewModel.setPriority(TaskPriority.Unset.toString());
        } else if (mBinding.lowPriority.isChecked()){
            mViewModel.setPriority(TaskPriority.Low.toString());
        } else if (mBinding.mediumPriority.isChecked()){
            mViewModel.setPriority(TaskPriority.Medium.toString());
        } else if (mBinding.highPriority.isChecked()){
            mViewModel.setPriority(TaskPriority.High.toString());
        } else {
            mViewModel.setPriority(TaskPriority.Unset.toString());
        }
    }

    private void onSubmit() {
        if (validateData()) {
            gatherData();
            mViewModel.commitData();

            mViewModel.reset();
            setData();
        }
    }

    private boolean validateData() {
        return ! ( mBinding.taskName.getText().toString().isEmpty()
                || mBinding.taskDetails.getText().toString().isEmpty()
                || mBinding.startDate.getText().toString().isEmpty()
                || mBinding.endDate.getText().toString().isEmpty()
                || mBinding.priority.getCheckedRadioButtonId() == -1 );
    }

    private int getPriorityRes(TaskPriority priority) {
        switch (priority) {
            case Low: return mBinding.lowPriority.getId();
            case Medium: return mBinding.mediumPriority.getId();
            case High: return mBinding.highPriority.getId();
            case Unset:
            default: return -1;
        }
    }

    @Override
    protected void onDestroy() {
        gatherData();
        super.onDestroy();
    }
}
