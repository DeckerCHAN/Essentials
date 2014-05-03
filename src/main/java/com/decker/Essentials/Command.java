package com.decker.Essentials;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.decker.Essentials.Category.Category;
import com.decker.Essentials.User.User;

 class Command
{
    public static byte [] Execute(User sender,HttpServletRequest request,HttpServletResponse response)
    {
	switch (request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/')))
	{
	case "/Sidebar.exec":return Category.getInstance().getJsonCategory(sender.getUserType()).getBytes();
	
	case "/Login.exec":return sender.identifyUser().getBytes();
	
	case "/ReceiveUserData.exec":return sender.getUserInformationsJson().getBytes();
	    	

	default:
	    response.setStatus(404);
	}
	
	return null;
    }
}
