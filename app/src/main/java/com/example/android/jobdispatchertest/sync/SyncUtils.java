package com.example.android.jobdispatchertest.sync;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;



/**
 * Erzeugt von M. Fengels am 01.02.2018.
 */

public class SyncUtils {
	
	private static final int SYNC_INTERVAL_HOURS = 3;
	private static final int SYNC_INTERVAL_SECONDS = (int) TimeUnit.MINUTES.toSeconds(5);
	private static final int SYNC_FLEXTIME_SECONDS = SYNC_INTERVAL_SECONDS / 3;
	private static final String ZEITERFASSUNG_SYNC_TAG = "test-sync";
	private static boolean sInitialized;
	
	public static void scheduleFirebaseJobDispatcherSync(@NonNull final Context context) {
		
		Driver driver = new GooglePlayDriver(context);
		FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);
		
		Job syncDataJob = dispatcher.newJobBuilder()
				.setService(FirebaseJobService.class)
				.setTag(ZEITERFASSUNG_SYNC_TAG)
				.setConstraints(true ? Constraint.ON_UNMETERED_NETWORK : Constraint.ON_ANY_NETWORK)
				.setLifetime(Lifetime.FOREVER)
				.setRecurring(true)
				.setTrigger(Trigger.executionWindow(
						SYNC_INTERVAL_SECONDS,
						SYNC_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))
				.setReplaceCurrent(true)
				.build();
		Log.w("syncUtils","job scheduled");
		dispatcher.schedule(syncDataJob);
	}
}
