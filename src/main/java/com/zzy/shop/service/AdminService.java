package com.zzy.shop.service;
import com.zzy.shop.bean.Admin;
import com.zzy.shop.core.Service;


public interface AdminService extends Service<Admin> {

	Admin findByUsername(String username);
}
