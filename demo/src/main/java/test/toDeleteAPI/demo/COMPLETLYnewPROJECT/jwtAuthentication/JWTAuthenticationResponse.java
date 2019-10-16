package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.jwtAuthentication;

public class JWTAuthenticationResponse {

    private String accessToken;

    public JWTAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
