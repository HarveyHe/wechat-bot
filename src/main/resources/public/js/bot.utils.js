/**
 * @author Harvey.He
 */
//~屏蔽压缩报错
~(function (window) {
    "use strict";
    
    var $bot = {};
    
    
    $bot.post = function(url,param,fn){
    	$.ajax({
            type : 'POST',
            url : url,
            dataType : 'json',
　　　　  		contentType : 'application/json',
            data : JSON.stringify(param),
            beforeSend : function () {
                // ....
            },
            success : function (result) { // 返回的RequestResult的json对象
            	if(fn && Object.prototype.toString.call(fn)=== '[object Function]'){
            		fn(result)
            	}
            },
        });
    };
    /**
     * 
     * pagingList 原分页数组 每组最多显示5个页面按钮
     */
    $bot.setPaginationUI =function(pagingInfo,pagingList){
    	var paginationUI = {};
    	
    	var pageNo = pagingInfo.pageNo;
    	var totalPages = pagingInfo.totalPages;
    	
    	paginationUI.pagingList = [];
    	if( $.inArray(pageNo,pagingList) == -1){
    		
    		var digitalDiff = totalPages - pageNo;
    		var startIndex = pageNo;
    		var endIndex = pageNo + 4;
    		if(totalPages <= 5){
    			startIndex = 1;
    			endIndex = totalPages;
    		}else if(digitalDiff < 5){
    			startIndex = totalPages - 4;
    			endIndex = totalPages;
    		}else{
    			if(pageNo<=3){
    				startIndex = 1;
    				endIndex = 5;
    			}else if( pageNo-2 > 1 && pageNo+2 < totalPages ){
    				startIndex = pageNo-2;
    				endIndex = pageNo + 2;
    			}
    		}
    		for (var i = startIndex ;i<= endIndex ;i++ ){
    			paginationUI.pagingList.push(i);
    		}
    	}else{
    		paginationUI.pagingList = pagingList;
    	}
    	
    	paginationUI.prev = pageNo - 1 == 0?1:pageNo - 1;
    	paginationUI.next = pageNo + 1 == totalPages + 1?totalPages:pageNo + 1;
    	return paginationUI;
    }
    
    //为全局对象绑定$bot
    window.$bot = $bot;
})(window);



