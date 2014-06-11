package com.decker.Essentials.Category;

import java.awt.List;
import java.util.LinkedList;

import com.decker.Essentials.User.UserType;
import com.google.gson.Gson;

public class Category
{
    private static Category _Instance;

    public static Category getInstance()
    {
	if (_Instance == null)
	{
	    _Instance = new Category();
	}
	return _Instance;
    }

    public LinkedList<CategoryChild> AnonymousCategory;
    public LinkedList<CategoryChild> StudentCategory;
    public LinkedList<CategoryChild> TeacherCategory;
    public LinkedList<CategoryChild> AdministrarorCategory;

    public Category()
    {
	this.AnonymousCategory = new LinkedList<CategoryChild>();
	this.StudentCategory = new LinkedList<CategoryChild>();
	this.TeacherCategory = new LinkedList<CategoryChild>();
	this.AdministrarorCategory = new LinkedList<CategoryChild>();
	
	this.AnonymousCategory.add(new CategoryChild("icon-home", "Homepage", "Template/Homepage.html"));
	this.StudentCategory.add(new CategoryChild("icon-home", "Homepage", "Template/Homepage.html"));
	this.TeacherCategory.add(new CategoryChild("icon-home", "Homepage", "Template/Homepage.html"));
	this.AdministrarorCategory.add(new CategoryChild("icon-home", "Homepage", "Template/Homepage.html"));
	
    }

    public void addFunctionToCategory(UserType category, String iconName, String title, String url)
    {
	CategoryChild child = new CategoryChild(iconName, title, url);
	switch (category)
	{
	case Anonymous:
	    this.AnonymousCategory.add(child);
	    break;
	case Student:
	    this.AnonymousCategory.add(child);
	    break;
	case Teacher:
	    this.AnonymousCategory.add(child);
	    break;
	case SystemAdmin:
	    this.AnonymousCategory.add(child);
	    break;

	default:
	    break;
	}
    }

    public  String getJsonCategory(UserType type)
    {
	Gson serializator = new Gson();
	switch (type)
	{
	case Anonymous:
	    return serializator.toJson(_Instance.AnonymousCategory);

	case Student:
	    return serializator.toJson(_Instance.StudentCategory);

	case Teacher:
	    return serializator.toJson(_Instance.TeacherCategory);

	case SystemAdmin:
	    return serializator.toJson(_Instance.AdministrarorCategory);

	default:
	    return null;
	}

    }
}
