package com.decker.Essentials.API;

import javax.servlet.http.HttpServletRequest;

import com.decker.Essentials.User.UserType;

public class UserAPI
{
    public static UserType getUserType(HttpServletRequest request)
    {
	//TODO:Add User Process
	return UserType.Anonymous;
    }
}
