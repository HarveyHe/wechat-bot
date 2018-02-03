$(function() {

	/**
	 * 下注管理
	 */
	window.orderApp = new Vue({
		el : '#orderApp',
		data : {
			orderList : [],
			pagingInfo : {
				pageSize : 8,
				pageNo : 1
			},
			condition : {
				userName : "",
				status : null
			},
			paginationUi : {
				prev : 1,
				next : 1,
				pagingList : []
			}

		},
		methods : {
			queryPage : function(pageNo) {
				var _self = this;
				_self.pagingInfo.pageNo = pageNo;
				_self.query();
			},
			query : function() {
				var _self = this;
				var param = {
					request : _self.condition
				}
				param.request.pagingInfo = _self.pagingInfo;
				$bot.post("/hao/bot/api/query/order.do", param, function(
						result) {
					_self.orderList = result.data;
					_self.pagingInfo = result.pagingInfo;
					_self.paginationUi = $bot.setPaginationUi(_self.pagingInfo,
							_self.paginationUi.pagingList);

				});
			}

		}
	})

	window.orderApp.queryPage(1);
})