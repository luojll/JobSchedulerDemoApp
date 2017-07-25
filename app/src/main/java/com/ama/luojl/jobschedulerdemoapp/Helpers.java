package com.ama.luojl.jobschedulerdemoapp;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;

/**
 * Created by luojl on 7/25/17.
 */

public class Helpers {

    public static void scheduleJob(Context context, int id) {
        final JobScheduler scheduler = context.getSystemService(JobScheduler.class);

        final JobInfo.Builder builder = new JobInfo.Builder(id,
                new ComponentName(context, DemoJobService.class));

        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED);

        scheduler.schedule(builder.build());
    }
}
