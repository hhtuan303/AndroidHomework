package com.example.bai10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MySmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        processSms(context, intent);
    }

    private void processSms(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String message = "";
        String body = "";
        String address = "";
        if (bundle != null) {
            Object[] mySms = (Object[]) bundle.get("pdus");
            for (int i = 0; i < mySms.length; i++) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) mySms[i]);
                body += smsMessage.getMessageBody();
                address = smsMessage.getOriginatingAddress();
            }
            message = "Có 1 tin nhắn từ " + address + "\n" + body + " vừa gửi đến";
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}