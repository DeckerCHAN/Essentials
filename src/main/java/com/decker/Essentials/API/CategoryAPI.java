package com.decker.Essentials.API;

import com.decker.Essentials.Category.Category;
import com.decker.Essentials.Category.CategoryChild;
import com.decker.Essentials.User.UserType;

public class CategoryAPI
{
    public static  void addCategoryChild(UserType userType,CategoryChild child)
    {
	switch (userType)
	{
	case Anonymous:Category.getInstance().AnonymousCategory.add(child); break;
	case Student:Category.getInstance().AnonymousCategory.add(child); break;
	case Teacher:Category.getInstance().AnonymousCategory.add(child); break;
	case SystemAdmin:Category.getInstance().AnonymousCategory.add(child); break;
	default:
	    return;
	}
    }
}
