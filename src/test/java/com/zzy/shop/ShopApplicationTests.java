package com.zzy.shop;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zzy.shop.dao.UserDao;
import com.zzy.shop.model.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopApplicationTests {
	@Autowired
    private UserDao userDao;
	
	@Test
	public void contextLoads() {
	}

	@Before
    public void before() {
		User user = new User();
		user.setPhone("15010672177");
		user.setUsername("zzy");
		userDao.save(user);
    }
    @Test
    public void testAdd() {
        User user = userDao.findByPhone("15010672177");
        if(user!=null) {
        	System.out.println(" username: "+user.getUsername());
        }
    }
    
}
