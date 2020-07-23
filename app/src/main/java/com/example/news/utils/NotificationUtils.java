package com.example.news.utils;

import android.app.NotificationManager;

import androidx.core.app.NotificationCompat;

public class NotificationUtils {
    private static int NOTIFICATION_ID1;

    private static final int PENDING_INTENT_ID = 9001;
    private static final String NOTIFICATION_CHANNEL_ID = "notification_channel_name";
    private static NotificationCompat.Builder mNotificationBuilder;
    private static NotificationManager mNotificationManager;
    private static boolean isNotificationNew = true;

    private NotificationUtils() {}

   /* public static void showNotification(Context context, String title, String body,int NOTIFICATION_ID) {
       NOTIFICATION_ID1=NOTIFICATION_ID;
        if (isNotificationNew == false) {
            updateNotification(title, body);
            return;
        }
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String name = "Awesome Notification Channel";
            String description = "This channel shows only awesome notification";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance);
            mNotificationManager.createNotificationChannel(channel);
            channel.setDescription(description);
        }
        mNotificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setGroup(NotificationCompat.CATEGORY_REMINDER)
                .setGroupSummary(true)
                .setSmallIcon(R.drawable.ic_sms_indigo_a700_24dp)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(title)
                .setContentText(body)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                .setContentIntent(contentIntent(context))
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .addAction(
                        R.drawable.ic_action_stat_share,
                        "Share",
                        PendingIntent.getActivity(
                                context,
                                0,
                                Intent.createChooser(new Intent(Intent.ACTION_SEND)
                                        .setType("text/plain")
                                        .putExtra(Intent.EXTRA_TEXT, "Dummy text"), "Dummy title"),
                                PendingIntent.FLAG_UPDATE_CURRENT))
                .addAction(
                        R.drawable.ic_action_stat_reply,
                        "Replay",
                        null)

                // .addAction(actionOne(context))
            //    .addAction(actionTwo(context))
                .setAutoCancel(true);
        // Notification Intrusive (PRIORITY) on Android 7.1 and Lower.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
            mNotificationBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
           }
        mNotificationManager.notify(NOTIFICATION_ID, mNotificationBuilder.build());
        isNotificationNew = false;
    }



    private static void updateNotification(String title, String body) {
        mNotificationBuilder
                .setContentTitle(title)
                .setContentText(body)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                .setOnlyAlertOnce(true) ;
        mNotificationManager.notify(NOTIFICATION_ID1, mNotificationBuilder.build());
    }

    private static PendingIntent contentIntent(Context context) {
        Intent startActivityIntent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(context,PENDING_INTENT_ID,startActivityIntent,PendingIntent.FLAG_UPDATE_CURRENT);
    }
    private static Bitmap largeIcon(Context context) {
        Resources res = context.getResources();
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.m);
        return largeIcon;
    }
    public static void clearNotifications(Context context)
    {     NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancelAll();
    }
    public static void shareNotification(Context context, String title, String body){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(title);
        stringBuilder.append("\n");
        stringBuilder.append(body);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, stringBuilder.toString());
        context.startActivity(intent.createChooser(intent, "Choose the app you want to share on it"));
    }


    private static NotificationCompat.Action actionShareNotification(Context context, String title, String body) {
        Intent intent = new Intent(context, NotificationService.class);
      //  intent.setAction(AppTask.ACTION_SHARE);
        intent.putExtra("TITLE_KEY", title);
        intent.putExtra("BODY_KEY", body);

        PendingIntent pendingIntent = PendingIntent.getService(
                context,
                PENDING_INTENT_ID,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT
        );
        NotificationCompat.Action action = new NotificationCompat.Action(
                R.drawable.ic_sms_indigo_a700_24dp,
                context.getString(R.string.action_settings),
                pendingIntent     );

        return action;
    }
    private static NotificationCompat.Action actionClearNotifications(Context context) {
        Intent intent = new Intent(context, NotificationService.class);
        //intent.setAction(AppTask.ACTION_CLEAR_ALL_NOTIFICATION);

        PendingIntent pendingIntent = PendingIntent.getService(context,
                PENDING_INTENT_ID,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Action action = new NotificationCompat.Action(
                R.drawable.m,
                context.getString(R.string.share),
                pendingIntent     );

        return action;
    }

*/
}
