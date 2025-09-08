package org.project_kessel.api.auth;

import com.nimbusds.oauth2.sdk.GeneralException;
import com.nimbusds.oauth2.sdk.as.AuthorizationServerMetadata;
import com.nimbusds.oauth2.sdk.id.Issuer;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;

public class OIDCDiscovery {

    private OIDCDiscovery() {}

    public static OIDCDiscoveryMetadata fetchOIDCDiscovery(String issuerUrl) throws OAuth2Exception {
        try {
            // Check if Nimbus OAuth library is available
            Class.forName("com.nimbusds.oauth2.sdk.as.AuthorizationServerMetadata");
            Objects.requireNonNull(issuerUrl, "issuerUrl must not be null");

            Issuer issuer = new Issuer(URI.create(issuerUrl));
            AuthorizationServerMetadata metadata = AuthorizationServerMetadata.resolve(issuer);

            if (metadata.getTokenEndpointURI() == null) {
                throw new OAuth2Exception("Token endpoint could not be discovered from issuer URL");
            }

            return new OIDCDiscoveryMetadata(metadata.getTokenEndpointURI().toString());

        } catch (ClassNotFoundException e) {
            throw new OAuth2Exception(
                "OIDC discovery requires Nimbus OAuth library. " +
                "Add dependency: com.nimbusds:oauth2-oidc-sdk"
            );
        } catch (IOException e) {
            throw new OAuth2Exception("Failed to fetch OIDC discovery metadata from: " + issuerUrl, e);
        } catch (com.nimbusds.oauth2.sdk.ParseException e) {
            throw new OAuth2Exception("Failed to parse OIDC discovery response from: " + issuerUrl, e);
        } catch (GeneralException e) {
            throw new OAuth2Exception("Failed to discover token endpoint from:" + issuerUrl, e);
        }
    }

}
