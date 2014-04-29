package com.decker.Essentials.User;

import java.math.BigInteger;
import java.net.HttpCookie;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.sipc.se.dao.factory.OperateFactory;

public class User
{
    private HttpServletRequest Requset;
    private HttpServletResponse Response;
    private ResultSet AboutUser;

    public User(HttpServletRequest request, HttpServletResponse response)
    {
	this.Requset = request;
	this.Response = response;
	if (request.getCookies() != null && request.getCookies().length != 0)
	{
	    for (Cookie cookie : request.getCookies())
	    {
		if (cookie.getName().equals("identification"))
		{
		    String[] arg = cookie.getValue().split(":");
		    try
		    {
			this.AboutUser = ReceiveDataFromUser(arg[0], arg[1]);
		    } catch (Exception e)
		    {
			e.printStackTrace();
			this.AboutUser = null;
		    }

		}
	    }
	}

    }

    public String getUserInformationsJson()
    {

	if (this.AboutUser != null)
	{
	    return "{\"username\":\"" + this.getUserName() + "\",\"category\":\"" + this.getUserType().toString() + "\"}";
	}

	return "";

    }

    public String identifyUser()
    {
	if ((this.Requset.getParameter("username") != null) && (this.Requset.getParameter("password") != null))
	{
	    this.AboutUser = ReceiveDataFromUser((String) this.Requset.getParameter("username"), ConvertMD5((String) this.Requset.getParameter("password")));
	    if (this.AboutUser != null)
	    {
		this.generateCookie();
		return "{\"status\":0,\"text\":\"Login Success\"}";
	    }
	}
	return "{\"status\":2,\"text\":\"User name or password invalid\"}";
    }

    public void generateCookie()
    {
	if (this.AboutUser == null)
	    return;
	try
	{
	    Cookie cookie = new Cookie("identification", this.AboutUser.getString("Name") + ":" + this.AboutUser.getString("Password"));
	    this.Response.addCookie(cookie);
	} catch (SQLException e)
	{
	    e.printStackTrace();
	}
    }

    public UserType getUserType()
    {
	try
	{
	    if (this.AboutUser == null)
		return UserType.Anonymous;
	    switch (this.AboutUser.getInt("InRole"))
	    {
	    case 0:
		return UserType.SystemAdmin;
	    case 1:
		return UserType.Teacher;
	    case 2:
		return UserType.Student;
	    case 3:
		return UserType.Anonymous;
	    default:
		return UserType.Anonymous;
	    }
	} catch (Exception e)
	{
	    e.printStackTrace();
	    return UserType.Anonymous;
	}

    }

    public String getUserName()
    {
	if (this.AboutUser == null)
	    return null;
	try
	{
	    return this.AboutUser.getString("Name");
	} catch (Exception e)
	{
	    e.printStackTrace();
	    return null;
	}

    }

    public Date getLastLogin()
    {
	try
	{
	    if (this.AboutUser == null)
		return new Date();

	    return this.AboutUser.getDate("LastLoginDate");

	} catch (SQLException e)
	{
	    e.printStackTrace();
	    return new Date();
	}
    }

    public static ResultSet ReceiveDataFromUser(String username, String MD5password)
    {
	String sqlString = String.format("SELECT * FROM Essentials.User WHERE Name = '%s' and Password = '%s' ", username, MD5password);
	ResultSet resultSet;
	try
	{
	    resultSet = OperateFactory.getInstance().getDAOInstance().doQuery(sqlString);
	    if (!resultSet.first())
	    {
		return null;
	    } else
	    {
		return resultSet;
	    }
	} catch (Exception e)
	{
	    e.printStackTrace();
	}
	return null;
    }

    public static String ConvertMD5(String string)
    {
	try
	{
	    MessageDigest digest = MessageDigest.getInstance("MD5");
	    digest.update(string.getBytes(), 0, string.length());
	    return new BigInteger(1, digest.digest()).toString(16).toUpperCase();

	} catch (NoSuchAlgorithmException e)
	{
	    e.printStackTrace();
	    return null;
	}
    }

}
