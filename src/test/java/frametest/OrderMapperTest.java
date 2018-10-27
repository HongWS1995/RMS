package frametest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hong.bean.Order;
import com.hong.dao.OrderMapper;
import junit.framework.TestCase;
//用户bean测试通过
public class OrderMapperTest extends TestCase {
	//Junit单元测试会创建与测试用例相同数量的对象，使用类成员变量能够减少Spring和C3P0的多次初始化
	static ApplicationContext context= new ClassPathXmlApplicationContext("spring-context.xml");
	static SqlSessionFactory sf = (SqlSessionFactory)context.getBean("sqlSessionFactory");
	SqlSession session = sf.openSession(true);
	OrderMapper mapper = session.getMapper(OrderMapper.class);
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetOrderById() {
		Order order = mapper.selectByPrimaryKey(4);
		System.out.println("查询："+order.getId());
		System.out.println(order.getTableId());
		assertEquals(true, order.getId()>0);
	}
	
	public void testGetOrderList() {
		Order order2 = new Order();
		List<Order> orders = mapper.getOrderList(1,10, order2);
		for (Iterator iterator = orders.iterator(); iterator.hasNext();) {
			Order order = (Order) iterator.next();
			System.out.println(order.getTableR().getTableName());
			
		}
		System.out.println( orders.get(0).getTableR().getTableName());
		assertEquals(true, orders.get(0).getTableR().getTableName()!=null);
	}
	
	public void testGetOrderDetails() throws JsonProcessingException {
		Order orders = mapper.getOrderDetailById(1000014);
		ObjectMapper mapper2 = new ObjectMapper();
		String s = mapper2.writeValueAsString(orders);
		System.out.println( s);
		assertEquals(true, s!=null);
	}

	public void testUpdateOrder() {
		Order order = mapper.selectByPrimaryKey(1000000);
		System.out.println("更新："+order.getId());
		order.setTableId(2);
		order.setStatus("未收费");
		order.setTotal(10);
		order.setRemark("非常棒");
		order.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		mapper.updateByPrimaryKeySelective(order);
		assertEquals(true, order.getId()>0);
	}

	public void testInsertOrder() {
		Order order = new Order();
		order.setTableId(2);
		order.setStatus("未收费");
		order.setTotal(10);
		order.setRemark("非常棒");
		order.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		mapper.insert(order);
		assertEquals(true, order.getId()>0);
	}

	public void testDeleteOrder() {
		int count  = mapper.deleteByPrimaryKey(2);
		assertEquals(true, count>0);
	}

}
