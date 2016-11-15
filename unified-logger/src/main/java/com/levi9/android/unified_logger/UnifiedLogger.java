package com.levi9.android.unified_logger;

import com.crashlytics.android.Crashlytics;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import timber.log.Timber;

/**
 * Created by l.major on 2/3/2015.
 */
public class UnifiedLogger {

    private SimpleDateFormat mDateFormat;

    private String appendTimeStamp(String tag) {
        return "[" + mDateFormat.format(new Date()) + "] " + tag;
    }

    /**
     * Holds the classname to properly tag messages
     */

    /**
     * Return a UnifiedLogger with timestamp
     *
     */
    private UnifiedLogger() {
        mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        mDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    /**
     * Return a logger named corresponding to the class passed as parameter
     *
     * @param clazz the returned logger will be tagged after clazz
     *
     * @return logger
     */
    public static UnifiedLogger getUnifiedLogger (final Class clazz) {
        return getUnifiedLogger(clazz.getName());
    }

    public static UnifiedLogger getUnifiedLogger(final String className) {
        if (className == null) {
            throw new IllegalArgumentException("name argument cannot be null");
        }
        return getUnifiedLogger();

    }

    /**
     * New contructor that doesn't rely on getting tag from class, since
     * all methods take tag
     * @return logger
     */
    public static UnifiedLogger getUnifiedLogger() {
        return new UnifiedLogger();
    }

    public void d(String tag, String msg, boolean uploadToCrashlytics) {
        logToTimber(Log.DEBUG, tag, msg, null);
        if (uploadToCrashlytics && Timber.treeCount() > 0) {
            Crashlytics.log(Log.DEBUG, appendTimeStamp(tag), msg);
        }
    }

    public void d(String tag, String msg, boolean uploadToCrashlytics, Throwable tr) {
        logToTimber(Log.DEBUG, tag, msg, tr);
        if (uploadToCrashlytics && Timber.treeCount() > 0) {
            Crashlytics.logException(tr);
        }
    }

    public void v(String tag, String msg, boolean uploadToCrashlytics) {
        logToTimber(Log.VERBOSE, tag, msg, null);
        if (uploadToCrashlytics && Timber.treeCount() > 0) {
            Crashlytics.log(Log.VERBOSE, appendTimeStamp(tag), msg);
        }
    }

    public void v(String tag, String msg, boolean uploadToCrashlytics, Throwable tr) {
        logToTimber(Log.VERBOSE, tag, msg, tr);
        if (uploadToCrashlytics && Timber.treeCount() > 0) {
            Crashlytics.logException(tr);
        }
    }

    public void e(String tag, String msg, boolean uploadToCrashlytics) {
        logToTimber(Log.ERROR, tag, msg, null);
        if (uploadToCrashlytics && Timber.treeCount() > 0) {
            Crashlytics.log(Log.ERROR, appendTimeStamp(tag), msg);
        }
    }

    public void e(String tag, String msg, boolean uploadToCrashlytics, Throwable tr) {
        logToTimber(Log.ERROR, tag, msg, tr);
        if (uploadToCrashlytics && Timber.treeCount() > 0) {
            Crashlytics.logException(tr);
        }
    }

    public void w(String tag, String msg, boolean uploadToCrashlytics) {
        logToTimber(Log.WARN, tag, msg, null);
        if (uploadToCrashlytics && Timber.treeCount() > 0) {
            Crashlytics.log(Log.WARN, appendTimeStamp(tag), msg);
        }
    }

    public void w(String tag, String msg, boolean uploadToCrashlytics, Throwable tr) {
        logToTimber(Log.WARN, tag, msg, tr);
        if (uploadToCrashlytics && Timber.treeCount() > 0) {
            Crashlytics.logException(tr);
        }
    }

    public void w(String tag, boolean uploadToCrashlytics, Throwable tr) {
        logToTimber(Log.WARN, tag, "", tr);
        if (uploadToCrashlytics && Timber.treeCount() > 0) {
            Crashlytics.logException(tr);
        }
    }

    public void wtf(String tag, String msg, boolean uploadToCrashlytics) {
        logToTimber(Log.ASSERT, tag, msg, null);
        if (uploadToCrashlytics && Timber.treeCount() > 0) {
            Crashlytics.log(Log.ERROR, appendTimeStamp(tag), msg);
        }
    }

    public void wtf(String tag, String msg, boolean uploadToCrashlytics, Throwable tr) {
        logToTimber(Log.ASSERT, tag, msg, tr);
        if (uploadToCrashlytics && Timber.treeCount() > 0) {
            Crashlytics.logException(tr);
        }
    }

    public void wtf(String tag, boolean uploadToCrashlytics, Throwable tr) {
        logToTimber(Log.ASSERT, tag, "", tr);
        if (uploadToCrashlytics && Timber.treeCount() > 0) {
            Crashlytics.logException(tr);
        }
    }

    public void i(String tag, String msg, boolean uploadToCrashlytics) {
        logToTimber(Log.INFO, tag, msg, null);
        if (uploadToCrashlytics && Timber.treeCount() > 0) {
            Crashlytics.log(Log.VERBOSE, appendTimeStamp(tag), msg);
        }
    }

    public void i(String tag, String msg, boolean uploadToCrashlytics, Throwable tr) {
        logToTimber(Log.INFO, tag, msg, tr);
        if (uploadToCrashlytics && Timber.treeCount() > 0) {
            Crashlytics.logException(tr);
        }
    }

    private void logToTimber(int priority, String tag, String msg, Throwable throwable) {
        Timber.tag(tag);
        if (throwable != null) {
            Timber.log(priority, throwable, msg);
        } else {
            Timber.log(priority, msg);
        }

    }
}