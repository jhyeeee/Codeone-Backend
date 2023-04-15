package com.codeone.etc;

import java.util.Random;

//인증번호를 보낼 때 사용할 클래스 
// 이 클래스를 호출할 때는 몇 자리 수로 할 건지 사이즈를 파라미터로 보내면 됨

public class TempKey{
    private boolean lowerCheck;		// 대문자를 소문자로 바꿀 것인지 여부 설정	true == 소문자, false == 그대로반환
    private int size;				// 몇개로 뽑을 건지여부

    public String getKey(int size, boolean lowerCheck) {	// 몇자리인지, 소문자 대문자인지 값 넣어주기
        this.size = size;
        this.lowerCheck = lowerCheck;
        return init();
    }

    private String init() {
        Random ran = new Random();			// 랜덤 난수
        StringBuffer sb = new StringBuffer();	// 문자열 저장해 줄 곳
        int num  = 0;
        do {								// 아스키 코드에서 48은 문자 0의 코드값, 122는 문자 Z의 코드값			
            num = ran.nextInt(75) + 48;		// 0 - 74까지의 임의의 정수 생성 + 48. 48~122까지의 ASCII문자 코드 값 생성
            if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
                sb.append((char) num);		// 이 사이에 있는 숫자들의 값이 나오면 문자로 변환해서 저장
            } else {
                continue;
            }
        } while (sb.length() < size);		// 들어온 값보다 작을때 까지 반복
        if (lowerCheck) {			// lowerCheck가 true이면 소문자로 변환
            return sb.toString().toLowerCase();
        }
        return sb.toString();		
    }
}
