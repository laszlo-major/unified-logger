package com.levi9.android.unified_logger;

import com.crashlytics.android.Crashlytics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by l.major on 2/3/2015.
 */
public class UnifiedLogger {

    private SimpleDateFormat mDateFormat;

    private String appendTimeStamp(String tag) {
        return "[" + mDateFormat.format(new Date()) + "] " + tag;
    }

    /**
     * Holds an instance of a slf4j.Logger relevant to the particular instance of UnifiedLogger
     */
    private Logger classLogger;

    /**
     * Return a UnifiedLogger containing a properly named slf4j.Logger
     *
     * @param className
     *         The name of the logger.
     */
    private UnifiedLogger(String className) {
        classLogger = LoggerFactory.getLogger(className);
        mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        mDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    /**
     * Return a logger named corresponding to the class passed as parameter, using
     * the statically bound {@link org.slf4j.ILoggerFactory} instance.
     *
     * @param clazz the returned logger will be named after clazz
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
        return new UnifiedLogger(className);

    }

    public void d(String tag, String msg, boolean uploadToCrashlytics) {
        classLogger.debug(msg);
        if (uploadToCrashlytics) {
            Crashlytics.log(Log.DEBUG, appendTimeStamp(tag), msg);
        }
    }

    public void d(String tag, String msg, boolean uploadToCrashlytics, Throwable tr) {
        classLogger.debug(msg, tr);
        if (uploadToCrashlytics) {
            Crashlytics.logException(tr);
        }
    }

    public void v(String tag, String msg, boolean uploadToCrashlytics) {
        classLogger.trace(msg);
        if (uploadToCrashlytics) {
            Crashlytics.log(Log.VERBOSE, appendTimeStamp(tag), msg);
        }
    }

    public void v(String tag, String msg, boolean uploadToCrashlytics, Throwable tr) {
        classLogger.trace(msg, tr);
        if (uploadToCrashlytics) {
            Crashlytics.logException(tr);
        }
    }

    public void e(String tag, String msg, boolean uploadToCrashlytics) {
        classLogger.error(msg);
        if (uploadToCrashlytics) {
            Crashlytics.log(Log.ERROR, appendTimeStamp(tag), msg);
        }
    }

    public void e(String tag, String msg, boolean uploadToCrashlytics, Throwable tr) {
        classLogger.error(msg);
        if (uploadToCrashlytics) {
            Crashlytics.logException(tr);
        }
    }

    public void w(String tag, String msg, boolean uploadToCrashlytics) {
        classLogger.warn(msg);
        if (uploadToCrashlytics) {
            Crashlytics.log(Log.WARN, appendTimeStamp(tag), msg);
        }
    }

    public void w(String tag, String msg, boolean uploadToCrashlytics, Throwable tr) {
        classLogger.warn(msg, tr);
        if (uploadToCrashlytics) {
            Crashlytics.logException(tr);
        }
    }

    public void w(String tag, boolean uploadToCrashlytics, Throwable tr) {
        classLogger.warn(tag, tr);
        if (uploadToCrashlytics) {
            Crashlytics.logException(tr);
        }
    }

    public void wtf(String tag, String msg, boolean uploadToCrashlytics) {
        classLogger.error(msg);
        if (uploadToCrashlytics) {
            Crashlytics.log(Log.ERROR, appendTimeStamp(tag), msg);
        }
    }

    public void wtf(String tag, String msg, boolean uploadToCrashlytics, Throwable tr) {
        classLogger.error(msg, tr);
        if (uploadToCrashlytics) {
            Crashlytics.logException(tr);
        }
    }

    public void wtf(String tag, boolean uploadToCrashlytics, Throwable tr) {
        classLogger.error(tag, tr);
        if (uploadToCrashlytics) {
            Crashlytics.logException(tr);
        }
    }

    public void i(String tag, String msg, boolean uploadToCrashlytics) {
        classLogger.info(msg);
        if (uploadToCrashlytics) {
            Crashlytics.log(Log.VERBOSE, appendTimeStamp(tag), msg);
        }
    }

    public void i(String tag, String msg, boolean uploadToCrashlytics, Throwable tr) {
        classLogger.info(msg, tr);
        if (uploadToCrashlytics) {
            Crashlytics.logException(tr);
        }
    }
}