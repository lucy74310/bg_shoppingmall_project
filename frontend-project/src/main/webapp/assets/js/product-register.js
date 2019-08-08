$(document).ready(function() {
	/*사이드바 메뉴 토글*/
	$("#menu-toggle").click(function(e) {
		e.preventDefault();
		$("#wrapper").toggleClass("toggled");
	});
	
	
	/*재고 사용 여부 변경*/
	$('input[name="use_stock"]').change(function(e) {
		var ues_stock = e.target.value;
		if (ues_stock == 'Y') {
			$('#stock').css('display', 'block');
		} else {
			$('#stock').css('display', 'none');
		}
	
	})
	
	
	
	/*옵션 사용 여부 변경 */
	var option_clone_html = $('#option-list').clone().html();
	var option_clone = $('#option-list .option').clone();
	console.log(option_clone);
	
	
	var option_add_button = $('#option-add-button');
	
	
	$('input[name="use_option"]').change(function(e) {
		var ues_stock = e.target.value;
		if (ues_stock == 'Y') {
			option_add_button.css('display', 'block');
			$('#option-list').css('display', 'block');
			$('#option-list').html(option_clone);
		} else {
			option_add_button.css('display', 'none');
			
			$('#option-list').html('');
		}
	});
	
	/*옵션추가*/
	option_add_button.click(function(e) {
		$('#option-list').append(option_clone_html);
	})
	
	
	/*이미지 추가*/
	$('#add-image').click(function(e) {
		$('.image-list').append('<input type="file" name="image" class="btn btn-light" />');
	})
	
});
