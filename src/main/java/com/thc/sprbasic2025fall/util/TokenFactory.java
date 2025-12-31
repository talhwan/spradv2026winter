package com.thc.sprbasic2025fall.util;

import com.thc.sprbasic2025fall.domain.RefreshToken;
import com.thc.sprbasic2025fall.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class TokenFactory {

    final RefreshTokenRepository refreshTokenRepository;

    static int refreshTokenValidityHour = 12;
    static int accessTokenValidityTerm = 1;

    public String createToken(Long userId, int termHour) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("1. now : " + now);

        //now = now.plusHours(termHour);
        // 엑세스 토큰 엄청 줄이기!
        if(termHour == accessTokenValidityTerm) {
            now = now.plusMinutes(1);
        } else {
            now = now.plusHours(termHour);
        }

        System.out.println("2. now : " + now);
        String token = null;
        String info = userId + "_" + now;
        try{
            token = AES256Cipher.AES_Encode(null, info);
        } catch (Exception e){}
        System.out.println("3. token : " + token);
        return token;
    }
    // 일단, 리프레시 토큰 만들기
    public String createRefreshToken(Long userId) {
        return createToken(userId, refreshTokenValidityHour);
    }
    // 엑세스 토큰 만들기
    public String createAccessToken(String refreshToken) {
        Long userId = validateToken(refreshToken); //리프레시 토큰

        RefreshToken entity = refreshTokenRepository.findByContent(refreshToken);
        if(entity == null){
            return null;
        }
        Long userIdFromToken = entity.getUserId();
        System.out.println("userIdFromToken : " + userIdFromToken);
        if(!userIdFromToken.equals(userId)){
            return null;
        }

        System.out.println("userId : " + userId);
        if(userId == null){
            return null;
        }
        return createToken(userId, accessTokenValidityTerm);
    }

    // 엑세스 토큰 검증하기
    public Long validateAccessToken(String token) {
        Long userId = validateToken(token);
        if(userId == null) {
            throw new RuntimeException("please check your RefreshToken");
        }
        return userId; //엑세스토큰 검증!
    }

    public Long validateToken(String token) {
        String info = null;
        try{
            info = AES256Cipher.AES_Decode(null, token);

            String[] array_info = info.split("_");
            Long userId = Long.parseLong(array_info[0]);
            LocalDateTime now = LocalDateTime.now();
            String due = array_info[1];
            String nowTime = now.toString();

            String[] tempArray = {due, nowTime};
            Arrays.sort(tempArray);
            // asc 로 정렬하네!
            // 앞에 있는 것이 현재 시간이어야만 하네!
            // tempArray[0] === nowTime?!
            if(nowTime.equals(tempArray[0])){
                return userId;
            }
        } catch (Exception e){}

        return null;
    }

}
