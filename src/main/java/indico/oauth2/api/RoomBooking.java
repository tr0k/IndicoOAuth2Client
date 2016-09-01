package indico.oauth2.api;

import indico.oauth2.client.params.CERNClient;
import indico.oauth2.client.params.ClientParams;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

/**
 * Created by tr0k on 2016-09-01.
 */
public class RoomBooking {

    public OAuthResourceResponse getRoomData(OAuthClient oAuthClient, String accessToken, String roomId)
            throws OAuthSystemException, OAuthProblemException {
        ClientParams clientParams = new CERNClient();
        StringBuilder url = new StringBuilder(clientParams.getIndicoURL());
        url.append("/export/room/CERN/").append(roomId).append(".json");

        OAuthClientRequest bearerClientRequest =
                new OAuthBearerClientRequest(url.toString()).setAccessToken(accessToken).buildQueryMessage();

        return oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
    }
}
