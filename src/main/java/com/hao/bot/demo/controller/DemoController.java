package com.hao.bot.demo.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hao.bot.model.BotUserModel;
import com.hao.bot.service.BotUserService;
import com.hao.bot.utils.PasswordUtils;

@RestController
@RequestMapping("/demo")
public class DemoController {
	private Logger logger = LoggerFactory.getLogger(DemoController.class);
	@Autowired
    private PasswordUtils passwordUtils;
	@Autowired
	private BotUserService botUserService;
	@RequestMapping(value = "/test/{mappedCode}.do",method = { RequestMethod.POST ,RequestMethod.GET})
	public String test(@PathVariable("mappedCode") String mappedCode,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("参数{}", mappedCode);
		BotUserModel model = new BotUserModel();
		model.setUserAccount("admin");
		String enPassword = this.passwordUtils.encode("admin");
		model.setUserName("admin");
		model.setPassword(enPassword);
		model.setUserStatus(new Byte("1"));
		model.setUserType(new Byte("1"));
		botUserService.save(model);
		return mappedCode;
		
	}
}
