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

    private static final int SLEEP_TIME = 5*1000;

    @Override
    public boolean onStartJob(JobParameters params) {
        final int id = params.getJobId();
        Log.i(TAG, "onStartJob, id: " + id);
        new JobTask(this).execute(params);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        final int id = params.getJobId();
        Log.i(TAG, "onStopJob, id: " + id);
        return false;
    }

    private static class JobTask extends AsyncTask<JobParameters, Void, JobParameters> {
        private final JobService mJobService;

        public JobTask(JobService jobService) {
            mJobService = jobService;
        }

        @Override
        protected JobParameters doInBackground(JobParameters... jobParameterses) {
            JobParameters job = jobParameterses[0];
            int jobId = job.getJobId();
            Log.i(TAG, "doInBackground, id: " + jobId);
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                Log.w(TAG, "Thread interrupted, id: " + jobId);
            }
            Log.i(TAG, "Sleep Done, id: " + jobId);
            return job;
        }

        @Override
        protected void onPostExecute(JobParameters jobParameters) {
            int jobId = jobParameters.getJobId();
            Log.i(TAG, "onPostExecute, id: " + jobId);
            mJobService.jobFinished(jobParameters, false);
        }
    }
}
