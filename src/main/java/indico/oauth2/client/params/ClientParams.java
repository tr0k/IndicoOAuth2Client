package indico.oauth2.client.params;

/**
 * Created by tr0k on 2016-09-01.
 */
public abstract class ClientParams {
    public abstract String getClientId();

    public abstract String getClientSecretCode();

    public abstract String getIndicoURL();

    public abstract String getAuthorizationLocation();

    public abstract String getTokenLocation();

    public abstract String getRedirectURI();
}
