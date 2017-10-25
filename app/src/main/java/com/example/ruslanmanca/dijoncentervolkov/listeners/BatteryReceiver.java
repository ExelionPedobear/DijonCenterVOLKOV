package com.example.ruslanmanca.dijoncentervolkov.listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by RuslanManca on 25/10/2017.
 */

public class BatteryReceiver  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Battery !!", Toast.LENGTH_LONG).show();
    }
}