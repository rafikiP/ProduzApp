package br.ufba.comunicacao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/uploadFiles")
public class RecebeArquivo {
	public String ip;

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response UploadFile(@FormDataParam("wadl") InputStream wadl,
			@FormDataParam("wadl") FormDataContentDisposition detalhesWADL,
			@FormDataParam("rdf") FormDataContentDisposition detalhesRDF,
			@FormDataParam("rdf") InputStream rdf,
			@FormDataParam("ip") String ip) {
		
		String uploadedFileLocation = "src//main//resources//"+detalhesWADL.getFileName();
		this.ip=ip;
		String output = "complete!"; 
		
		saveToFile(wadl, uploadedFileLocation);
		uploadedFileLocation = "src//main//resources//"+ detalhesRDF.getFileName();
		saveToFile(rdf, uploadedFileLocation);
		
		 
        return Response.status(200).entity(output).build();
		
	}
	
	  // save uploaded file to new location
    private void saveToFile(InputStream uploadedInputStream,
        String uploadedFileLocation) {
 
        try {
            OutputStream out = null;
            int read = 0;
            byte[] bytes = new byte[1024];
 
            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
 
            e.printStackTrace();
        }
 
    }
 
}
