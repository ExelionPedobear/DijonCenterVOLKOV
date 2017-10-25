package com.example.ruslanmanca.dijoncentervolkov.listeners;

import android.provider.Telephony;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.example.ruslanmanca.dijoncentervolkov.CarteActivity;
import com.example.ruslanmanca.dijoncentervolkov.DetailActivity;
import com.example.ruslanmanca.dijoncentervolkov.MainActivity;
import com.example.ruslanmanca.dijoncentervolkov.adapters.PoiAdapter;
import com.example.ruslanmanca.dijoncentervolkov.models.Poi;

import java.util.ArrayList;

/**
 * Created by RuslanManca on 25/10/2017.
 */

public class SmsListener extends BroadcastReceiver {

    private SharedPreferences preferences;
    final SmsManager sms = SmsManager.getDefault();
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs = null;
            String msg_from;
            if (bundle != null){
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        String msgBody = msgs[i].getMessageBody();
                        PoiAdapter poiAdapter = new PoiAdapter("https://my-json-server.typicode.com/lpotherat/pois/pois/");
                        final Poi poi = poiAdapter.GetById(msgBody);
                        Intent intent2 = new Intent(context, DetailActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent2.putExtra("Poi", poi);
                        context.startActivity(intent2);
                        Toast.makeText(context, poi.getName() , Toast.LENGTH_LONG).show();
                    }
                }catch(Exception e){
//                            Log.d("Exception caught",e.getMessage());
                }
            }
        }
    }
}