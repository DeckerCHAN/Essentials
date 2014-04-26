package com.decker.Essentials;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.decker.Essentials.Category.Category;
import com.decker.Essentials.User.User;


public class Center extends org.sipc.se.plugin.PluginImpl
{

    Category category;
    public Center()
    {
	super();
	this.category=Category.getInstance();
    }

    @Override
    public void getResponse(HttpServletRequest request, HttpServletResponse response)
    {
	String url = request.getRequestURI();
	String target = url.substring(url.indexOf("Essentials") + "Essentials".length() + 1);
	OutputStream stream;
	try
	{
	    stream = response.getOutputStream();
	    byte[] resource=null;

	    if (target.matches(".*.exec"))
	    {		
		//dynamic generate
		User current=new User(request.getCookies());
		resource = Command.Execute(current,request,response);
	    } else
	    {
		//static resource
		resource = ResourceManager.getInstance().getResource("Content/"+target);
	    }

	    if (resource.length == 0)
	    {
		response.setStatus(404);
	    }

	    stream.write(resource);

	} catch (IOException e)
	{

	}
	
    }

    @Override
    public String getUrl()
    {
	return "Essentials";
    }

    @Override
    public boolean onEnable()
    {
	// TODO Auto-generated method stub
	return true;
    }
}
