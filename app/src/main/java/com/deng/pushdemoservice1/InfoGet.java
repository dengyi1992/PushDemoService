package com.deng.pushdemoservice1;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by deng on 16-1-22.
 */
public class InfoGet {
    /**
     * get the unique device ID
     *
     * @param context
     * @return the unique device ID
     */
    public static String getDeviceID(Context context) {
        String DeviceID;
        final TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        DeviceID = tm.getDeviceId();
        return DeviceID;
    }
}
