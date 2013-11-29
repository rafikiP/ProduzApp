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
import com.devices.Buzzer;
import com.devices.DefinitionsArduino;
import com.resource.Definitions;

@Path("devices/actuator/buzzer")
public class ArduinoBuzzer extends Buzzer{
	
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
	        
		}catch(Exception e){
			e.getMessage();
		}
		
        
		this.setId(arg0);
        
		return this.toString(this,"jsonp", Definitions.APP_NAME);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String postMethod(@PathParam("id") String arg0,
			@DefaultValue("ENABLE") @FormParam("status") String arg1,
			@DefaultValue("FAST") @FormParam("state") String arg2,
			@DefaultValue("") @FormParam("duration") String arg3) {
		
		try{
			Arduino arduino = new Arduino();
	        arduino.initialize();
	        
	        // Obtendo porta do Atuador
	        String port = arg0.split("_")[1];
	        
	        // Valor padrão é SLOW
	        String state;
	        
	        switch (arg2) {
			case "FAST":
				state = "1";
				break;
			case "SLOW":
				state = "2";
				break;
			case "CUSTOM":
				state = "3";
				break;

			default:
				state = "1";
				break;
			}
	        
	        System.out.println(arg2 + " STATE"  + state);
	        
	      	arduino.sendComand(DefinitionsArduino.WHITE_INDEX + port + state + arg3);
	      	
	      	this.setState(arg2);
	      	this.setStatus(arg1);
	      	
	      	System.out.println(DefinitionsArduino.WHITE_INDEX + port + state + arg3);
	      	
	        arduino.close();
		}catch(Exception e){
			e.getMessage();
		}
		
		this.setId(arg0);
		
		return this.toString(this,"json",Definitions.APP_NAME);
	}

}
