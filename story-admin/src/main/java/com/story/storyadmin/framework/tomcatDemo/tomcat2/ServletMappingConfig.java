package com.story.storyadmin.framework.tomcatDemo.tomcat2;


import java.util.ArrayList;
import java.util.List;

public class ServletMappingConfig {
    public static List<ServletMapping> servletMappingList = new ArrayList<>();

    static {
        servletMappingList.add(new ServletMapping("success","/success","com.dongshuo.YouWillSuccessServlet"));
        servletMappingList.add(new ServletMapping("findgirl","/findgirl","com.dongshuo.YouWillFindGirlServlet"));
    }
}

