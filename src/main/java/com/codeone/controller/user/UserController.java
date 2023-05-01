package com.codeone.controller.user;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.codeone.dto.user.UserDto;
import com.codeone.etc.TempKey;
import com.codeone.service.user.UserService;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;


@RestController
@RequestMapping(value="/user")
public class UserController {

	// userService 연결
	@Autowired
	UserService service;

	
	// 회원가입 이메일 중복체크
	@GetMapping(value = "/checkingEmail")
	public ResponseEntity<String> checkingEmail(@RequestParam String email) throws Exception {
		System.out.println(email + " checkingEmail");
		if (service.checkEmail(email)) { // 중복된 이메일이 있음
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
		}

		// 중복된 아이디 없음
		return ResponseEntity.ok().build();
	}


	// Id 체크 로직
	@GetMapping(value = "/checkingId")
	public ResponseEntity<String> checkingId(@RequestParam String id) throws Exception {
		System.out.println(id + " checkingId");
		boolean isIdCheck = service.checkId(id);
		if (isIdCheck) { // 중복된 아이디가 있음
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
		}
		// 아이디가 없으면
		return ResponseEntity.ok().build();
	}
	
	// 회원가입 페이지 이동 react 프론트에서 이메일 인증 넣어주기
	// 회원가입
	@PostMapping(value = "/addUser")
	public String addUser(UserDto dto) {
		System.out.println("userController addUser() " + new Date());
		
		// 넘어온 값 확인 
		System.out.println(dto);
		
		// 이메일 중복체크
		boolean isEmailCheck = service.checkEmail(dto.getEmail());
		if (isEmailCheck) { // 중복된 이메일이 있음
			return "DUPLICATED_EMAIL";
		}
		
		// 중복 이메일 없을 때	
		
		// 회원가입
		boolean isSignupSuccess = service.addUser(dto);
		
		// 이메일인증여부 업데이트
		service.updateEmailAuth(dto);
		
		if (isSignupSuccess == false) { // 회원가입 실패
			return "NO";
		}	
		
		return "YES"; // 회원가입 성공
	}


	@PostMapping(value = "/sendSignUpEmail")
	public ResponseEntity<String> sendSignUpEmail(UserDto dto) throws Exception {
		System.out.println("userController sendSignUpEmail() " + new Date());
		System.out.println(dto.getEmail());
		// 이메일 중복체크
		boolean isEmailCheck = service.checkEmail(dto.getEmail());
		if (isEmailCheck) { // 중복된 이메일이 있음
			HttpHeaders header = new HttpHeaders();
	        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
		}
		
		// 중복 이메일이 아닌 이메일이면 메일발송
		service.sendSignUpEmail(dto.getEmail());
		return ResponseEntity.ok().build(); 
	}


	@GetMapping(value = "/signUpemailAf")
	public void signUpemailAf(String email, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.sendRedirect("http://localhost:3000/signupinfo?email="+ email);
	}
	
	
	// signUpAf 만들어주기
	@PostMapping(value = "/signUp")
	public void signUpemail(String email,HttpServletRequest request, HttpServletResponse response) throws Exception {

		// DB에 회원을
		UserDto user = new UserDto();
		user.setEmail(email);
		String emailKey = new TempKey().getKey(10, false); // 랜덤키 길이 설정
		System.out.println(emailKey);		
		user.setEmailKey(emailKey);
		if(service.addUser(user)) {
			HttpSession httpSession = request.getSession();
	        httpSession.setAttribute("user", user);
			
			response.sendRedirect("http://localhost:3000");
		}else {
			response.sendRedirect("http://localhost:3000/error");
		}
		
		
	}

	// regi 만들어주기
	@PostMapping(value = "/regi")
	public ResponseEntity<String> regi(String id, String name, String email, String phoneNumber, String profileUrl, MultipartFile multipartFile,HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("/regi");
		UserDto user = new UserDto();
		user.setId(id);
		user.setName(name);
		user.setEmail(email);
		
		//파일이 null일경우(회원가입시 프로필 사진을 변경 안했을 경우)
		if(Objects.isNull(multipartFile)) {
			user.setFilename(profileUrl);
		} else {
			String filename = saveProfilePicture(request, multipartFile);
			user.setFilename(filename);
		}		
		
		String emailKey = new TempKey().getKey(10, false); 
		user.setEmailKey(emailKey);
		if (service.checkEmail(user.getEmail())) { // 중복된 이메일이 있음
			HttpHeaders header = new HttpHeaders();
	        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
	        response.sendRedirect("http://localhost:3000");
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
		}

		if(service.addUser(user)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			UserDto newUser = (UserDto)session.getAttribute("user");
			System.out.println(newUser.toString() + " in session");
	        return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.status(500).build();
		}		
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<String> login(String email) throws Exception {
		System.out.println("userController login() " + new Date());
		System.out.println(email);
		// 이메일넣고 회원정보 불러오기
		UserDto dto = service.getMember(email);
		
		if(dto == null) {		// 가입정보가 없을 때
			return ResponseEntity.noContent().build();	
		}		
		
		// 가입된 이메일이 있을 때
		// 인증키 생성
		String emailKey = new TempKey().getKey(10, false); // 랜덤키 길이 설정
		System.out.println(emailKey);
		
		// 메일키 회원정보에 업데이트 시켜주기	
		dto.setEmailKey(emailKey);
		service.updateEmailKey(dto);
		
		// 이메일로 로그인 메일 전송
		service.sendLoginEmail(email, emailKey);
		
		// 이메일로 링크 전송후 프론트에서 메일키 맞는지 확인하고 로그인 시키고 메일주소에 정보 지워줌.

		return ResponseEntity.ok().build();
	}
	
	// loginAf 만들어주기
	@GetMapping(value = "/loginAf")
	public void loginAf(String email, String emailKey, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(email);
		System.out.println(emailKey);
		UserDto user = service.getMember(email);
		if(Objects.isNull(user)) {
			request.getSession().invalidate();
			response.sendRedirect("http://localhost:3000/error/nouser");
			return;
		}
		// 메일키 회원정보에 업데이트 시켜주기	
		user.setEmailKey(emailKey);
		service.updateEmailKey(user);
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("user"));
		session.setAttribute("user", user);
		response.sendRedirect("http://localhost:3000");		
	}
	
	@GetMapping(value="/logout")
	public ResponseEntity<UserDto> logout(HttpServletRequest request) {
		HttpSession session =  request.getSession();
		if(session != null) {
			System.out.println("logout");
			session.removeAttribute("user");
			session.invalidate();			
		}
		return ResponseEntity.ok().build();
	}
	    
    @GetMapping(value="/getSessionUser")
    public ResponseEntity<UserDto> getSessionUser(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("getSessionUser()" + new Date());
    	HttpSession session = request.getSession();
    	UserDto user = (UserDto)session.getAttribute("user");
    	if(user != null) {    		
    		System.out.println(user + " getSesssionUser");
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        	return ResponseEntity.ok()
    				.headers(header)
    				.body(user); 
    	} else {
    		return ResponseEntity.noContent().build();
    	}    	
    }
    
    
 // 이미지 불러오기
 	@GetMapping("/getImage/{fileId}/{fileType}")
 	public ResponseEntity<byte[]> getImageFile(@PathVariable String fileId, @PathVariable String fileType, HttpServletRequest req) {
 		System.out.println(" GetImageFile???");
 		String path = req.getServletContext().getRealPath("/images/tempProfileImage");
 		try {
 			FileInputStream fis = new FileInputStream(path + "\\" + fileId + "." + fileType);
 			ByteArrayOutputStream baos = new ByteArrayOutputStream();
 			
 			byte buffer[] = new byte[1024];
 			int length = 0;
 			
 			while((length = fis.read(buffer)) != -1) {
 				baos.write(buffer, 0, length);
 			}
 			
 			return new ResponseEntity<byte[]>(baos.toByteArray(), HttpStatus.OK);
 			
 		} catch(IOException e) {
 			return new ResponseEntity<byte[]>(new byte[] {}, HttpStatus.CONFLICT);
 		}
 	}
    
    
    // 프로필 체인지
    @PostMapping(value = "/profileChange")
    public ResponseEntity<UserDto> profileChange(String id, String email, MultipartFile multipartFiles, HttpServletRequest req) throws Exception {
    	String filename = "";
    	boolean noPicture = Objects.isNull(multipartFiles);
    	System.out.println(noPicture + " ???????????????????");
    	// 이미지 프로필 수정한경우
		if(!noPicture) {
			filename = saveProfilePicture(req, multipartFiles);
		}
		// 예전 프로필		
		UserDto user = service.getMember(email);
		// 새프로필
		if(!noPicture) {
			user.setFilename(filename);
		}
		user.setId(id);
		
		// 업데이트 하고 결과 확인
		if(service.profileUpdate(user) < 0 ) {
			return ResponseEntity.notFound().build();
		};
		
		user = service.getMember(email);
		req.getSession().invalidate();
		req.getSession().setAttribute("user", user);
		UserDto changedUser = (UserDto)req.getSession().getAttribute("user");
		System.out.println("=================중요=========================================================");
		System.out.println(changedUser.toString());

		
		HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
     	return ResponseEntity.ok()
 				.headers(header)
 				.body(user);
    }
    
    @GetMapping(value="/getProfileImage/{fileId}/{fileType}")
	public ResponseEntity<byte[]> getProfileImage(HttpServletRequest req, @PathVariable String fileId, @PathVariable String fileType) {
		System.out.println(" GetImageFile???");
		String path = req.getServletContext().getRealPath("/images/tempProfileImage");
		System.out.println(path);
		try {
			FileInputStream fis = new FileInputStream(path + "\\" + fileId + "." + fileType);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			byte buffer[] = new byte[1024];
			int length = 0;
			
			while((length = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, length);
			}
			
			return new ResponseEntity<byte[]>(baos.toByteArray(), HttpStatus.OK);
			
		} catch(IOException e) {
			return new ResponseEntity<byte[]>(new byte[] {}, HttpStatus.CONFLICT);
		}
	}
	
	private String saveProfilePicture(HttpServletRequest req, MultipartFile multipartFiles) throws Exception{
		// 이미지 저장
		String path = req.getServletContext().getRealPath("/images/tempProfileImage");
		MultipartFile file = multipartFiles;
		
		String fileId = (new Date().getTime()) + "" + (new Random().ints(1000, 9999).findAny().getAsInt()); // 현재 날짜와 랜덤 정수값으로 새로운 파일명 만들기
		String originName = file.getOriginalFilename(); // ex) 파일.jpg
		String fileExtension = originName.substring(originName.lastIndexOf(".") + 1); // ex) jpg
		originName = originName.substring(0, originName.lastIndexOf(".")); // ex) 파일
		
		File fileSave = new File(path, fileId + "." + fileExtension); // ex) fileId.jpg
//		File fileSave = new File("http://localhost/user/getProfileImage/"+ fileId + "/" + fileExtension); // ex) fileId.jpg
		if(!fileSave.exists()) { // 폴더가 없을 경우 폴더 만들기
			fileSave.mkdirs();
		}        
		file.transferTo(fileSave);
		String filename = "http://localhost/user/getProfileImage/"+ fileId + "/" + fileExtension;
		
		return filename;
	}
    
}
