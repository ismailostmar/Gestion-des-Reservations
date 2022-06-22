package SGABSHACKALB.Gestion.des.Reservations.Security;

public class JWTUtile {
    public static final String SECRET="SecretHACKLABHACK";
    public static final String AUTH_HEADER="Authorization";
    public static final long EXPIRE_ACCESS_TOKEN=2*6*1000;
    public static final long EXPIRE_REFRESH_TOKEN=15*6*1000;
    public static final String PREFIX="Bearer ";
    public static final String REFRESH_TOKEN_REQUIRED="REFRESH TOKEN REQUIRED";
    public static  final String CONTENT_TYPE="application/json";
    public static  final String CLAIM_NAME="roles";
    public static final String ACCESS_TKN="Access_Token";
    public static final String REFRESH_TKN="Refresh_Token";
}
