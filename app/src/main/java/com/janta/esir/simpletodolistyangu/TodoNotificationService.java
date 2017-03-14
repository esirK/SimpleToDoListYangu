package com.janta.esir.simpletodolistyangu;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.UUID;

/**
 * Created by esir on 14/03/2017.
 */

public class TodoNotificationService extends IntentService {
    public static final String TODOTEXT="com.janta.esir.todonotificationservicetxt";
    public static final String TODOUUID="com.janta.esir.todonotificationserviceuuid";
    private String mTodoText;
    private UUID mTodoUUID;
    private Context context;

    public TodoNotificationService(){
        super("ToDo Notification Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mTodoText=intent.getStringExtra(TODOTEXT);
        mTodoUUID=(UUID)intent.getSerializableExtra(TODOUUID);

        NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent i=new Intent(this,ReminderActivity.class);
        i.putExtra(TodoNotificationService.TODOUUID,mTodoUUID);
        Intent deleteIntent=new Intent(this,DeleteNotificationService.class);
        deleteIntent.putExtra(TODOUUID,mTodoUUID);
        Notification notification=new Notification.Builder(this).setContentTitle(mTodoText)
                .setSmallIcon(R.drawable.ic_done_white_24dp)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setDeleteIntent(PendingIntent.getService(this,mTodoUUID.hashCode(),deleteIntent,PendingIntent.FLAG_UPDATE_CURRENT))
                .setContentIntent(PendingIntent.getActivity(this,mTodoUUID.hashCode(),i,PendingIntent.FLAG_UPDATE_CURRENT))
                .build();

        manager.notify(100,notification);
    }
}
