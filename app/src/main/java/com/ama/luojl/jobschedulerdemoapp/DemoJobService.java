package com.ama.luojl.jobschedulerdemoapp;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by luojl on 7/25/17.
 */

public class DemoJobService extends JobService {
    private static final String TAG = "DemoJobService";

    private static final int SLEEP_TIME = 5*1000; // 5 seconds

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        final int jobId = params.getJobId();
        Log.i(TAG, "id: " + jobId + ", onStartJob");
        new JobTask(this).execute(params);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        final int jobId = params.getJobId();
        Log.w(TAG, "id: " + jobId + ", onStopJob");
        return false;
    }

    private static class JobTask extends AsyncTask<JobParameters, Void, JobParameters> {
        private final JobService mJobService;

        public JobTask(JobService jobService) {
            mJobService = jobService;
        }

        @Override
        protected JobParameters doInBackground(JobParameters... jobParameterses) {
            JobParameters jobParam = jobParameterses[0];
            int jobId = jobParam.getJobId();
            Log.i(TAG, "id: " + jobId + ", doInBackground");
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                Log.w(TAG, "id: " + jobId + ", Thread interrupted");
            }
            return jobParam;
        }

        @Override
        protected void onPostExecute(JobParameters jobParameters) {
            int jobId = jobParameters.getJobId();
            Log.i(TAG, "id: " + jobId + ", onPostExecute");
            mJobService.jobFinished(jobParameters, false);
        }
    }
}
