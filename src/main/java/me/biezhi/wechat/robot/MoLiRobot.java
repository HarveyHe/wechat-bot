package me.biezhi.wechat.robot;

import com.blade.kit.StringKit;
import com.blade.kit.http.HttpRequest;

import me.biezhi.wechat.Constant;

/**
 * 
 * @author Harvey.He
 *
 */
public class MoLiRobot implements Robot {

	private String apiUrl;

	public MoLiRobot() {
		String apiKey = Constant.config.get("itpk.api_key");
		String apiSecret = Constant.config.get("itpk.api_secret");
		if(StringKit.isNotBlank(apiKey) && StringKit.isNotBlank(apiSecret)){
			this.apiUrl = Constant.ITPK_API + "?api_key=" + apiKey + "&api_secret=" + apiSecret;
		}
	}

	@Override
	public String talk(String msg) {
		if(null == this.apiUrl){
			return "机器人未配置";
		}
		String url = apiUrl + "&question=" + msg;
		String result = HttpRequest.get(url).connectTimeout(3000).body();
		return result;
	}

}
