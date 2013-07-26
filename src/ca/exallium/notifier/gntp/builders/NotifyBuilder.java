package ca.exallium.notifier.gntp.builders;

import ca.exallium.notifier.gntp.GntpException;
import ca.exallium.notifier.gntp.Request;
import ca.exallium.notifier.gntp.RequestType;

public class NotifyBuilder extends RequestBuilder<NotifyBuilder> {

    public NotifyBuilder(String password) throws GntpException {
        super(RequestType.Notify, password);
    }

    public NotifyBuilder withText(String text) {
        request.addHeader("Notification-Text", text);
        return this;
    }

    public NotifyBuilder withTitle(String title) {
        request.addHeader("Notification-Title", title);
        return this;
    }

    public NotifyBuilder withDisplayName(String name) {
        request.addHeader("Notification-Display-Name", name);
        return this;
    }

    public NotifyBuilder enabled(boolean isEnabled) {
        request.addHeader("Notification-Enabled", String.format("%s", isEnabled));
        return this;
    }

    public NotifyBuilder withIcon(String uri) {
        request.addHeader("Notification-Icon", uri);
        return this;
    }

    @Override
    protected NotifyBuilder getThis() {
        return this;
    }
}
