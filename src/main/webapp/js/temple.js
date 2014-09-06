;
(function(window, $){
	var temple = window.temple;
	if (!temple) {
		temple = window.temple = {};
	}

	var validDateReg = /\d{1,2}\/\d{1,2}\/\d{4}/;
	temple.validateDate = function($input, msg) {
		var date = $input.val();
		if (date) {
			var validate = validDateReg.test($input.val());
			if (!validate && arguments.length > 1) {
				window.alert('The date format of "' + msg + '" is not valid!');
				$input.val('');
			}
			return validate;
		}
		return true;
	}

	// datepicker
	$(function() {
		$('.datepicker').datepicker().css('width', '100px');
	});
})(window, jQuery);