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

	temple.template = {};
	temple.template.noteRowTemplate = _.template('<tr id="note-<%= noteId %>"><td><span class="note-content"><%- note %></span>' +
		'<span class="label label-warning btnEditNote" style="cursor: pointer">Edit</span>' +
		'<span class="label label-danger btnDeleteNote" style="cursor: pointer">Delete</span></td></tr>');

})(window, jQuery);