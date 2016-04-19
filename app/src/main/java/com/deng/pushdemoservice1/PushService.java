package com.deng.pushdemoservice1;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by deng on 16-1-25.
 */
public class PushService extends Service {
    private static final int NOTIFICATION_ID = 1;
    private Socket mSocket;
    private NotificationManager mNotifMan;
    private String deviceID;

    {
        try {
//            mSocket = IO.socket("http://192.168.10.116:3000/");
            mSocket = IO.socket("http://192.168.199.127:2999/");
        } catch (URISyntaxException e) {
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        deviceID = InfoGet.getDeviceID(this);
        mSocket.on("new message", onNewMessage);
        mSocket.connect();
        addUser();

    }

    private void addUser() {
//        if (mSocket.connected()){
        mSocket.emit("add user", deviceID);
//        }
    }

    private String messagetitle;
    private String message;
    private String messagecontent;
    private String url;
    private Emitter.Listener onNewMessage = new Emitter.Listener() {


        @Override
        public void call(final Object... args) {
            Log.d("11111111", "收到消息");

            JSONObject data = (JSONObject) args[0];
            String username;

            try {
                messagetitle = data.getString("messagetitle");
                messagecontent = data.getString("messagecontent");
                url =data.getString("promotionlink");
                System.out.println(url);

            } catch (JSONException e) {
                return;
            }
//            try {
//                url = data.getString("url");
//                System.out.println("**********************8" + url);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }


            if (url == null) {
                mNtify(messagetitle, messagecontent);

            } else {
                mNtifyWeb(messagetitle, messagecontent);
            }

        }


    };

    private void mNtifyWeb(String title, String message) {
        mNotifMan = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent();
        intent.putExtra("url","jd.com");
        intent.setClass(this, WebActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                intent, 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(message))
                        .setContentTitle(title)
                        .setContentText(message);

        mBuilder.setContentIntent(contentIntent);
        mNotifMan.notify(NOTIFICATION_ID, mBuilder.build());
    }

    private void mNtify(String title, String message) {
        mNotifMan = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent();
        intent.putExtra("content", title + ":" + message);
        intent.setClass(this, WebActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                intent, 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(message))
                        .setContentTitle(title)
                        .setContentText(message);

        mBuilder.setContentIntent(contentIntent);
        mNotifMan.notify(NOTIFICATION_ID, mBuilder.build());
    }


}
