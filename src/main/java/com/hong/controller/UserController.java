package com.hong.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hong.bean.User;
import com.hong.bean.Message;
import com.hong.bean.PageBean;
import com.hong.bean.Product;
import com.hong.service.UserService;
import com.hong.utils.DateFormats;

@Controller
@RequestMapping("/user")
public class UserController {
	public static Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping("/findUserById")
	public User findUserById(HttpServletRequest request, @RequestParam String id) {
		int uid = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(uid);
		return user;
	}

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/UserList")
	public Map getUserList(int currentPage, int pageSize, User userform, HttpSession session,
			HttpServletRequest request) throws UnsupportedEncodingException {
		User user = (User) session.getAttribute("user");
		String url = URLEncoder.encode(request.getRequestURI(), "UTF-8");

		int totalRecord = this.userService.selectCount(userform);
		PageBean<User> pageBean = new PageBean<User>(currentPage, pageSize, totalRecord);
		List<User> users = this.userService.getUserList(currentPage, pageSize, userform);
		pageBean.setBeanList(users);
		Map<String, Object> map = new HashMap<String, Object>();
		if (user != null) {
			map.put("result", "success");
			map.put("url", url);
			map.put("pageBean", pageBean);
			return map;
		} else {
			map.put("result", "fail");
			return null;
		}

	}

	@ResponseBody
	@RequestMapping("/userlist_1")
	public List<User> getUserList1(int page, int rows, User userform, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null)
			return this.userService.getUserList(page, rows, userform);
		else
			return null;
	}

	@RequestMapping("/index")
	public void toIndex(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null && !user.getUsername().isEmpty()) {
			user.setIslogin("Y");
			user.setLastestLoginTime(DateFormats.format(new Date()));
			user.setLoginTimes(user.getLoginTimes() + 1);
			this.userService.updateByPrimaryKeySelective(user);
			try {
				Cookie cookie = new Cookie("user", "" + user.getId());
				cookie.setMaxAge(60 * 60 * 2);
				cookie.setPath("/");
				Cookie cookie_2 = new Cookie("userinfo", user.getUsername());
				cookie_2.setMaxAge(60 * 60 * 2);
				cookie_2.setPath("/");
				response.addCookie(cookie);
				response.addCookie(cookie_2);
				response.sendRedirect(request.getServletContext().getContextPath() + "/index.html");
			} catch (IOException e) {
				logger.error("主页请求异常");
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().write("<script>alert('请求异常');</script>");
			} catch (IOException e) {
				logger.error("主页请求异常");
				e.printStackTrace();
			}
		}

	}

	@ResponseBody
	@RequestMapping("/logout")
	public Message logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		User user = (User) session.getAttribute("user");
		Message message = new Message();
		if (user != null && !user.getUsername().isEmpty()) {
			user.setIslogin("N");
			this.userService.updateByPrimaryKeySelective(user);
			Cookie cookie = new Cookie("user", "" + user.getId());
			cookie.setMaxAge(0);
			cookie.setPath("/");
			Cookie cookie_2 = new Cookie("userinfo", user.getUsername());
			cookie_2.setMaxAge(0);
			cookie_2.setPath("/");
			response.addCookie(cookie);
			response.addCookie(cookie_2);
			session.invalidate();
			message.setResult("success");
		} else {
			message.setResult("fail");
		}
		return message;
	}

	@ResponseBody
	@RequestMapping("/isOnline")
	public Message isOnline(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		User user = (User) session.getAttribute("user");
		User user_hasLogin = null;
		if (user != null)
			user_hasLogin = this.userService.getUserById(user.getId());
		Message message = new Message();
		if (user != null && !user.getUsername().isEmpty() && user_hasLogin != null && user_hasLogin.getPtypeId() == 1
				&& user_hasLogin.getCanlogin() == 1) {
			message.setResult("success");
		} else {
			message.setResult("inValid");
			for (Cookie c : request.getCookies()) {
				if ("user".equals(c.getName())) {
					User user_logout = this.userService.getUserById(Integer.parseInt(c.getValue()));
					user_logout.setIslogin("N");
					this.userService.updateByPrimaryKeySelective(user_logout);
					session.invalidate();
					message.setResult("fail");
				}
			}
		}
		return message;
	}

	@ResponseBody
	@RequestMapping("/validateUser")
	public Message validateUser(String username, String password, HttpSession session) {
		Message message = new Message();
		User user = this.userService.validate(username, password);
		if (user == null) {
			message.setResult("fail");
			message.setMessage("验证失败，请重新输入！");
		} else {
			if (user.getCanlogin() == 0) {
				message.setResult("fail");
				message.setMessage("您被禁止登录系统，请联系管理员解除限制！");
				return message;
			}
			if (user.getPtypeId() != 1) {
				message.setResult("fail");
				message.setMessage("您没有权限登录系统！");
				return message;
			}
			message.setResult("success");
			message.setMessage("验证成功！");
			message.setObject(user);
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(3600);
		}
		return message;
	}

	@ResponseBody
	@RequestMapping("/save")
	public Map<String, Object> saveUser(User userform, HttpSession session) {
		User user = (User) session.getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		String result="fail";
		int count = 0;
		if (user != null) {
			userform.setIslogin("N");
			userform.setLoginTimes(0);
			User temp = new User();
			temp.setUsername(userform.getUsername());
			List<User> users = this.userService.getUserList(1, 1, temp);
			if(users.isEmpty())
				count = this.userService.saveUser(userform);
			else
				result="fail";
			if (count > 0)
				result="success";
		} else {
			result="fail";
		}
		map.put("result",result);
		return map;
	}

	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> updateUser(User userform, HttpSession session) {
		User user = (User) session.getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		if (user != null) {
			int count = this.userService.updateByPrimaryKeySelective(userform);
			if (count > 0)
				map.put("result", "success");
			return map;
		} else
			return map;
		
		
		
	}

	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> deleteUser(int id, HttpSession session) {
		User user = (User) session.getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		if (user != null) {
			int count = this.userService.deleteByPrimaryKey(id);
			if (count > 0)
				map.put("result", "success");
			return map;
		} else
			return map;
	}
	
	@ResponseBody
	@RequestMapping("/updatePayImage")
	public Map<String, Object> updateProduct( HttpSession session,
			MultipartHttpServletRequest request) {
		User user = (User) session.getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		if (user != null&&user.getPtypeId()==1) {
			MultipartFile file = request.getFile("file");
			if (!file.isEmpty()) {
				String contextPath = session.getServletContext().getRealPath("/");
				String imagepath = "image/pay/wechat.jpg";
				String imageRealPath = contextPath + imagepath;
				String systemFilePath = "C:/Users/hongw/git/RMS/WebContent/"+imagepath;
				File imagePath = new File(imageRealPath);
				File systemFile = new File(systemFilePath);
				InputStream is = null;
				OutputStream os = null;
				OutputStream devos = null;
				try {
					is = file.getInputStream();
					os = new FileOutputStream(imagePath);
					devos = new FileOutputStream(systemFile);
					byte[] buffer = new byte[2048];
					while(is.read(buffer)!=-1) {
						os.write(buffer);
						devos.write(buffer);
					}
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					if(is!=null) {
						try {
							is.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(os!=null) {
						try {
							os.flush();
							os.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(devos!=null) {
						try {
							devos.flush();
							devos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			map.put("result", "success");
		} else
			map.put("result", "fail");
		return map;
	}
}