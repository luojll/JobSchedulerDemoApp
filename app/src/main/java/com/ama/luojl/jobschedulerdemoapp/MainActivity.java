package com.ama.luojl.jobschedulerdemoapp;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private Button startJobBtn;
    private Button cancelJobBtn;

    private int jobCnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        textView = (TextView) findViewById(R.id.infoView);

        startJobBtn = (Button) findViewById(R.id.startJobBtn);
        cancelJobBtn = (Button) findViewById(R.id.cancelJobBtn);

        startJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helpers.scheduleJob(getApplicationContext(), ++jobCnt);
            }
        });
        cancelJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final JobScheduler scheduler = getApplicationContext().getSystemService(JobScheduler.class);
                scheduler.cancel(jobCnt);
            }
        });
    }
}
