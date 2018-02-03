$(function() {
  	
  	/**
  	*充值管理
  	*/
	window.extractApp = new Vue({
		el : '#extractApp',
		data : {
			extractList : [],
			pagingInfo : {
				pageSize : 8,
				pageNo : 1
			},
			condition : {
				userName : "",
				status : null
			},
			paginationUI :{
				prev:1,
				next:1,
				pagingList:[]
			}
			
		},
		methods: {
		    queryPage: function (pageNo) {
		    	var _self = this;
		    	_self.pagingInfo.pageNo = pageNo;
		    	_self.query();
		    },
		    query: function () {
		    	var _self = this;
		    	var param = {
		    		request : _self.condition
		    	}
		    	param.request.pagingInfo = _self.pagingInfo;
		    	$bot.post("/hao/bot/api/query/extract.do",param,function(result){
                	_self.extractList = result.data;
                	_self.pagingInfo = result.pagingInfo;
                	_self.paginationUI = $bot.setPaginationUI(_self.pagingInfo,_self.paginationUI.pagingList);
                	
		    	});
		    },
		    audit : function(id,status){
		    	var _self = this;
		    	var param = {
		    		request : {
		    			id : id,
		    			status:status
		    		}
		    	}
		    	$bot.post("/hao/bot/api/audit/extract.do",param,function(result){
                	_self.query();
                	
		    	});
		    }
		
		}
	})
  	
  	window.extractApp.queryPage(1);
})