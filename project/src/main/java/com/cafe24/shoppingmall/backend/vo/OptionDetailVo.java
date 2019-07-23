package com.cafe24.shoppingmall.backend.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class OptionDetailVo {
	
	private Long no;
	@NotEmpty
	@Length(max=20)
	private String opd_name;
	@NotEmpty
	private Long plus_price;
	private int opd_order;
	private Long option_no;
	private String flag;
	
	
	public OptionDetailVo() {
	}
	public OptionDetailVo(String opd_name, Long plus_price) {
		this.opd_name = opd_name;
		this.plus_price = plus_price;
	}
	public OptionDetailVo(Long no, String opd_name, Long plus_price, int opd_order, Long option_no) {
		this.no = no;
		this.opd_name = opd_name;
		this.plus_price = plus_price;
		this.opd_order = opd_order;
		this.option_no = option_no;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Long getPlus_price() {
		return plus_price;
	}


	public void setPlus_price(Long plus_price) {
		this.plus_price = plus_price;
	}

	public String getOpd_name() {
		return opd_name;
	}

	public void setOpd_name(String opd_name) {
		this.opd_name = opd_name;
	}

	public int getOpd_order() {
		return opd_order;
	}

	public void setOpd_order(int opd_order) {
		this.opd_order = opd_order;
	}

	public Long getOption_no() {
		return option_no;
	}

	public void setOption_no(Long option_no) {
		this.option_no = option_no;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "OptionDetailVo [no=" + no + ", opd_name=" + opd_name + ", plus_price=" + plus_price + ", opd_order="
				+ opd_order + ", option_no=" + option_no + ", flag=" + flag + "]";
	}

	
	
	
	
}
