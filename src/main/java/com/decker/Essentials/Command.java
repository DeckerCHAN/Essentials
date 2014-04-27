package com.decker.Essentials;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.decker.Essentials.Category.Category;
import com.decker.Essentials.User.User;

public class Command
{
    public static byte [] Execute(User sender,HttpServletRequest request,HttpServletResponse response)
    {
	switch (request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/')))
	{
	case "/Sidebar.exec":return Category.getInstance().getJsonCategory(sender.Type).getBytes();
	
	case "/Login.exec":return "{\"status\":0,\"text\":\"hehe\"}".getBytes();
	
	case "/ReceiveUserData.exec":return "{\"username\":0,\"category\":\"hehe\"}".getBytes();
	    	

	default:
	    response.setStatus(404);
	}
	
	return null;
    }
}
