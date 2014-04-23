package com.decker.Essentials;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Center implements org.sipc.se.plugin.Plugin
{

    public void getResponse(HttpServletRequest request, HttpServletResponse response)
    {
	String url = request.getRequestURI();
	String target = url.substring(url.indexOf("Essentials") + "Essentials".length() + 1);
	OutputStream stream;
	try
	{

	    byte[] resource = ResourceManager.getInstance().getResource(target);
	    if(resource.length==0)
	    {
		response.setStatus(404);
	    }

	    stream = response.getOutputStream();
	    stream.write(resource);

	} catch (IOException e)
	{

	}

    }

    public String getUrl()
    {
	return "Essentials";
    }

    public boolean onEnable()
    {

	return true;
    }

}
