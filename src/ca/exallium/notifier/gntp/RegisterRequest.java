package ca.exallium.notifier.gntp;

import ca.exallium.notifier.gntp.models.NotificationType;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

public class RegisterRequest extends Request {

    List<NotificationType> notificationTypeList;

    public RegisterRequest(RequestType type, EncryptionType encryption, HashAlgorithm algorithm, String password) throws GntpException {
        super(type, encryption, algorithm, password);
    }

    public void sendTo(List<NotificationType> notifications, long connectionID, String host, int port) throws GntpException, UnknownHostException, IOException {
        notificationTypeList = notifications;
        super.sendTo(connectionID, host, port);
    }

    @Override
    public void write(ChannelWriter writer) throws IOException {
        super.write(writer);
        for (NotificationType type : notificationTypeList) {
            writer.write(Constants.END_OF_LINE);
            for (String key : type.getMap().keySet())
                writeHeader(writer, key, type.getMap().get(key));
        }
        writer.write(Constants.END_OF_LINE);
    }
}
