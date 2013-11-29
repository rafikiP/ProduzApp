package com.arduino;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.devices.Arduino;
import com.devices.Temperature;
import com.resource.Definitions;

@Path("devices/sensor/temperature")
public class ArduinoTemperature extends Temperature{
	
	@Override
	@GET
	@Produces("text/javascript")
	@Path("/{id}")
	public String getMethod(@PathParam("id") String arg0) {
		
		try{
			Arduino arduino = new Arduino();
	        arduino.initialize();
	        
	        // Obtendo porta do Atuador
	        String port = arg0.split("_")[1];
	        
	        String value = arduino.getStatusDevice(port);
	        
	        arduino.close();
	        
	        System.out.println(value);
	        if(value.equals("-1")){
	        	this.setStatus("UNAVAILABLE");
	        }else{
	        	this.setStatus("ENABLE");
	        }
	        
	        this.setValue(Long.parseLong(value));
		}catch(Exception e){
			e.getMessage();
		}
		
		this.setId(arg0);
				
		return this.toString(this,"jsonp",Definitions.APP_NAME);
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String postMethod(@PathParam("id") String arg0,
			@DefaultValue("") @FormParam("status") String arg1) {
		
		try{
			Arduino arduino = new Arduino();
	        arduino.initialize();
	        
	        // Obtendo porta do Atuador
	        String port = arg0.split("_")[1];
	        
	        String value = arduino.getStatusDevice(port);
	        
	        arduino.close();
	        
	        System.out.println(value);
	        
	        this.setValue(Long.parseLong(value));
		}catch(Exception e){
			e.getMessage();
		}
		
		this.setId(arg0);
		this.setStatus(arg1);
		
		return this.toString(this,"json",Definitions.APP_NAME);
	}
}
