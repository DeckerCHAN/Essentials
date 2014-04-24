package com.decker.Essentials.Category;

import java.awt.List;
import java.util.LinkedList;

import com.decker.Essentials.OpenAPI.User.UserType;

public class Category
{
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
}
