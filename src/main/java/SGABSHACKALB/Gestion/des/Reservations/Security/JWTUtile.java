package SGABSHACKALB.Gestion.des.Reservations.Security;

public class JWTUtile {
    public static String SECRET="SecretHACKLABHACK";
    public static String AUTH_HEADER="Authorization";
    public static long EXPIRE_ACCESS_TOKEN=80*6*1000;
    public static long EXPIRE_REFRESH_TOKEN=300*6*1000;
    public static String PREFIX="Bearer ";
    public static String REFRESH_TOKEN_REQUIRED="REFRESH TOKEN REQUIRED";
    public static String CONTENT_TYPE="application/json";
    public static String ACCESS_TKN="Access_Token";
    public static String REFRESH_TKN="Refresh_Token";
}
