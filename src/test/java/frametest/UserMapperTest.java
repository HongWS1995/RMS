package frametest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.hong.bean.User;
import com.hong.dao.UserMapper;
import junit.framework.TestCase;
//用户bean测试通过
public class UserMapperTest extends TestCase {
	//Junit单元测试会创建与测试用例相同数量的对象，使用类成员变量能够减少Spring和C3P0的多次初始化
	static ApplicationContext context= new ClassPathXmlApplicationContext("spring-context.xml");
	static SqlSessionFactory sf = (SqlSessionFactory)context.getBean("sqlSessionFactory");
	SqlSession session = sf.openSession(true);
	UserMapper mapper = session.getMapper(UserMapper.class);
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetUserById() {
		User user = mapper.selectByPrimaryKey(22);
		System.out.println("查询："+user.getId());
		assertEquals(true, user.getId()>0);
	}

	public void testUpdateUser() {
		User user = mapper.selectByPrimaryKey(2);
		System.out.println("更新："+user.getId());
		user.setLoginTimes(4);
		user.setIsstaff("N");
		user.setCanlogin(1);
		user.setLastestLoginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		user.setCanlogin(1);
		user.setPtypeId(3);
		mapper.updateByPrimaryKeySelective(user);
		assertEquals(true, user.getId()>0);
	}

	public void testInsertUser() {
		User user = new User();
		//user.setId(23);
		user.setUsername("用户"+user.getId());
		user.setPassword("123456");
		user.setLoginTimes(1);
		user.setIsstaff("N");
		user.setCanlogin(1);
		user.setLastestLoginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		user.setCanlogin(1);
		user.setPtypeId(3);
		mapper.insert(user);
		assertEquals(true, user.getId()>0);
	}

	public void testDeleteUser() {
		User user = mapper.selectByPrimaryKey(5);
		mapper.deleteByPrimaryKey(5);
	}

}
