$(function() {
  	
  	/**
  	*充值管理
  	*/
	window.rechargeApp = new Vue({
		el : '#rechargeApp',
		data : {
			rechargeList : [],
			pagingInfo : {
				totalPages:0,
				pageSize : 8,
				pageNo : 1
			},
			condition : {
				userName : "",
				status : null
			},
			paginationUi :{
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
		    	$bot.post("/hao/bot/api/query/recharge.do",param,function(result){
                	_self.rechargeList = result.data;
                	_self.pagingInfo = result.pagingInfo;
                	_self.paginationUi = $bot.setPaginationUi(_self.pagingInfo,_self.paginationUi.pagingList);
                	
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
		    	$bot.post("/hao/bot/api/audit/recharge.do",param,function(result){
                	_self.query();
                	
		    	});
		    }
		
		}
	})
  	
  	window.rechargeApp.queryPage(1);
})