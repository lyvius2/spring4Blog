package com.walter.config.logging;

import com.walter.model.AccessUserVO;
import com.walter.model.AccessVO;
import com.walter.model.MemberVO;
import com.walter.model.ResumeVO;
import com.walter.service.LogService;
import com.walter.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.support.BindingAwareModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 접속 로그 등록 (Aspect)
 * Created by Walter on 2017-07-16.
 */
@Slf4j
@Aspect
@Component
public class WaltersLoggingAspect {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private LogService logService;

	@Around("execution(* com.walter.controller.PostController.*View(..)) || execution(* com.walter.controller.ResumeController.*View(..))")
	public Object loggingAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object obj = joinPoint.getTarget();
		Object[] args = joinPoint.getArgs();
		String targetClass = obj.getClass().getSimpleName();

		AccessVO access = new AccessVO();
		access.setTarget_class(targetClass);
		access.setRequest_path(request.getServletPath());
		access.setIp(request.getRemoteAddr());
		access.setMethod(joinPoint.getSignature().getName());
		Date beginTime = new Date();
		access.setBeginTime(beginTime);

		Object returnObj = joinPoint.proceed();

		Date endTime = new Date();
		access.setEndTime(endTime);
		access.setProceed_time(endTime.getTime() - beginTime.getTime());

		AccessUserVO accessUser = null;
		if (targetClass.equals("ResumeController")) {
			MemberVO member = CommonUtil.getLoginUserInfo();
			accessUser = new AccessUserVO();
			accessUser.setUsername(member.getUsername());
			accessUser.setUserlink(member.getLink());

			BindingAwareModelMap modelMap = (BindingAwareModelMap)args[1];
			accessUser.set_id(((ResumeVO)modelMap.get("resume")).get_id());
		}
		logService.setAccessLog(access, accessUser);
		return returnObj;
	}
}
