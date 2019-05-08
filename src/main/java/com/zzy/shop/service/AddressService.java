package com.zzy.shop.service;
import com.zzy.shop.bean.Address;
import java.util.List;
import com.zzy.shop.core.Service;


public interface AddressService extends Service<Address> {
	List<Address> findAllByUserId(Long userId);
	void deleteAllByUserId(Long userId);
}
