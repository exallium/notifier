package ca.exallium.notifier.gntp.builders;

import ca.exallium.notifier.gntp.*;
import ca.exallium.notifier.gntp.models.NotificationType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterBuilder extends RequestBuilder<RegisterBuilder> {

    List<NotificationType> notificationList = new ArrayList<NotificationType>();

    public RegisterBuilder(String password) throws GntpException {
        super(RequestType.Register, password);
        request = new RegisterRequest(RequestType.Register, EncryptionType.None, HashAlgorithm.SHA256, password);
    }

    public RegisterBuilder withNote(NotificationType n) {
        notificationList.add(n);
        return this;
    }

    public RegisterBuilder withCount(int count) {
        request.addHeader("Notifications-Count", String.format("%d", count));
        return this;
    }

    @Override
    public void send() throws GntpException, IOException {
        ((RegisterRequest) request).sendTo(notificationList, 0, host, Constants.GNTP_PORT);
        request = new RegisterRequest(RequestType.Register, EncryptionType.None, HashAlgorithm.SHA256, password);
    }

    @Override
    protected RegisterBuilder getThis() {
        return this;
    }
}
