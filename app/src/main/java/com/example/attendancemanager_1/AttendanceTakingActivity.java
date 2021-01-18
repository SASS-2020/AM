package com.example.attendancemanager_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AttendanceTakingActivity extends AppCompatActivity {
    private ArrayList<StudentInfoHolder> studentInfoHolderList;
    private ArrayList<AttendanceInfoHolder> attendanceInfoList;
    private RecyclerView attendanceRecyclerView;
    private FloatingActionButton fabSave;
    private Paint mSwipePaint;
    private Drawable presentDrawable;
    private Drawable absentDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_taking);
        studentInfoHolderList = new ArrayList<>();
        attendanceInfoList = new ArrayList<>();
        fabSave = findViewById(R.id.fabSaveAttendance);
        mSwipePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        presentDrawable = ContextCompat.getDrawable(this, R.drawable.ic_present);
        absentDrawable = ContextCompat.getDrawable(this, R.drawable.ic_absent);
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save Data to Database
                saveToDatabase();
            }
        });
        getStudentList();
        createRecyclerView();
    }

    void saveToDatabase()
    {
        //Save the data in attendanceInfoList
    }

    void createRecyclerView()
    {
        attendanceRecyclerView = findViewById(R.id.attendancerv);
        attendanceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        attendanceRecyclerView.setAdapter(new StudentListAdapter(studentInfoHolderList));
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction == ItemTouchHelper.RIGHT)
                {
                    attendanceInfoList.add(new AttendanceInfoHolder(studentInfoHolderList.get(viewHolder.getAdapterPosition()).getName(), studentInfoHolderList.get(viewHolder.getAdapterPosition()).getRegistration()));
                }
                studentInfoHolderList.remove(viewHolder.getAdapterPosition());
                attendanceRecyclerView.getAdapter().notifyItemRemoved(viewHolder.getAdapterPosition());
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE)
                {
                    int iconLeft;
                    int iconRight;
                    int iconTop;
                    int iconBottom;
                    int rectLeft;
                    int rectTop;
                    int rectRight ;
                    int rectBottom;

                    View v = viewHolder.itemView;
                    if(dX<0)
                    {
                        rectLeft = v.getRight()+(int)dX;
                        rectTop = v.getTop();
                        rectRight = v.getRight();
                        rectBottom = v.getBottom();
                        int margin = (v.getHeight()-absentDrawable.getIntrinsicHeight())/2;
                        iconLeft = rectLeft+margin;
                        iconTop = rectTop+margin;
                        iconRight = iconLeft+absentDrawable.getIntrinsicWidth();
                        iconBottom = rectBottom-margin;
                        mSwipePaint.setColor(getResources().getColor(R.color.colorAbsent));
                        c.drawRect(new Rect(rectLeft, rectTop, rectRight, rectBottom), mSwipePaint);
                        absentDrawable.setBounds(new Rect(iconLeft, iconTop, iconRight, iconBottom));
                        absentDrawable.draw(c);
                    }
                    else
                    {
                        rectLeft = v.getLeft();
                        rectTop = v.getTop();
                        rectRight = rectLeft+(int)dX;
                        rectBottom = v.getBottom();
                        int margin = (v.getHeight()-presentDrawable.getIntrinsicHeight())/2;
                        iconRight = rectRight-margin;
                        iconTop = rectTop+margin;
                        iconLeft = iconRight-presentDrawable.getIntrinsicWidth();
                        iconBottom = rectBottom-margin;
                        mSwipePaint.setColor(getResources().getColor(R.color.colorPresent));
                        c.drawRect(new Rect(rectLeft, rectTop, rectRight, rectBottom), mSwipePaint);
                        presentDrawable.setBounds(new Rect(iconLeft, iconTop, iconRight, iconBottom));
                        presentDrawable.draw(c);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }).attachToRecyclerView(attendanceRecyclerView);
    }

    void getStudentList()
    {
        studentInfoHolderList = getIntent().getExtras().getParcelableArrayList("StudentList");
    }
}