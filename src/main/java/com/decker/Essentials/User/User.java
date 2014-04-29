package com.decker.Essentials.User;

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
    /* private UserType Type; */
    private HttpServletRequest Requset;
    private HttpServletResponse Response;
    private ResultSet AboutUser;

    /*
     * private String UserName; private String Password; private String
     * DisplayName; private Date LastLogin;
     */

    public User(HttpServletRequest request, HttpServletResponse response)
    {
	this.Requset = request;
	this.Response = response;
	if ((request.getAttribute("username") != null) && (request.getAttribute("password") != null))
	{
	    this.AboutUser = ReceiveDataFromUser((String) request.getAttribute("username"), ConvertMD5((String) request.getAttribute("password")));
	}

    }

    public UserType GetUserType()
    {
	try
	{
	    if (this.AboutUser == null )
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
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return UserType.Anonymous;
	}

    }

    public Date GetLastLogin()
    {
	try
	{
	    if (this.AboutUser == null )
	    return new Date();
	    
	    return this.AboutUser.getDate("LastLoginDate");
	    
	} catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return new Date();
	}
    }
    
    public static ResultSet ReceiveDataFromUser(String username, String MD5password)
    {
	String sqlString = String.format("SELECT * FROM Essentials.User WHERE UserID = '%s' and Password = '%s' ", username, MD5password);
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
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    public static String ConvertMD5(String string)
    {
	try
	{
	    return new String(MessageDigest.getInstance("MD5").digest(string.getBytes()));
	} catch (NoSuchAlgorithmException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return null;
	}
    }

}
