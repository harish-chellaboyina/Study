package WebServices;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import Constants.ServiceConstants;
import DTO.DoctorDTO;
import DTO.PatientDTO;
import DatabaseConnector.DBConnectorService;

 /*
  * This class entirely deals with authentication of patient and doctor
  */
@Path("/authenticate")
public class AuthenticateService {
	
	@Path("/patient")
	@OPTIONS
	@Produces("application/json")
	public Response returnHeaders(@Context HttpHeaders headers) {
		ResponseBuilder res = Response.status(200);
		res.header("Access-Control-Allow-Origin", "*");
		res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
		res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
		return res.build();
	}

	
	@Path("/patient")
	@GET
	@Produces("application/json")
	public Response patientLogin(@Context HttpHeaders headers) {
		String email = headers.getRequestHeader(ServiceConstants.EMAIL_HEADER).get(0);
		String password = headers.getRequestHeader(ServiceConstants.PASSWORD_HEADER).get(0);
		
		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setEmail(email);
		
		DBConnectorService dbs = new DBConnectorService();
		PatientDTO dbPatientDetails = dbs.getPatientDetailsWithEmail(patientDTO);
		
		if (dbPatientDetails != null && dbPatientDetails.getPassword().equals(password))
			return Response.status(200).entity(ServiceConstants.AUTH_SUCCESS_REPONSE).build();
		return Response.status(200).entity(ServiceConstants.AUTH_FAILURE_REPONSE).build();
	}
	
	
	@Path("/doctor")
	@OPTIONS
	@Produces("application/json")
	public Response returnHeadersDoctor(@Context HttpHeaders headers) {
		ResponseBuilder res = Response.status(200);
		res.header("Access-Control-Allow-Origin", "*");
		res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
		res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
		return res.build();
	}
	
	@Path("/doctor")
	@GET
	@Produces("application/json")
	public Response doctorLogin(@Context HttpHeaders headers) {
		String email = headers.getRequestHeader(ServiceConstants.EMAIL_HEADER).get(0);
		String password = headers.getRequestHeader(ServiceConstants.PASSWORD_HEADER).get(0);
		
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setEmail(email);
		
		DBConnectorService dbs = new DBConnectorService();
		DoctorDTO dbDoctorDetails = dbs.getDoctorDetailsWithEmail(doctorDTO);
		
		if (dbDoctorDetails != null && dbDoctorDetails.getPassword().equals(password))
			return Response.status(200).entity(ServiceConstants.AUTH_SUCCESS_REPONSE).build();
		
		return Response.status(200).entity(ServiceConstants.AUTH_FAILURE_REPONSE).build();
	}
}