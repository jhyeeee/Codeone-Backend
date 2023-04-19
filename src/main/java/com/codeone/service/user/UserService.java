package com.codeone.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.user.UserDao;
import com.codeone.dto.user.UserDto;

import jakarta.mail.internet.MimeMessage;

@Service
@Transactional
public class UserService {
	
	// userDao 연결
	@Autowired
	UserDao dao;
	
	// 메일
	@Autowired
    private JavaMailSender mailSender;
	
	public boolean checkEmail(String email) {
		int n = dao.checkEmail(email);
		return n>0?true:false;
	}
	
	public boolean checkId(String id) {
		int n = dao.checkId(id);
		return n>0?true:false;
	}
	

	public boolean addUser(UserDto dto) {
		int n = dao.addUser(dto);
		return n>0?true:false;
	}
	
	// 회원가입 이메일 발송
    public void sendEmail(String email, String emailKey) throws Exception {
    	
    	// 메일 회원가입 이동 링크 수정
    	String mailContent = "<div style='text-align:center;'>" +
                "<h1 style='color:black;'>CodeOne 회원가입</h1>" +
                "<p style='font-size:18px; color:#333; margin-top:30px; margin-bottom:20px;'>안녕하세요</p>" +
                "<p style='font-size:16px; color:#555; margin-top:20px; margin-bottom:30px;'>회원가입을 진행하려면 아래 링크를 클릭해주세요</p>" +
                "<a href='http://localhost:3000/signup?email=" + email + "&emailKey=" + emailKey + "' style='display:inline-block; background-color:#FF4136; color:#fff; font-size:16px; text-align:center; padding:12px 20px; border-radius:5px; text-decoration:none; margin-bottom:30px;'>계속하기</a>" +
                "<p style='font-size:14px; color:#999; margin-top:30px;'>본 이메일은 발신 전용입니다. 문의 사항은 고객센터를 이용해주세요.</p>" +
                "<p style='font-size:14px; color:#999;'>CodeOne | 서울특별시 강남구 테헤란로 1234</p>" +
                "</div>";
    	MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("codeone.verify@gmail.com", "CodeOne");
        helper.setTo(email);
        helper.setSubject("CodeOne 회원가입");
        helper.setText(mailContent, true);
        mailSender.send(message);
    }
    
    
    // 로그인 이메일 발송
    public void sendLoginEmail(String email, String emailKey) throws Exception {
    	// 로그인시키고 메인페이지 이동
    	// mailContent 링크 수정
    	String mailContent = "<div style='text-align:center;'>" +
                "<h1 style='color:black;'>CodeOne 로그인</h1>" +
                "<p style='font-size:18px; color:#333; margin-top:30px; margin-bottom:20px;'>안녕하세요</p>" +
                "<p style='font-size:16px; color:#555; margin-top:20px; margin-bottom:30px;'>로그인을 계속하시려면 하단의 링크를 클릭하세요</p>" +
                "<a href='http://localhost:3000/codeone/loginAf?email=" + email + "&emailKey=" + emailKey + "' style='display:inline-block; background-color:#FF4136; color:#fff; font-size:16px; text-align:center; padding:12px 20px; border-radius:5px; text-decoration:none; margin-bottom:30px;'>계속하기</a>" +
                "<p style='font-size:14px; color:#999; margin-top:30px;'>본 이메일은 발신 전용입니다. 문의 사항은 고객센터를 이용해주세요.</p>" +
                "<p style='font-size:14px; color:#999;'>CodeOne | 서울특별시 강남구 테헤란로 1234</p>" +
                "</div>";
    	MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("codeone.verify@gmail.com", "CodeOne");
        helper.setTo(email);
        helper.setSubject("CodeOne 로그인");
        helper.setText(mailContent, true);
        mailSender.send(message);
		
	}
    
    public UserDto getMember(String email) {
		return dao.getMember(email);
	}
    
    // 회원정보에 메일키 업데이트
    public int updateEmailKey(UserDto dto) {
		return dao.updateEmailKey(dto);
	}
    
    // 회원정보에 메일인증여부 업데이트
    public int updateEmailAuth(UserDto dto) {
		return dao.updateEmailAuth(dto);
	}
}








