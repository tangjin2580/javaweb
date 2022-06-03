package org.example;

import javax.servlet.annotation.WebServlet;
import java.util.Properties;

@WebServlet(name = "PropertiesServlet", value = "/PropertiesServlet")
public class PropertiesServlet extends HelloServlet {
    public void  test(){
        Properties properties=new Properties();
    }
}

