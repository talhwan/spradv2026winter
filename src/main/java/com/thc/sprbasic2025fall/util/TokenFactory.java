package com.thc.sprbasic2025fall.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public class TokenFactory {

    static int refreshTokenValidityHour = 2;

    // 일단, 리프레시 토큰 만들기
    public static String createRefreshToken(Long userId) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("1. now : " + now);
        now = now.plusHours(refreshTokenValidityHour);
        System.out.println("2. now : " + now);
        String token = null;
        String info = userId + "_" + now;
        try{
            token = AES256Cipher.AES_Encode(null, info);
        } catch (Exception e){}

        System.out.println("3. token : " + token);

        return token;
    }

    public static Long validateToken(String token) {
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
