package WebServices;

import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;



@Path("/")
public class DefaultOptionsHandler {
	
	@OPTIONS
	@Produces("application/json")
	public Response returnHeaders(@Context HttpHeaders headers) {
		ResponseBuilder res = Response.status(200);
		res.header("Access-Control-Allow-Origin", "*");
		res.header("Access-Control-Allow-Headers", "username, password, patientid, doctorid");
		res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
		
	
		return res.build();
	}

}
