package ca.exallium.notifier.gntp.models;

import java.util.HashMap;

public class NotificationType {

    private HashMap<String, String> headers = new HashMap<String, String>();

    protected NotificationType() {}

    public static class Builder {

        NotificationType notification;

        public Builder() {
            notification = new NotificationType();
        }

        public Builder withName(String name) {
            notification.headers.put("Notification-Name", name);
            return this;
        }
        public Builder withDisplayName(String displayName) {
            notification.headers.put("Notification-Display-Name", displayName);
            return this;
        }

        public NotificationType build() {
            return notification;
        }

    }

    public HashMap<String, String> getMap() {
        return headers;
    }

}
