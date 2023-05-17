  package com.codeone.controller.user;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codeone.controller.naver.NaverCloud;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class NaverCloudController {

	// STT : 음성인식 wav -> string
	@PostMapping(value = "fileUpload")
	public String fileUpload(@RequestParam("uploadFile")MultipartFile uploadFile, 
							 HttpServletRequest req) {
		System.out.println("NaverCloudController STT " + new Date());
		
		String uploadpath = req.getServletContext().getRealPath("/upload");
		
		String filename = uploadFile.getOriginalFilename();
		String filepath = uploadpath + "/" + filename;
		
		System.out.println(filepath);
		
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			os.write(uploadFile.getBytes());
			os.close();
			
		} catch (Exception e) {			
			e.printStackTrace();
			return "fail";
		}
		
		// Naver Cloud AI
		String resp = NaverCloud.stt(filepath);
		
		return resp;
	}
	
	// CFR 얼굴인식
	@PostMapping(value = "cfr_fileUpload")
	public String cfr_fileUpload(@RequestParam("uploadFile")MultipartFile uploadFile, 
								HttpServletRequest req,
								String title) {
		System.out.println("NaverCloudController cfr_fileUpload " + new Date());
		
		String uploadpath = req.getServletContext().getRealPath("/upload");
		
		String filename = uploadFile.getOriginalFilename();
		String filepath = uploadpath + "/" + filename;
		
		System.out.println(filepath);
		
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			os.write(uploadFile.getBytes());
			os.close();
			
		} catch (Exception e) {			
			e.printStackTrace();
			return "fail";
		}
		
		String resp = NaverCloud.cfr(filepath, title);
		
		return resp;		
	}
	
	@GetMapping(value = "tts_proc")
	public String tts_proc(String str, HttpServletRequest req) {
		System.out.println("NaverCloudController ttc_proc " + new Date());
		System.out.println("str:" + str);
		
		String path = req.getServletContext().getRealPath("/upload");
		
		boolean b = NaverCloud.tts(str, path);
		if(b == false) {
			return "NG";
		}
		
		return "OK";
	}
	
	@PostMapping(value = "obj_detection")
	public String obj_detection(@RequestParam("uploadFile")MultipartFile uploadFile, 
								HttpServletRequest req) {
		System.out.println("NaverCloudController obj_detection " + new Date());
		
		String uploadpath = req.getServletContext().getRealPath("/upload");
		
		String filename = uploadFile.getOriginalFilename();
		String filepath = uploadpath + "/" + filename;
		
		System.out.println(filepath);
		
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			os.write(uploadFile.getBytes());
			os.close();
			
		} catch (Exception e) {			
			e.printStackTrace();
			return "fail";
		}
		
		String result = NaverCloud.ObjectDetection(filepath);		
		return result;
	}
	
	@PostMapping(value = "ocr_fileUpload")
	public String ocr_fileUpload(@RequestParam("uploadFile")MultipartFile uploadFile, 
								HttpServletRequest req) {		
		System.out.println("NaverCloudController obj_detection " + new Date());
		
		String uploadpath = req.getServletContext().getRealPath("/upload");
		
		String filename = uploadFile.getOriginalFilename();
		String filepath = uploadpath + "/" + filename;		
		System.out.println(filepath);
		
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			os.write(uploadFile.getBytes());
			os.close();
			
		} catch (Exception e) {			
			e.printStackTrace();
			return "fail";
		}
		
		String result = NaverCloud.ocr(filepath);
		return result;
	}	
	
	@PostMapping(value = "chatBot")
	public String chatBot(String msg) {
		System.out.println("NaverCloudController chatBot " + new Date());
		
		String json = NaverCloud.chatBot(msg);		
		return json;		
	}
	
}















