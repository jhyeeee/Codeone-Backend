package com.codeone.controller.studygroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.codeone.service.studygroup.StudygroupScheduleService;

@Component
public class StudygroupScheduler {

	@Autowired
	StudygroupScheduleService studygroupScheduleService;
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void checkDeadlineForRecruitment() {
		studygroupScheduleService.closeStudygroupRecruitment();
	}
	
}
