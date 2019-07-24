SELECT NO, po_name, po_code, stock, use_stock, selling, 
				displayed, plus_price, po_order, product_no
FROM product_option
WHERE product_no = 3
ORDER BY po_order ASC;


SELECT NO, po_name, po_code, stock, use_stock, selling, 
				displayed, plus_price, po_order, product_no
FROM product_option
WHERE product_no =3
ORDER BY po_order ASC;


SELECT c.*
FROM product_category AS pc
LEFT JOIN category AS c ON pc.category_no = c.`no` 
WHERE  c.`no` IN (SELECT c2.no FROM category AS c2 WHERE c2.no = c.upper_no);


SELECT c.* , c2.category_name AS upper_category_name , c2.upper_no AS upper_no2
from category AS c LEFT JOIN category AS c2 ON c.upper_no = c2.no
WHERE c.no = 4;






SELECT * 
FROM product AS p JOIN product_option AS po ON p.`no`=po.product_no 
LEFT JOIN op ON p.`no` =op.product_no JOIN opd ON op.`no` = opd.option_no;


SELECT 
	    	p.no, p.product_name, p.product_price, p.stock, p.use_stock,
	    	op.no, op.op_name, op.op_order 
	    	FROM product AS p JOIN op  
	    	ON p.no = op.product_no WHERE p.no = 3;
	    	
 SELECT 
	    	p.no, p.product_name, p.product_price, p.stock, p.use_stock,
	    	op.no, op.op_name, op.op_order , opd.no AS opd_no, opd.opd_name, opd.opd_order, opd.plus_price
	    	FROM product AS p LEFT JOIN op ON p.no = op.product_no 
	    	LEFT JOIN opd ON op.`no` =opd.option_no
			 WHERE p.no = 3 AND opd.is_use ='Y' ;
			 

INSERT INTO non_member VALUES (NULL, 'test1234', NOW()+1 );		 
SELECT * FROM non_member;			 
			 
			 
			 
INSERT INTO cart (member_no, non_member_no, product_option_no, count, price)
			values(#{member_no} , #{non_member_no}, #{product_option_no}, #{count}, #{price})