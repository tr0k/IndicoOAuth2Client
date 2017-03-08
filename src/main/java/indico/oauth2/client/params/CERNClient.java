package indico.oauth2.client.params;

/**
 * Created by tr0k on 2016-09-01.
 */
public class CERNClient extends ClientParams{
    private static final String clientId = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx";
    private static final String clientSecretCode = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx";
    private static final String indicoURL = "https://indico.cern.ch/";
    private static final String authorizationLocation = "https://indico.cern.ch/oauth/authorize";
    private static final String tokenLocation = "https://indico.cern.ch/oauth/token";
    private static final String redirectURI = "https://mwod-demo.web.cern.ch/mwod-demo/";

    public String getClientId() {
        return clientId;
    }

    public String getClientSecretCode() {
        return clientSecretCode;
    }

    public String getIndicoURL() {
        return indicoURL;
    }

    public String getAuthorizationLocation() {
        return authorizationLocation;
    }

    public String getTokenLocation() {
        return tokenLocation;
    }

    public String getRedirectURI() {
        return redirectURI;
    }
}
