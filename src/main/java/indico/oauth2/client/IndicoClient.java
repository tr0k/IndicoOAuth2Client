package indico.oauth2.client;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.ResponseType;

/**
 * Created by tr0k on 2016-08-22.
 */
public class IndicoClient {

    /**
     * Send code to request access token
     */
    public OAuthClientRequest getAccessTokenRequest(String clientId, String clientSecretCode,
                                                            String redirectURI, String authCode, String tokenLocation)
            throws OAuthSystemException {
        OAuthClientRequest request;
        request = OAuthClientRequest
                .tokenLocation(tokenLocation)
                .setGrantType(GrantType.AUTHORIZATION_CODE)
                .setClientId(clientId)
                .setClientSecret(clientSecretCode)
                .setRedirectURI(redirectURI)
                .setCode(authCode)
                .buildQueryMessage();
        return request;
    }

    /**
     * End user authorization request
     */
    public OAuthClientRequest getAuthorizationRequest(String clientId, String redirectURI, String
            authorizationLocation)
            throws OAuthSystemException {
        return OAuthClientRequest
                .authorizationLocation(authorizationLocation)
                .setClientId(clientId)
                .setRedirectURI(redirectURI)
                .setResponseType(ResponseType.CODE.toString())
                .setScope("read:user")
                .buildQueryMessage();
    }

    public OAuthResourceResponse getProtectedResources(OAuthClient oAuthClient, String accessToken, String url)
            throws OAuthSystemException, OAuthProblemException {
        OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(url).setAccessToken(accessToken)
                .buildQueryMessage();
        return oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
    }
}