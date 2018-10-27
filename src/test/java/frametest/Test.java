package frametest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hong.bean.User;
import com.hong.dao.UserMapper;
//手动测试太慢，使用Junit单元测试
public class Test {

	public static void main(String[] args) {
		ApplicationContext context= new ClassPathXmlApplicationContext("spring-context.xml");
		User user1 = (User) context.getBean("User");System.out.println(user1.toString());
		SqlSessionFactory sf = (SqlSessionFactory)context.getBean("sqlSessionFactory");
		System.out.println(sf.toString());
		SqlSession session =  sf.openSession();
		UserMapper mapper  = session.getMapper(UserMapper.class);
		User user = mapper.selectByPrimaryKey(3);
		System.out.println(user.getUsername()+":"+user.getPassword());
		user.setIsstaff("N");
		mapper.updateByPrimaryKey(user);
//		user.setId(4);
//		user.setPassword("456");
//		mapper.insertUser(user);
		session.commit(true);
		mapper.deleteByPrimaryKey(user.getId());
		session.commit();
	}

}
