package indico.oauth2;

import indico.oauth2.client.IndicoClient;
import indico.oauth2.client.params.CERNClient;
import indico.oauth2.client.params.ClientParams;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by tr0k on 2016-09-01.
 */
public class IndicoMain {
    public static void main(String[] args) throws OAuthSystemException, IOException {
        ClientParams clientParams = new CERNClient();

        IndicoClient client = new IndicoClient();

        OAuthClientRequest request = client.getAuthorizationRequest(clientParams.getClientId(),
                clientParams.getRedirectURI(), clientParams.getAuthorizationLocation());

        //in web application we make redirection to following uri:
        System.out.println("Visit: " + request.getLocationUri() + "\nand grant permission");

        //Receive and input code from fb service
        System.out.print("Now enter the OAuth code you have received in redirect uri ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String authCode = br.readLine();
        System.out.println("Received code: " + authCode);

        request = client.getAccessTokenRequest(clientParams.getClientId(), clientParams.getClientSecretCode(),
                clientParams.getRedirectURI(), authCode, clientParams.getTokenLocation());

        //Get protected resources from Indico

        //create OAuth client that uses custom http client under the hood
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

        OAuthJSONAccessTokenResponse oAuthResponse = null;
        try {
            oAuthResponse = oAuthClient.accessToken(request);

            System.out.println(
                    "Access Token: " + oAuthResponse.getAccessToken() + ", Expires in: " + oAuthResponse
                            .getExpiresIn());

            StringBuilder url = new StringBuilder(clientParams.getIndicoURL())
                    .append("/export/room/CERN/57.json?pretty=yes");

            System.out.println("Resources: " + client.getProtectedResources(oAuthClient, oAuthResponse.getAccessToken
                    (), url.toString()).getBody());

        } catch (OAuthProblemException e) {
            e.printStackTrace();
        }

    }
}
