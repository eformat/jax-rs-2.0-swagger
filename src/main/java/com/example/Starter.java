package com.example;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.example.config.AppConfig;

public class Starter {	
	private static final String CONTEXT_PATH = "rest";
	
	public static void main( final String[] args ) throws Exception {
		Resource.setDefaultUseCaches( false );
		
		String webPort = System.getProperty("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        System.out.println("Web PORT is:"+webPort);

        String proxyWebPort = System.getProperty("PROXY_PORT");
        if(proxyWebPort == null || proxyWebPort.isEmpty()) {
            proxyWebPort = "8080";
        }
        System.out.println("Proxy Web PORT is:" + proxyWebPort);               		
		System.setProperty( AppConfig.SERVER_PROXY_PORT, proxyWebPort );
        
		final Server server = new Server( new Integer(webPort).intValue() );		
		System.setProperty( AppConfig.SERVER_PORT, webPort );
		
		String serverHost = System.getProperty("SWAG_HOST");
        if(serverHost == null || serverHost.isEmpty()) {
            serverHost = "localhost";
        }
        System.out.println("Server HOST is:"+serverHost);
		
		System.setProperty( AppConfig.SERVER_HOST, serverHost );
		System.setProperty( AppConfig.CONTEXT_PATH, CONTEXT_PATH );				

		// Configuring Apache CXF servlet and Spring listener  
		final ServletHolder servletHolder = new ServletHolder( new CXFServlet() ); 		 		
 		final ServletContextHandler context = new ServletContextHandler(); 		
 		context.setContextPath( "/" );
 		context.addServlet( servletHolder, "/" + CONTEXT_PATH + "/*" ); 	 		
 		context.addEventListener( new ContextLoaderListener() ); 		 		
 		context.setInitParameter( "contextClass", AnnotationConfigWebApplicationContext.class.getName() );
 		context.setInitParameter( "contextConfigLocation", AppConfig.class.getName() );
 		
 	    // Configuring Swagger as static web resource
 		final ServletHolder swaggerHolder = new ServletHolder( new DefaultServlet() );
 		final ServletContextHandler swagger = new ServletContextHandler();
 		swagger.setContextPath( "/swagger" );
 		swagger.addServlet( swaggerHolder, "/*" ); 	 		
        swagger.setResourceBase( new ClassPathResource( "/webapp" ).getURI().toString() );

 		final HandlerList handlers = new HandlerList();
 		handlers.addHandler( swagger );
 		handlers.addHandler( context );
 		
        server.setHandler( handlers );
        server.start();
        server.join();	
	}
}

