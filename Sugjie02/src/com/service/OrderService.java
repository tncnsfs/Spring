package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.model.ItemDao;
import com.model.Order;
import com.model.OrderDao;

@Service
public class OrderService {
	private OrderDao orderDao;
	private ItemDao itemDao;
	
	@Autowired
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Autowired
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor ={Exception.class})
	
	public void orderAction(Order order)throws Exception{ // �� ���񽺰� Ʈ����� ����..Ŀ��/�ѹ� 
		orderDao.addOrder(order); //�ֹ� ���

		if(itemDao.findItem(order.getNo()).getAmount() < order.getAmount()){ //�ֹ���ȣ
			throw new Exception("������");
	}
	itemDao.updateItem(order);
}
}





