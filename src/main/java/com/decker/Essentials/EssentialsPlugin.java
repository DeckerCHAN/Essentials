package com.decker.Essentials;

import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.decker.Essentials.Category.Category;
import com.decker.Essentials.User.User;

public class EssentialsPlugin extends org.sipc.se.plugin.PluginImpl
{

    public Category category;

    public EssentialsPlugin()
    {
	super();
	this.category = Category.getInstance();
    }

    @Override
    public void getResponse(HttpServletRequest request, HttpServletResponse response)
    {
	String url = request.getRequestURI();
	String target;
	Matcher matcher = Pattern.compile("(?<=Essentials/).*").matcher(url);
	if (matcher.find())
	{
	    target = matcher.group(0);
	} else
	{
	    target = "Default.html";
	}
	target = target.replaceAll("\\?.*", null);
	OutputStream stream;
	try
	{
	    stream = response.getOutputStream();
	    byte[] resource = null;

	    if (target.matches(".*.exec"))
	    {
		// dynamic generate
		User current = new User(request,response);
		response.setContentType("application/json");
		resource = Command.Execute(current, request, response);
	    } else
	    {
		// static resource
		resource = ResourceManager.getInstance().getResource( target);
		if (resource.length == 0)
		{
		    response.setStatus(404);
		}
	    }

	    stream.write(resource);

	} catch (IOException e)
	{
	    e.printStackTrace();
	    response.setStatus(500);
	    return;
	}

    }

    @Override
    public String getUrl()
    {
	return "~/Essentials";
    }

    @Override
    public boolean onEnable()
    {
	return true;
    }
}
