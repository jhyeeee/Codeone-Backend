package com.codeone.socialLogin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//구글(서드파티)로 액세스 토큰을 보내 받아올 구글에 등록된 사용자 정보
@AllArgsConstructor
@Getter
@Setter
public class NaverUser {
    public String id;
    public String nickname;
    public String name;
    public String email;
    public String gender;
    public String age;
    public String mobile;
    public String profileimage;

}