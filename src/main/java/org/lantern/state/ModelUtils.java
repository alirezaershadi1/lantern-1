package org.lantern.state;

import org.littleshoot.commom.xmpp.XmppCredentials;

/**
 * Interface for utility methods depending on the state model.
 */
public interface ModelUtils {

    boolean shouldProxy();

    void loadClientSecrets();

    boolean isConfigured();

    boolean isOauthConfigured();

    XmppCredentials newGoogleOauthCreds(String resource);

    boolean isInClosedBeta(String email);

    void addToClosedBeta(String to);

    void syncConnectingStatus(String msg);

}
