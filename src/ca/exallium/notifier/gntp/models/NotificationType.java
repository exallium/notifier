package ca.exallium.notifier.gntp.models;

import java.util.HashMap;

public class NotificationType {

    private HashMap<String, String> headers;

    protected NotificationType(HashMap<String, String> map) {
        headers = new HashMap<String, String>(map);
    }

    public static class Builder {

        private HashMap<String, String> headers = new HashMap<String, String>();

        public Builder withName(String name) {
            headers.put("Notification-Name", name);
            return this;
        }
        public Builder withDisplayName(String displayName) {
            headers.put("Notification-Display-Name", displayName);
            return this;
        }

        public NotificationType build() {
            return new NotificationType(headers);
        }

    }

    public HashMap<String, String> getMap() {
        return headers;
    }

}
