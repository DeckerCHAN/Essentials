package com.decker.Essentials.OpenAPI.Category;

import java.awt.List;
import java.util.LinkedList;

import com.decker.Essentials.OpenAPI.User.UserType;


public class Category
{
    public LinkedList<CategoryChild> AnonymousCategory;
    public LinkedList<CategoryChild> StudentCategory;
    public LinkedList<CategoryChild> TeacherCategory;
    public LinkedList<CategoryChild> AdministrarorCategory;
    
    private Category()
    {
	this.AnonymousCategory=new LinkedList<CategoryChild>();
	this.StudentCategory=new LinkedList<CategoryChild>();
	this.TeacherCategory=new LinkedList<CategoryChild>();
	this.AdministrarorCategory=new LinkedList<CategoryChild>();
 }    
    
    
    
    
    private static Category _Instance;
    
    public static Category GetInstance()
    {
	Init();
	return _Instance;
    }
    
    
    public static void addFunctionToCategory(UserType category,String iconName,String title,String url)
    {
	Init();
	CategoryChild child =new CategoryChild(iconName,title,url);
	switch (category)
	{
	case Anonymous:_Instance.AnonymousCategory.add(child);break;
	case Student:_Instance.AnonymousCategory.add(child);break;
	case Teacher:_Instance.AnonymousCategory.add(child);break;
	case SystemAdmin:_Instance.AnonymousCategory.add(child);break;
	
	default:
	    break;
	}
    }
    
    public static void Init()
    {
	if(_Instance==null)
	{
	    _Instance=new Category();
	}
    }
}
