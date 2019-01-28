package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.model.ItemDao;
import com.model.Order;
import com.model.OrderDao;

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
	
	
	// Ʈ����� ��¹� -> ������ Ʈ����� ������ �ƴ� �⺻�����϶� propagation = Propagation.REQUIRED ��� , �׸��� rollback ���� 
	@Transactional(propagation = Propagation.REQUIRED,
			rollbackFor = {Exception.class})
	
	public void orderAction(Order order)throws Exception{
		orderDao.addOrder(order); // �ֹ���� 
		
		// �ֹ���ȣ�� ��ǰ��ȣ ���� �Ͽ���, �Ʒ� �ڵ� �ֹ��� ���� < �ֹ��������� ũ�� 
		if(itemDao.findItem(order.getNo()).getAmount() < order.getAmount()){
			throw new Exception("������");
		}
		
		// ������ ,, ������� ��� �̷������ �ֹ��� ���� 
		itemDao.updateItem(order); 
	}
	
	

}
