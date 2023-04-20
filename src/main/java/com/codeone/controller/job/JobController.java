package com.codeone.controller.job;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.service.job.JobService;

@RequestMapping("job/")
@RestController
public class JobController {
	@Autowired
	private JobService service;
	@GetMapping("list")
	public Map<String, Object> list(@RequestParam Map<String, Object> params) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		
		res.put("list", service.joblist(params)); 
		return res;		
	}	
	
	
}
