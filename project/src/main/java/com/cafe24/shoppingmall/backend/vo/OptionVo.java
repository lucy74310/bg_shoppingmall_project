package com.cafe24.shoppingmall.backend.vo;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class OptionVo {
	private Long no;
	
	@NotEmpty
	@Length(min=1, max=20)
	private String op_name;
	private String op_explain;
	private int op_order;
	private Long product_no;
	private List<OptionDetailVo> od_list;
	
	
	
	
	public OptionVo() {
	}

	
	public OptionVo(String op_name, List<OptionDetailVo> od_list) {
		this.op_name = op_name;
		this.od_list = od_list;
	}



	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	
	public String getOp_name() {
		return op_name;
	}


	public void setOp_name(String op_name) {
		this.op_name = op_name;
	}


	public String getOp_explain() {
		return op_explain;
	}


	public void setOp_explain(String op_explain) {
		this.op_explain = op_explain;
	}


	public int getOp_order() {
		return op_order;
	}


	public void setOp_order(int op_order) {
		this.op_order = op_order;
	}


	public Long getProduct_no() {
		return product_no;
	}

	public void setProduct_no(Long product_no) {
		this.product_no = product_no;
	}

	public List<OptionDetailVo> getOd_list() {
		return od_list;
	}

	public void setOd_list(List<OptionDetailVo> od_list) {
		this.od_list = od_list;
	}


	@Override
	public String toString() {
		return "OptionVo [no=" + no + ", op_name=" + op_name + ", op_explain=" + op_explain + ", op_order=" + op_order
				+ ", product_no=" + product_no + ", od_list=" + od_list + "]";
	}

	
	
	
	
	
}
