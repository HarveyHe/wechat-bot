$(function() {

	/**
	 * 期数管理
	 */
	window.recordsApp = new Vue({
		el : '#recordsApp',
		data : {
			recordsList : [],
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
				$bot.post("/hao/bot/api/query/playing/records.do", param, function(
						result) {
					_self.recordsList = result.data;
					_self.pagingInfo = result.pagingInfo;
					console.log(_self.pagingInfo)
					_self.paginationUi = $bot.setPaginationUi(_self.pagingInfo,
							_self.paginationUi.pagingList);
					console.log(_self.paginationUi)

				});
			}

		}
	})

	window.recordsApp.queryPage(1);
})