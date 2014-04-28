package com.decker.Essentials.User;

import java.net.HttpCookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class User
{
    private UserType Type;
    private Cookie[] Cookies;
    public User( HttpServletRequest request,HttpServletResponse response)
    {
	this.Cookies=request.getCookies();
	this.setUserType();
    }
    private void setUserType()
    {
	//if(seesen)
	this.Type=UserType.Anonymous;
    }	
    
    public UserType getUserType()
    {
	//if(seesen)
	return this.Type;
    }	
}
