package com.decker.Essentials;

import java.io.IOException;
import java.io.InputStream;

public class ResourceManager
{

    private ResourceManager()
    {

    }

    public static ResourceManager _Instance;

    public static ResourceManager getInstance()
    {
	if (_Instance == null)
	{
	    _Instance = new ResourceManager();
	}
	return _Instance;
    }

    public byte[] getResource(String resourceName) throws IOException
    {
	InputStream is = this.getClass().getResourceAsStream("/" + resourceName);

	return sun.misc.IOUtils.readFully(is, -1, true);

    }

}