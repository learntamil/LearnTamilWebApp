(() => {
	/* body... */
	angular.module('app.utils',[]).
	factory('Utils', Utils);

	Utils.$inject=["$log"];
	function Utils($log)
	{
		$log.debug("Utils");
		function isUndefinedOrNull(Obj)
		{
			$log.debug("Utils.isUndefinedOrNull");
			return angular.isUndefined(Obj) || Obj == null;
		}
		return {
			isUndefinedOrNull: isUndefinedOrNull
		}
	}
})()