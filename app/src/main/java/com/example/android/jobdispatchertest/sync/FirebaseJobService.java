package com.example.android.jobdispatchertest.sync;

import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Erzeugt von M. Fengels am 01.02.2018.
 */

public class FirebaseJobService  extends JobService {
	@Override
	public boolean onStartJob(JobParameters job) {
		Log.d("FirebaseJobService","onStartJob");
		return false;
	}
	
	@Override
	public boolean onStopJob(JobParameters job) {
		return false;
	}
}
