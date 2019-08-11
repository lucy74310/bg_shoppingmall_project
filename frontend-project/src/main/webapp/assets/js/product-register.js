var add_od = function(e){
	var parent = e.target.dataset.parent;
	var ln = $('*[data-option='+ parent +']');
	var list_order = ln.data('listorder');
	var index = ln[0].children[1].children.length ; 
	var opd = "" +
			"<li class='list-group-item'>" +
			"<input type='text' name='o_list["+list_order+"].od_list["+index+"].opd_name' style='width: 6rem;' placeholder='옵션 항목명' required >" +
			" <input type='number' name='o_list["+list_order+"].od_list["+index+"].plus_price' style='width: 5.5rem;' "+
			"		placeholder='추가금액' required>원  " +
					"</li>";
	$('*[data-option='+ parent +'] > .opd').append(opd);
}
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
//	var option_clone_html = $('#option-list').clone().html();
//	var option_clone = $('#option-list .option').clone();
	
	var option_html = "<div class='card text-center etc-item option' data-option=1 " +
									"	data-listorder=0>	" +
									"	<div class='card-header'> 	" +
									"		<input type='text' name='o_list[0].op_name' 	" +
									"			style='width: 7rem;' placeholder='옵션명' required /> <img 	" +
									"			src='/shop/assets/image/icon/cancel-button.png'	" +
									"			class='cancel-btn' onclick='javascript:deleteOption(event)'> 	" +
									"	</div> 	" +
									"	<ul class='list-group list-group-flush opd'> " +
									"		<li class='list-group-item'><input type='text' " +
									"			name='o_list[0].od_list[0].opd_name' style='width: 6rem;' " +
									"			placeholder='옵션 항목명' required> <input type='number' " + 
									"			name='o_list[0].od_list[0].plus_price' " +
									"			style='width: 5.5rem;' placeholder='추가금액' required>원 " +
									"		</li> " +
									"	</ul> " +
									"	<ul class='list-group list-group-flush'> " +
									"		<li class='btn btn-primary option-detail-add-button' " +
									"			style='border: none;' data-parent=1>+항목추가</li> " +
									"	</ul> " +
								"	</div>";
	var option_add_button = $('#option-add-button');
	
	
	$('input[name="use_option"]').change(function(e) {
		var ues_stock = e.target.value;
		if (ues_stock == 'Y') {
			option_add_button.css('display', 'block');
			$('#option-list').css('display', 'block');
			//$('#option-list').html(option_clone);
			$('#option-list').html(option_html);
			$('.option-detail-add-button').click(add_od);
		} else {
			option_add_button.css('display', 'none');
			
			$('#option-list').html('');
		}
	});
	
	/*옵션추가*/
	var option_no = 1
	
	option_add_button.click(function(e) {
		option_no++;
		var list_order = ($('.option').length);
		
		var option_html = "<div class='card text-center etc-item option'  " +
									"	data-option=" + option_no + " data-listorder="+list_order+">	" +   
									"	<div class='card-header'> " +
									"		<input type='text' name='o_list["+list_order+"].op_name' style='width: 5rem;'	" +
									"			placeholder='옵션명' required /> <img	" +	
									"			src='/shop/assets/image/icon/cancel-button.png'	" +
									"			class='cancel-btn' onclick='javascript:deleteOption(event)'>	" +
									"	</div>	" +
									"	<ul class='list-group list-group-flush opd'>	" +
									"		<li class='list-group-item' ><input	" +
									"			type='text' name='o_list["+list_order+"].od_list[0].opd_name' style='width: 6rem;'	" +
									"			placeholder='옵션 항목명' required><input type='number' name='o_list["+list_order+"].od_list[0].plus_price' style='width: 5.5rem;' "+
									"			placeholder='추가금액' required>원</li>	" +
									"	</ul> " + 
									"	<ul class='list-group list-group-flush'> " +
									"		<li class='btn btn-primary option-detail-add-button' " +
									"			style='border: none;' data-parent="+ option_no + ">+항목추가</li> " + 
									"	</ul> " +
									"	</div>"; 
		console.log(option_html);
		$('#option-list').append(option_html);
		$('*[data-parent='+option_no+ ']').click(add_od);
	})
	
	
	/*이미지 추가*/
	$('#add-image').click(function(e) {
		$('.image-list').append('<input type="file" name="image_add_list" class="btn btn-light" />');
	});
	
	
	
	
});

