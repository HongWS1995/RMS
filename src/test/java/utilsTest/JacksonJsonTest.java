package utilsTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hong.bean.User;
//直接反编译出源码
//ObjectMapper核心类
public class JacksonJsonTest {

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		// POJO
		User user = new User();
		user.setId(21);
		user.setUsername("用户" + user.getId());
		user.setPassword("123456");
		user.setLoginTimes(1);
		user.setIsstaff("N");
		user.setCanlogin(1);
		user.setLastestLoginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		user.setCanlogin(1);
		user.setPtypeId(3);
		User user2 = new User();
		user.setId(21);
		user.setUsername("用户" + user.getId());
		user.setPassword("123456");
		// 集合
		List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user2);
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("id", 1);
		map.put("username", "李白");
		map.put("list", list);

		try {
			// 将POJO对象写入输出流、文件或者字符串[生成json]
			String json = mapper.writeValueAsString(user);
			// 从输入流、文件或者字符串将json值解析成POJO对象[解析Json]
			User user3 = mapper.readValue(json, User.class);
			System.out.println(user3.getLastestLoginTime());
			// 将List对象写入输出流、文件或者字符串
			String ListJson = mapper.writeValueAsString(list);
			System.out.println(ListJson);
			List list2 = mapper.readValue(ListJson, ArrayList.class);
			System.out.println(Arrays.toString(list2.toArray()));
			// 将map对象写入输出流、文件或者字符串
			String mapJson = mapper.writeValueAsString(map);
			System.out.println(mapJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
