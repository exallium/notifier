package ca.exallium.notifier.gntp.builders;

import ca.exallium.notifier.gntp.*;
import ca.exallium.notifier.gntp.models.NotificationType;

import java.io.IOException;

/**
 * This is not designed to support subscribe, as this is a feature we are uninterested in.
 *
 * Encapsulates a request object and adds methods to easily build notifications. These objects are
 * reusable, and are 'reset' after each send.
 *
 * Instead of build, we use send, as the request object is never handed back.
 */
public abstract class RequestBuilder<T extends RequestBuilder> {

    protected Request request;
    protected String host;

    protected RequestType type;
    protected String password;


    protected RequestBuilder(RequestType type, String password) throws GntpException {
        this.type = type;
        this.password = password;
        request = new Request(type, EncryptionType.None, HashAlgorithm.SHA256, password);
    }

    public T forApp(String name) {
        request.addHeader("Application-Name", name);
        return getThis();
    }

    public T withNote(NotificationType n) {
        for(String h : n.getMap().keySet()) {
            request.addHeader(h, n.getMap().get(h));
        }
        return getThis();
    }

    public T withHost(String host) {
        this.host = host;
        return getThis();
    }

    public void send() throws GntpException, IOException {
        request.sendTo(0, host, Constants.GNTP_PORT);
        request = new Request(this.type, EncryptionType.None, HashAlgorithm.SHA256, this.password);
    }

    protected abstract T getThis();
}
