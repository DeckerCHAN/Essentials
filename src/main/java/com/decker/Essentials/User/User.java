package com.decker.Essentials.User;

import java.net.HttpCookie;
import java.security.MessageDigest;
import java.sql.ResultSet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sipc.se.dao.factory.OperateFactory;

public class User
{
    private UserType Type;
    private Cookie[] Cookies;

    public User(HttpServletRequest request, HttpServletResponse response)
    {
	this.Cookies = request.getCookies();
	if (this.Cookies.length == 0)
	{

	}
	this.setUserType(UserType.Anonymous);
    }

    private void setUserType(UserType userType)
    {
	// if(seesen)
	this.Type = userType;
    }

    public UserType getUserType()
    {
	// if(seesen)
	return this.Type;
    }

    public static Cookie[] GetLoginCookies(String username, String password)
    {

	try
	{
	    password = new String(MessageDigest.getInstance("MD5").digest(password.getBytes()));
	    ResultSet resultSet = OperateFactory.getInstance().getDAOInstance().doQuery(String.format("SELECT * FROM Essentials.User WHERE UserID = '%s' and Password = '%s' ", username, password));
	    if (!resultSet.next())
	    {
		return new Cookie[] { new Cookie("identification", username), new Cookie("varify", password) };
	    }
	} catch (Exception e)
	{
	    System.out.print(e.toString());

	}

	return new Cookie[0];
    }
}
