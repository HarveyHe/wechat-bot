package com.hao.bot.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import com.gsst.eaf.core.utils.BeanUtils;
/**
 * 
 * @author Harvey.He
 *
 */
public class PasswordUtils {

	private PasswordEncoder passwordEncoder;
	
	private Pattern passwordPattern;
	
	private String invalidMessage;
	
	public String encode(CharSequence rawPassword) {
		return this.passwordEncoder.encode(rawPassword);
	}
	
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return this.passwordEncoder.matches(rawPassword, encodedPassword);
	}
	
	public void verify(String rawPassword) {
		Assert.hasText(rawPassword, "password can't be null");
		if(this.passwordPattern != null) {
			Matcher matcher = this.passwordPattern.matcher(rawPassword);
			if(!matcher.matches()) {
				throw new RuntimeException(BeanUtils.ifnull(invalidMessage,"Invalid password!"));
			}
		}
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public void setPasswordPattern(Pattern passwordPattern) {
		this.passwordPattern = passwordPattern;
	}
	
	public void setPasswordPattern(String passwordPattern) {
		if(passwordPattern != null) {
			this.passwordPattern = Pattern.compile(passwordPattern);
		}
	}

	public void setInvalidMessage(String invalidMessage) {
		this.invalidMessage = invalidMessage;
	}
}
