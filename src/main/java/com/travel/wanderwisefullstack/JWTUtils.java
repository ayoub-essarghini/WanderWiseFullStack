package com.travel.wanderwisefullstack;

public class JWTUtils {
    public static final String JWT_SECRET = "mySecretKey1234";
    public static final int TIME_OUT_ACCESS_TOKEN = (50 * 60) * 1000;
    public static final Long TIME_OUT_REFRESH_TOKEN = 500000000L * (60 * 1000);
}
