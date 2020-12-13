//ẩn hiện mật khẩu

$(document).ready(function() {
	$('.pass_show').append('<span class="ptxt">Hiển thị</span>');
});

$(document).on('click', '.pass_show .ptxt', function() {

	$(this).text($(this).text() == "Hiển thị" ? "Ẩn" : "Hiển thị");

	$(this).prev().attr('type', function(index, attr) {
		return attr == 'password' ? 'text' : 'password';
	});

});
