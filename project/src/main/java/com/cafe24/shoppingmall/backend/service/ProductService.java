package com.cafe24.shoppingmall.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.backend.repository.ProductDao;
import com.cafe24.shoppingmall.backend.vo.OptionDetailVo;
import com.cafe24.shoppingmall.backend.vo.OptionVo;
import com.cafe24.shoppingmall.backend.vo.ProductOptionVo;
import com.cafe24.shoppingmall.backend.vo.ProductVo;

@Service
public class ProductService {
	
	
	@Autowired
	private ProductDao productDao;
	
	public ProductVo getProductInfo(Long no) {
		return new ProductVo(1L,"test_product1", "5000", "test1입니다", "<h1>Test1입니다</h1>", 'Y', 'Y', 'Y', 'Y', 20, 'Y', 5, 2000);
	}

	public List<ProductOptionVo> getProductOptionInfo(Long no) {
		List<ProductOptionVo> po_list = new ArrayList<ProductOptionVo>();
//		po_list.add(new OptionVo(1L, "화이트", "A001", 15, 'Y', 0L, 1L));
//		po_list.add(new ProductOptionVo(2L, "아이보리", "A002", 12, 'Y', 2000L, 1L));
//		po_list.add(new ProductOptionVo(3L, "민트", "A003", 12, 'Y', 2000L, 1L));
		return po_list;
	}
	
	
	public Long deleteProduct(Long deleteNo) {
		return 1L;
	}
	
	@Transactional
	public Long addProduct(ProductVo productVo) {
		System.out.println(productVo);
		Long no = productDao.insertProduct(productVo);
		
		if(productVo.getUse_option() == 'Y') {
			int option_order = 1;
			for (OptionVo option : productVo.getPo_list() ) {
				option.setProduct_no(no);
				option.setOp_order(option_order);
				
				Long option_no = productDao.insertOption(option);
				if(option_no == null) return null;
				option_order++;
				int detail_order = 1;
				for(OptionDetailVo od : option.getOd_list()) {
					od.setOption_no(option_no);
					od.setOpd_order(detail_order);
					boolean result = productDao.insertOptionDetail(od);
					detail_order++;
					if(!result) return null;
				}
			}
		}
		
		if(productVo.getCategory_list() != null) {
			for(Long categoryNo : productVo.getCategory_list()) {
				boolean result = productDao.insertProductCategort(no, categoryNo);
				if(!result) return null;
			}
		}
		
		
		return no;
	}

	public boolean updateProduct(ProductVo productVo) {
		
		return false;
	}
}
