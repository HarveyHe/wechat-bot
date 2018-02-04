package com.hao.bot.demo;

import io.github.biezhi.elves.Elves;
import io.github.biezhi.elves.config.Config;
import io.github.biezhi.elves.pipeline.Pipeline;
import io.github.biezhi.elves.request.Request;
import io.github.biezhi.elves.response.Response;
import io.github.biezhi.elves.response.Result;
import io.github.biezhi.elves.spider.Spider;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 北京赛车网示例
 *
 * @author biezhi
 * @date 2018/1/11
 */
public class BotExample {

    @Slf4j
    static class BotSpider extends Spider {

        public BotSpider(String name) {
            super(name);
            this.startUrls("https://www.cp222567.com/views/bjpk10/");
        }

        @Override
        public void onStart(Config config) {
            this.addPipeline((Pipeline<List<String>>) (item, request) -> log.info("保存到文件: {}", item));
        }

        public Result parse(Response response) {
            Result<List<String>> result   = new Result<>();
            Elements  elements = response.body().css("#dataList");
//            Elements  elements = response.body().css("#gameissue #gameid.gameid");

            
            if (elements != null && elements.size() ==  1){
            	 Request nextReq     = this.makeRequest("https://www.cp222567.com/views/bjpk10/", this::parse);
                 result.addRequest(nextReq);
//            	System.out.println(elements2);
            }
            List<String> titles = elements.stream().map(Element::text).collect(Collectors.toList());
            result.setItem(titles);
//#historyList
            // 获取下一页 URL
//            Elements nextEl = response.body().css("#content > div > div.article > div.paginator > span.next > a");
//            if (null != nextEl && nextEl.size() > 0) {
//                String  nextPageUrl = nextEl.get(0).attr("href");
//                Request nextReq     = this.makeRequest(nextPageUrl, this::parse);
//                result.addRequest(nextReq);
//            }
            
            
/*            Elements  elements = response.body().css("#content table .pl2 a");
            
            List<String> titles = elements.stream().map(Element::text).collect(Collectors.toList());
            result.setItem(titles);
            
            // 获取下一页 URL
            Elements nextEl = response.body().css("#content > div > div.article > div.paginator > span.next > a");
            if (null != nextEl && nextEl.size() > 0) {
            	String  nextPageUrl = nextEl.get(0).attr("href");
            	Request nextReq     = this.makeRequest(nextPageUrl, this::parse);
            	result.addRequest(nextReq);
            }
*/            return result;
        }

    }

    public static void main(String[] args) {
        BotSpider botSpider = new BotSpider("北京赛车网");
        Elves.me(botSpider, Config.me()).start();
    }

}

