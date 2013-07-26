package ca.exallium.notifier;

import android.app.Activity;
import android.os.Bundle;
import ca.exallium.notifier.gntp.*;

import java.io.IOException;
import java.net.UnknownHostException;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                String ip = "172.16.10.164";

                try {
                    Request request = new Request(RequestType.Notify, EncryptionType.None, HashAlgorithm.SHA256, "notasecret");
                    request.addHeader("Application-Name", "Test");
                    request.addHeader("Notifications-Count", "1");
                    request.addHeader("Notification-Name", "Notify 1");
                    request.sendTo(1, ip, Constants.GNTP_PORT);

                    request = new Request(RequestType.Notify, EncryptionType.None, HashAlgorithm.SHA256, "notasecret");
                    request.addHeader("Application-Name", "Test");
                    request.addHeader("Notification-Name", "Notify 1");
                    request.addHeader("Notification-Title", "Hello");
                    request.sendTo(1, ip, Constants.GNTP_PORT);

                    request = new Request(RequestType.Notify, EncryptionType.None, HashAlgorithm.SHA256, "notasecret");
                    request.addHeader("Application-Name", "Test");
                    request.addHeader("Notification-Name", "Notify 1");
                    request.addHeader("Notification-Title", "Hello");
                    request.sendTo(1, ip, Constants.GNTP_PORT);
                } catch (GntpException e) {
                    e.printStackTrace();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        t.start();

    }
}
