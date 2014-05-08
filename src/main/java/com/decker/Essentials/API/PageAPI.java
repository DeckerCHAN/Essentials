package com.decker.Essentials.API;

import com.decker.Essentials.Pages.PerloadJs;

public class PageAPI
{
    public static void addPreloadJs(String url)
    {
	PerloadJs.addJsUrl(url);
    }
}
