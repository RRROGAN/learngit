package com.qq.client.model;
//¸øchat·þÎñ
import com.qq.common.*;
public class QqClientUser {

	public boolean checkUser(User u)
	{
		return new QqClientConServer().sendLoginInfoToServer(u);
	}
	
}
