var dropdown2 = function(no) {
		var subject = $('div.bg-wrap[data-parent="'+no+'"]');
		var s =document.querySelector('div.bg-wrap[data-parent="'+no+'"]');
		var flag = s.dataset.flag;
		if(flag == "false") {
			s.dataset.flag=true;
			subject.css('display', 'block');
		} else {
			s.dataset.flag=false;
			subject.css('display', 'none');
		}
		
	}