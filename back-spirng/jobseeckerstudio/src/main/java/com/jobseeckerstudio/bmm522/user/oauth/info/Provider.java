package com.jobseeckerstudio.bmm522.user.oauth.info;

public enum Provider {
    Google("Google"), Kakao("Kakao");

    private final String provider;

    Provider(final String provider){
        this.provider = provider;
    }

    public String getProvider(){
        return provider;
    }

}
