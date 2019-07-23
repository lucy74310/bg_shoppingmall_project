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
	private String is_use;
	private Long option_no;
	private String flag;
	
	
	public OptionDetailVo() {
	}
	public OptionDetailVo(String opd_name, Long plus_price) {
		this.opd_name = opd_name;
		this.plus_price = plus_price;
	}
	
	public OptionDetailVo(String opd_name, Long plus_price, int opd_order, String is_use) {
		this.opd_name = opd_name;
		this.plus_price = plus_price;
		this.opd_order = opd_order;
		this.is_use = is_use;
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
	public String getIs_use() {
		return is_use;
	}
	public void setIs_use(String is_use) {
		this.is_use = is_use;
	}
	@Override
	public String toString() {
		return "OptionDetailVo [no=" + no + ", opd_name=" + opd_name + ", plus_price=" + plus_price + ", opd_order="
				+ opd_order + ", is_use=" + is_use + ", option_no=" + option_no + ", flag=" + flag + "]";
	}
}
