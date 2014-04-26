package com.decker.Essentials.User;

import java.net.HttpCookie;

import javax.servlet.http.HttpSession;

public class User
{
    public UserType Type;
    private HttpCookie [] Cookies;
    public User( HttpCookie []  cookies)
    {
	this.Cookies=cookies;
    }
    
    private void chaseUserType()
    {
	//if(seesen)
	this.Type=UserType.Anonymous;
    }	
}
