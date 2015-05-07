package com.levi9.android.unified_logger_demo;

import com.crashlytics.android.Crashlytics;
import com.levi9.android.unified_logger.UnifiedLogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import io.fabric.sdk.android.Fabric;


public class UnifiedLoggerDemo extends ActionBarActivity {

    private static final UnifiedLogger LOG = UnifiedLogger.getUnifiedLogger(UnifiedLoggerDemo.class);

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //You can use this method to disable crashlytics in a specific flavor. Careful, BuildConfig.DEBUG is not always set correctly.
        //Disabled so we can remove secrets in the public
        //will instant crash without this
        //Crashlytics crashlytics = new Crashlytics.Builder().disabled(false).build();
        //Fabric.with(this, crashlytics);

        setContentView(R.layout.activity_unified_logger_demo);
        LOG.d(TAG, "MSG", true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_unified_logger_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void firstButton(View v) {
        LOG.d(TAG, "firstButton", true);
    }

    public void secondButton(View v) {
        LOG.d(TAG, "secondButton", false);
        LOG.d(TAG, "secondButtonWithTrue", true);
        Crashlytics.log("something something");
        Crashlytics.log("something something else");
    }

    public void thirdButton(View v) {
        LOG.v(TAG, "thirdButton", true);
    }

    public void crash(View v) {
        try {
            throw new ArithmeticException("panic!");
        } catch (Exception ex) {
            LOG.e(TAG, ex.toString(), true);
            throw new RuntimeException("crash");
        }
    }
}
