package ca.exallium.notifier;

import android.app.Activity;
import android.os.Bundle;
import ca.exallium.notifier.gntp.*;
import ca.exallium.notifier.gntp.builders.NotifyBuilder;
import ca.exallium.notifier.gntp.builders.RegisterBuilder;
import ca.exallium.notifier.gntp.models.NotificationType;

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
                    RegisterBuilder regBuilder = new RegisterBuilder("notasecret");
                    NotifyBuilder notify = new NotifyBuilder("notasecret");

                    NotificationType.Builder builder = new NotificationType.Builder();
                    NotificationType type = builder.withName("Notify 1").withDisplayName("Title").build();

                    regBuilder.forApp("Test").withCount(1).withHost(ip).withNote(type).send();
                    notify.forApp("Test").withNote(type).withTitle("Title").withText("Text").withHost(ip).send();
                    notify.forApp("Test").withNote(type).withTitle("Title").withText("Text 2").withHost(ip).send();
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
