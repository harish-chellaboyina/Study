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
import DTO.AppointmentDTO;
import DTO.DoctorDTO;
import DTO.PatientDTO;
import DatabaseConnector.DBConnectorService;


/*
 * This class entirely deals with the registration of
 * Doctor, Patient and Appointment
 */
@Path("/register")
public class RegisterService {

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

	/*
	 * This service is used to register a patient
	 */
	@Path("/patient")
	@GET
	@Produces("application/json")
	public Response registerPatient(@Context HttpHeaders headers) {
		String username = headers.getRequestHeader(ServiceConstants.USERNAME_HEADER).get(0);
		String password = headers.getRequestHeader(ServiceConstants.PASSWORD_HEADER).get(0);
		String email = headers.getRequestHeader(ServiceConstants.EMAIL_HEADER).get(0);
		
		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setUsername(username);
		patientDTO.setPassword(password);
		patientDTO.setEmail(email);
		
		DBConnectorService dbs = new DBConnectorService();
		try{
			dbs.registerPatient(patientDTO);
			ResponseBuilder res = Response.status(200).entity(ServiceConstants.AUTH_SUCCESS_REPONSE);
			res.header("Access-Control-Allow-Origin", "*");
			res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
			res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
			
			return res.build();
		} catch (Exception e) {
			ResponseBuilder res = Response.status(200).entity("Failed " + e.getMessage());
			res.header("Access-Control-Allow-Origin", "*");
			res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
			res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
			
			return res.build();
		}
	}
	
	@Path("/doctor")
	@OPTIONS
	@Produces("application/json")
	public Response returnHeadersDoc(@Context HttpHeaders headers) {
		ResponseBuilder res = Response.status(200);
		res.header("Access-Control-Allow-Origin", "*");
		res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
		res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
		return res.build();
	}
	
	
	/*
	 * This service is used to register a doctor
	 */
	@Path("/doctor")
	@GET
	@Produces("application/json")
	public Response registerDoctor(@Context HttpHeaders headers) {
		String username = headers.getRequestHeader(ServiceConstants.USERNAME_HEADER).get(0);
		String password = headers.getRequestHeader(ServiceConstants.PASSWORD_HEADER).get(0);
		String email = headers.getRequestHeader(ServiceConstants.EMAIL_HEADER).get(0);
		String specialization = headers.getRequestHeader(ServiceConstants.SPECIALIZATION_HEADER).get(0);
		
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setUsername(username);
		doctorDTO.setSpecialization(specialization);
		doctorDTO.setPassword(password);
		doctorDTO.setEmail(email);
		
		DBConnectorService dbs = new DBConnectorService();
		try{
			dbs.registerDoctor(doctorDTO);
			ResponseBuilder res = Response.status(200).entity(ServiceConstants.AUTH_SUCCESS_REPONSE);
			res.header("Access-Control-Allow-Origin", "*");
			res.header("Access-Control-Allow-Headers", "username, password, patientid, doctorid");
			res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
			
			return res.build();
		} catch (Exception e) {
			ResponseBuilder res = Response.status(200).entity("Failed " + e.getMessage());
			res.header("Access-Control-Allow-Origin", "*");
			res.header("Access-Control-Allow-Headers", "username, password, patientid, doctorid");
			res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
			
			return res.build();
		}
	}
	
	@Path("/appointment")
	@OPTIONS
	@Produces("application/json")
	public Response returnHeadersForAppointment(@Context HttpHeaders headers) {
		ResponseBuilder res = Response.status(200);
		res.header("Access-Control-Allow-Origin", "*");
		res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
		res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
		return res.build();
	}

	
	/*
	 * This service is used to register an appointment 
	 */
	@Path("/appointment")
	@GET
	@Produces("application/json")
	public Response registerAppointment(@Context HttpHeaders headers) {
		String doctorId = headers.getRequestHeader(ServiceConstants.DOCTOR_ID_HEADER).get(0);
		String date = headers.getRequestHeader(ServiceConstants.DATE_HEADER).get(0);
		String slot = headers.getRequestHeader(ServiceConstants.SLOT_HEADER).get(0);
		String problemDesc = headers.getRequestHeader(ServiceConstants.PROBLEM_DESCRIPTION_HEADER).get(0);
		String patientId = null;
		
		DBConnectorService dbs = new DBConnectorService();
		
		try {
			patientId = headers.getRequestHeader(ServiceConstants.PATIENT_ID_HEADER).get(0);
		} catch (Exception e) {
			if (patientId == null) {
				String patientEmail = headers.getRequestHeader(ServiceConstants.EMAIL_HEADER).get(0);
				PatientDTO temp = new PatientDTO();
				temp.setEmail(patientEmail);
				temp = dbs.getPatientDetailsWithEmail(temp);
				patientId = Integer.toString(temp.getPatientId());
				
			}
		}
		
		AppointmentDTO appDTO = new AppointmentDTO();
		
		appDTO.setAppointmentDate(date);
		appDTO.setAppointmentSlot(slot);
		appDTO.setPatientRemarks(problemDesc);
		appDTO.setDoctorUserName(Integer.parseInt(doctorId));
		appDTO.setPatientUserName(Integer.parseInt(patientId));
		
		try{
			dbs.registerAppointment(appDTO);
			ResponseBuilder res = Response.status(200).entity("Appointment created successfully");
			res.header("Access-Control-Allow-Origin", "*");
			res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
			res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
			
			return res.build();
		} catch (Exception e) {
			ResponseBuilder res = Response.status(200).entity("Failed " + e.getMessage());
			res.header("Access-Control-Allow-Origin", "*");
			res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
			res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
			
			return res.build();
		}
	}

}