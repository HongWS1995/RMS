package com.hong.webSocket;

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.socket.CloseStatus;  
import org.springframework.web.socket.TextMessage;  
import org.springframework.web.socket.WebSocketSession;  
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import tempBean.TableOrder;

import java.io.IOException;  
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;  
  
/** 
 * Websocket处理器 
 */  
  
public class WebSocketHandler extends TextWebSocketHandler {  
    private final static Logger LOGGER = LoggerFactory.getLogger(WebSocketHandler.class);  
 
    //已建立连接的用户  
    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();  
  
    /** 
     * 处理前端发送的文本信息 
     * js调用websocket.send时候，会调用该方法 
     * 
     * @param session 
     * @param message 
     * @throws Exception 
     */  
    @Override  
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {  
    	String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
    	String payLoad = message.getPayload();
    	ObjectMapper mapper = new ObjectMapper();
    	TableOrder trOrder = mapper.readValue(payLoad,TableOrder.class);
        //回复一条信息
        if(username.contains("user")){
        	 // 获取提交过来的消息详情  
            LOGGER.debug("收到用户 " + username + "的订单消息:" + message.getPayload());
            String tableName = trOrder.getTableName();
            String orderId = trOrder.getOrderId();
            LOGGER.debug("收到用户 " + username + "的订单餐桌信息:" + tableName);
            LOGGER.debug("收到用户 " + username + "的订单餐桌信息:" + orderId);
        	session.getAttributes().put("orderId",orderId);
        	//两个方法在没有使用JSF的项目中是没有区别的
//        	RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
////        	                                            RequestContextHolder.getRequestAttributes();
//        	//从session里面获取对应的值
//        	String str = (String) requestAttributes.getAttribute("name",RequestAttributes.SCOPE_SESSION);
//        	HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
//        	HttpSession httpSession = request.getSession();
        	LOGGER.debug("session 中的属性test:" +session.getAttributes().get("test"));
        	TextMessage textMessage = new TextMessage(trOrder.getTableName()+"桌的客人已提交订单,订单编号:"+trOrder.getOrderId()+"请及时处理!");
        	sendMessageToUser("admin",textMessage);
        }
        else {
        	for (WebSocketSession user : users) {
        		System.out.println(user.getAttributes().get("orderId"));
                if(trOrder.getOrderId().equals(user.getAttributes().get("orderId"))) {
                	if (user.isOpen()) {  
                        if("已处理".equals(trOrder.getStatus()))
                        	user.sendMessage(new TextMessage("您的订单"+trOrder.getStatus()));  
                	}     
                }
        	}
        }
    }
  
  
    /** 
     * 当新连接建立的时候，被调用 
     * 连接成功时候，会触发页面上onOpen方法 
     * 
     * @param session 
     * @throws Exception 
     */  
    @Override  
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	
        users.add(session);  
        String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");  
        LOGGER.info("用户 " + username + " Connection Established");  
        //session.sendMessage(new TextMessage(username + "接入系统成功"));  
        
    }  
  
    /** 
     * 当连接关闭时被调用 
     * 
     * @param session 
     * @param status 
     * @throws Exception 
     */  
    @Override  
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {  
        String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");  
        LOGGER.info("用户 " + username + " Connection closed. Status: " + status);  
        users.remove(session);  
    }  
  
    /** 
     * 传输错误时调用 
     * 
     * @param session 
     * @param exception 
     * @throws Exception 
     */  
    @Override  
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {  
        String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");  
        if (session.isOpen()) {  
            session.close();  
        }  
        LOGGER.debug("用户: " + username + " websocket connection closed......");  
        users.remove(session);  
    }  
  
    /** 
     * 给所有在线用户发送消息 
     * 
     * @param message 
     */  
    public void sendMessageToUsers(TextMessage message) {  
        for (WebSocketSession user : users) {  
            try {  
                if (user.isOpen()) {  
                    user.sendMessage(message);  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    /** 
     * 给某个用户发送消息 
     * 
     * @param userName 
     * @param message 
     */  
    public void sendMessageToUser(String userName, TextMessage message) {  
        for (WebSocketSession user : users) {  
            if (user.getAttributes().get("WEBSOCKET_USERNAME").equals(userName)) {  
                try {  
                    if (user.isOpen()) {  
                        user.sendMessage(message);  
                    }  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
                break;  
            }  
        }  
    }  
}  

