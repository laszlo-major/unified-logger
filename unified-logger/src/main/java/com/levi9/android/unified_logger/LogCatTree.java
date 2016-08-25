package com.levi9.android.unified_logger;

import android.util.Log;

import timber.log.Timber;

/**
 * Created by l.major on 25/08/16.
 */

public class LogCatTree extends Timber.Tree {
    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        Log.println(priority, tag, message);
    }

}
