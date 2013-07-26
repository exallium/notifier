package ca.exallium.notifier;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class NotificationListenerService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
