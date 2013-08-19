package ca.exallium.notifier;

import android.app.Notification;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import ca.exallium.notifier.gntp.GntpException;
import ca.exallium.notifier.gntp.builders.NotifyBuilder;
import ca.exallium.notifier.gntp.builders.RegisterBuilder;
import ca.exallium.notifier.gntp.models.NotificationType;

import java.io.IOException;
import java.net.UnknownHostException;

public class NotificationListener extends NotificationListenerService {

    private final static String APP_NAME = "Notifier";

    String ip = "172.16.10.164";

    @Override
    public void onNotificationPosted(final StatusBarNotification sbn) {

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {

                    String host = getApplicationContext()
                            .getSharedPreferences(APP_NAME, MODE_PRIVATE)
                            .getString("host", "172.16.10.164");

                    String password = getApplicationContext()
                            .getSharedPreferences(APP_NAME, MODE_PRIVATE)
                            .getString("password", "notasecret");

                    RegisterBuilder regBuilder = new RegisterBuilder(password);
                    NotifyBuilder notify = new NotifyBuilder(password);

                    NotificationType.Builder builder = new NotificationType.Builder();
                    NotificationType type = builder.withName(sbn.getPackageName()).withDisplayName(sbn.getTag()).build();
                    regBuilder.forApp(APP_NAME).withCount(1).withHost(ip).withNote(type).send();

                    notify.forApp(APP_NAME).withNote(type)
                            .withTitle(sbn.getTag())
                            .withText(sbn.getNotification().tickerText.toString())
                            .withHost(ip).send();
                } catch (GntpException e) {
                    e.printStackTrace();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
