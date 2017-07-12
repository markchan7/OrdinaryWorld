package com.markchan.carrier;

import android.app.Application;

import com.blankj.utilcode.utils.Utils;

import timber.log.Timber;
import timber.log.Timber.DebugTree;

/**
 * @author Mark Chan <a href="markchan2gm@gmail.com">Contact me.</a>
 * @version 1.0
 * @since 17/7/12
 */
public class CarrierApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(getApplicationContext());
        Timber.plant(new DebugTree());
    }
}