package com.zzy.shop;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zzy.shop.bean.Order;
import com.zzy.shop.bean.User;
import com.zzy.shop.service.OrderService;
import com.zzy.shop.service.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopApplicationTests {
	@Autowired
    private UserService userService;
	@Autowired
    private OrderService orderService;
	@Before
    public void before() {
//		User user1 = new User();
//		user1.setPhone("15010672177");
//		user1.setUsername("zzy");
//		User user2 = new User();
//		user2.setPhone("13899990000");
//		user2.setUsername("ggg");
//		
//		userDao.save(user1);
//		userDao.save(user2);
    }
    @Test
    public void createOrder() {
    	Order order = new Order();
    	User user = userService.findById((long) 13);
    	if(user!=null) {
    		order.setId(null);
    		order.setUser(user);
        	order.setCode("O-XXX-199");
        	orderService.save(order);
    	}
    }
    
  //修改
  	@Test
  	public void testUpdate(){
  		User user = userService.findById((long) 13);
    	if(user!=null) {
        	System.out.println(" order code: "+user.getOrders().size());
    	}
  	}
  	
       //删除
  	//默认情况下，若删除1的一端，则会先把关联的多的一段的外键置空,然后删除一的一端
  	//可以通过@OneToMany的cascade 属性来修改默认的删除策略
//  	@Test
//  	public void testOneToManyRemove(){
//  		Optional<User> user = userDao.findById((long) 17);
//    	if(user.isPresent()) {
//      		System.out.println("zzy "+user.get().getOrders().size());
//      		userDao.delete(user.get());
//    	}
//  		
//  	}
  	
  //默认情况下，若删除1的一端，则会先把关联的多的一段的外键置空,然后删除一的一端
  	//可以通过@OneToMany的cascade 属性来修改默认的删除策略
  	@Test
  	public void testManyToOneRemove(){
  		Order order = orderService.findById((long) 5);
    	if(order!=null) {
      		System.out.println("zzy orderId:"+order.getId());
      		
      		User user = order.getUser();
      		System.out.println("zzy userId:"+user.getId());
      		user.getOrders().remove(order);
      		userService.saveAndFlush(user);
      		
      		System.out.println("zzy "+user.getOrders().size());
      		order.setUser(null);
      		orderService.save(order);
      		orderService.deleteById(order.getId());
    	}
  		
  	}
  	
  	//默认对关联多的一方使用懒加载的加载策略(延迟加载)
  	//可以使用@OneToMany的fetch 属性来修改默认的加载策略
  	//查询
  	@Test
  	public void testOneToManyFind(){
  		User user = userService.findById((long) 18);
    	if(user!=null) {
    		System.out.println("zzy "+user.getInfo());
      		System.out.println("zzy "+user.getOrders().size());
    	}
  	}
  	
  	//双向一对多的关联关系在执行保存时
  	//若先保存多的一端，在保存一的一端，默认情况下，会多出四条update语句
  	//若先保存一的一端则会多出2(n)条update语句
  	//在进行双向一对多的关联关系时，建议使用多的一方来维护关联关系，而1的一方不维护关联关系，这样会有效的减少sql语句
  	//注意：若在一的一端@oneToMany 中使用mapperBy属性，则@oneToMany端就不能在使用@JoinColumn(name="CUSTOMER_ID")属性
  	
  	//单向一对多保存时，一定会多出update语句
  	//因为多的一端在插入时不会同时插入外键列
  	//保存
  	@Test
  	public void testOneToManyPersist(){
  		User user = new User();
  		user.setPhone("13899992222");
  		user.setUsername("jjj");
  		
  	//执行保存操作
  		userService.saveAndFlush(user);
  		
  		Order order1=new Order();
  		order1.setCode("o-CC-1");
  		order1.setUser(user);
  		
  		Order order2=new Order();
  		order2.setCode("o-CC-2");
  		order2.setUser(user);
  		
  		orderService.save(order1);
  		orderService.save(order2);
  	}
    
}
