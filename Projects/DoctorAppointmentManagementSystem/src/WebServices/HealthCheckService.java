package WebServices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/healthCheck")
public class HealthCheckService {
	
	@GET
	@Produces("application/json")
	public Response registerPatient(@Context HttpHeaders headers) {
		return Response.status(200).entity("{'HealthCheck': 'Success'}").build();
	}
}
