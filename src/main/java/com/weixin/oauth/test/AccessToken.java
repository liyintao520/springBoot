package com.weixin.oauth.test;

/**
 * @author maz
 * 微信获取accessToken
 */
public class AccessToken {

	// 接口访问凭证
    private String accessToken;
    
    // 凭证有效期，单位：秒
    private int expiresIn;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
    
}
