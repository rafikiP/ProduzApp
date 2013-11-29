package com.resource;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.devices.DefinitionsMule;

@Path("resource")
public class Resources {

	@GET
	@Produces("text/xml")
	public File descriptionRDF() {

		try {

			File description = new File(DefinitionsMule.MULE_HOME
					+ DefinitionsMule.DIR_SEPARATOR + DefinitionsMule.MULE_APPS
					+ DefinitionsMule.DIR_SEPARATOR + Definitions.APP_NAME
					+ DefinitionsMule.DIR_SEPARATOR + Definitions.DIR_RDF
					+ DefinitionsMule.DIR_SEPARATOR + Definitions.FILE_NAME_RDF);

			return description;

		} catch (Exception e) {
			e.getMessage();
		}

		return null;

	}

	@GET
	@Path("/application")
	@Produces("text/xml")
	public File descriptionWADL() {

		try {

			File description = new File(DefinitionsMule.MULE_HOME
					+ DefinitionsMule.DIR_SEPARATOR + DefinitionsMule.MULE_APPS
					+ DefinitionsMule.DIR_SEPARATOR + Definitions.APP_NAME
					+ DefinitionsMule.DIR_SEPARATOR + Definitions.DIR_WADL
					+ DefinitionsMule.DIR_SEPARATOR + Definitions.FILE_NAME_WADL);

			return description;

		} catch (Exception e) {
			e.getMessage();
		}

		return null;

	}
}