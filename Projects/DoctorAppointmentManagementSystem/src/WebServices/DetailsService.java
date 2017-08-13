package WebServices;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import Utils.SendMailTLS;

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
import DTO.OrganDonorDTO;
import DTO.PatientDTO;
import DatabaseConnector.DBConnectorService;


/*
 * This class provides services for getting and updating details of
 * Patients, Doctors and Appointments
 */
@Path("/details")
public class DetailsService {

	@Path("/getAllSpecilizations")
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
	 * This service is used to get all unique specializations of all doctors
	 */
	@Path("/getAllSpecilizations")
	@GET
	@Produces("application/json")
	public Response getAllSpecializations(@Context HttpHeaders headers) {
		DBConnectorService dbs = new DBConnectorService();
		List<String> specializationsList = dbs.getAllSpecilizations();
		
		ResponseBuilder res = Response.status(200).entity(StringUtils.join(specializationsList, ","));
		res.header("Access-Control-Allow-Origin", "*");
		res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
		res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
		
		return res.build();
	}

	
	@Path("/getDoctorsForSpecialization")
	@OPTIONS
	@Produces("application/json")
	public Response returnHeaders1(@Context HttpHeaders headers) {
		ResponseBuilder res = Response.status(200);
		res.header("Access-Control-Allow-Origin", "*");
		res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
		res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
		return res.build();
	}

	
	/*
	 * This service is used to get doctors list for provided specialization
	 */
	@Path("/getDoctorsForSpecialization")
	@GET
	@Produces("application/json")
	public Response getDoctorsForSpecilization(@Context HttpHeaders headers) {
		String specialization = headers.getRequestHeader(ServiceConstants.SPECIALIZATION_HEADER).get(0);
		DBConnectorService dbs = new DBConnectorService();
		JSONObject result = dbs.getDoctorsForSpecilization(specialization);
		
		ResponseBuilder res = Response.status(200).entity(result.toString());
		res.header("Access-Control-Allow-Origin", "*");
		res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
		res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
		
		return res.build();
	}
	
	@Path("/getAppointmentsForPatient")
	@OPTIONS
	@Produces("application/json")
	public Response returnHeaders2(@Context HttpHeaders headers) {
		ResponseBuilder res = Response.status(200);
		res.header("Access-Control-Allow-Origin", "*");
		res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
		res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
		return res.build();
	}

	
	/*
	 * This service is used to get all appointments of patient
	 */
	@Path("/getAppointmentsForPatient")
	@GET
	@Produces("application/json")
	public Response getAppointmentsOfPatient(@Context HttpHeaders headers) {
		
		DBConnectorService dbs = new DBConnectorService();
		
		String email = null;
		try {
			email = headers.getRequestHeader("email").get(0);
		} catch (Exception e) {
			int patientId =  Integer.parseInt(headers.getRequestHeader("patientId").get(0));
			PatientDTO temp = new PatientDTO();
			temp.setPatientId(patientId);
			temp = dbs.getPatientDetails(temp);
			email = temp.getEmail();
		}
		
		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setEmail(email);
		
		
		patientDTO = dbs.getPatientDetailsWithEmail(patientDTO);
		List<AppointmentDTO> appointments = dbs.getAppointmentsOfPatient(patientDTO);
		
		for(int i = 0;i < appointments.size();i++)
		{
			AppointmentDTO temp = appointments.get(i);
			DoctorDTO t = new DoctorDTO();
			t.setDoctorId(temp.getDoctorUserName());
			t = dbs.getDoctorDetails(t);
			temp.setDoctorName(t.getUsername());
		}
		
        JSONArray result = new JSONArray(appointments);
		ResponseBuilder res = Response.status(200).entity(result.toString());
		res.header("Access-Control-Allow-Origin", "*");
		res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
		res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
		
		return res.build();
	}

	@Path("/getAppointmentsForDoctor")
	@OPTIONS
	@Produces("application/json")
	public Response returnHeaders9(@Context HttpHeaders headers) {
		ResponseBuilder res = Response.status(200);
		res.header("Access-Control-Allow-Origin", "*");
		res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
		res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
		return res.build();
	}

	/*
	 * This service is used to get all appointments of a doctor
	 */
	@Path("/getAppointmentsForDoctor")
	@GET
	@Produces("application/json")
	public Response getAppointmentsOfDoctor(@Context HttpHeaders headers) {
		
		String email = headers.getRequestHeader("email").get(0);
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setEmail(email);
		
		
		DBConnectorService dbs = new DBConnectorService();
		doctorDTO = dbs.getDoctorDetailsWithEmail(doctorDTO);
		List<AppointmentDTO> appointments = dbs.getAppointmentsOfDoctor(doctorDTO);
		
		for(int i = 0;i < appointments.size();i++)
		{
			AppointmentDTO temp = appointments.get(i);
			PatientDTO t = new PatientDTO();
			t.setPatientId(temp.getPatientUserName());
			t = dbs.getPatientDetails(t);
			temp.setPatientName(t.getUsername());
		}
		
        JSONArray result = new JSONArray(appointments);
		ResponseBuilder res = Response.status(200).entity(result.toString());
		res.header("Access-Control-Allow-Origin", "*");
		res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
		res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
		
		return res.build();
	}
	

	@Path("/deleteAppointment")
	@OPTIONS
	@Produces("application/json")
	public Response returnHeaders3(@Context HttpHeaders headers) {
		ResponseBuilder res = Response.status(200);
		res.header("Access-Control-Allow-Origin", "*");
		res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
		res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
		return res.build();
	}

	
	/*
	 * This service is used to delete an appointment.
	 */
	@Path("/deleteAppointment")
	@GET
	@Produces("application/json")
	public Response deleteAppointment(@Context HttpHeaders headers) {
		
		String appointmentId = headers.getRequestHeader(ServiceConstants.APPOINTMENT_ID_HEADER).get(0);
		
		AppointmentDTO appointmentDetails = new AppointmentDTO();
		appointmentDetails.setAppointmentId(Integer.parseInt(appointmentId));
		
		DBConnectorService dbs = new DBConnectorService();
		try {
			dbs.deleteAppointment(appointmentDetails);
			ResponseBuilder res = Response.status(200).entity("Deleted successfully");
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
	
	@Path("/getAppointment")
	@OPTIONS
	@Produces("application/json")
	public Response returnHeaders4(@Context HttpHeaders headers) {
		ResponseBuilder res = Response.status(200);
		res.header("Access-Control-Allow-Origin", "*");
		res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
		res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
		return res.build();
	}

	
	/*
	 * This service is used to get the details of an appointment
	 */
	@Path("/getAppointment")
	@GET
	@Produces("application/json")
	public Response getAppointment(@Context HttpHeaders headers) {
		
		String appointmentId = headers.getRequestHeader(ServiceConstants.APPOINTMENT_ID_HEADER).get(0);
		
		AppointmentDTO appointmentDetails = new AppointmentDTO();
		appointmentDetails.setAppointmentId(Integer.parseInt(appointmentId));
		
		DBConnectorService dbs = new DBConnectorService();
		try {
			appointmentDetails = dbs.getAppointment(appointmentDetails);
			JSONObject result = new JSONObject(appointmentDetails);
			ResponseBuilder res = Response.status(200).entity(result.toString());
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
	
	@Path("/updateAppointment")
	@OPTIONS
	@Produces("application/json")
	public Response returnHeaders5(@Context HttpHeaders headers) {
		ResponseBuilder res = Response.status(200);
		res.header("Access-Control-Allow-Origin", "*");
		res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
		res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
		return res.build();
	}

	
	/*
	 * This service is used to update an appointment
	 */
	@Path("/updateAppointment")
	@GET
	@Produces("application/json")
	public Response updateAppointment(@Context HttpHeaders headers) {
		
		int appointmentId = Integer.parseInt(headers.getRequestHeader(ServiceConstants.APPOINTMENT_ID_HEADER).get(0));
		String appointmentDate = headers.getRequestHeader("appointmentDate").get(0);
		String appointmentSlot = headers.getRequestHeader("slot").get(0);
		String doctorRemarks = headers.getRequestHeader("doctorRemarks").get(0);
		
		
		
		AppointmentDTO appointmentDetails = new AppointmentDTO();
		appointmentDetails.setAppointmentId(appointmentId);
		appointmentDetails.setAppointmentDate(appointmentDate);
		appointmentDetails.setDoctorRemarks(doctorRemarks);
		appointmentDetails.setAppointmentSlot(appointmentSlot);
		
		
		DBConnectorService dbs = new DBConnectorService();
		try {
			AppointmentDTO oldAppointment = new AppointmentDTO();
			oldAppointment.setAppointmentId(appointmentId);
			oldAppointment = dbs.getAppointment(oldAppointment);
			dbs.updateAppointment(appointmentDetails);
			SendMailTLS mailer = new SendMailTLS();
			PatientDTO  patientDetails = new PatientDTO();
			patientDetails.setPatientId(oldAppointment.getPatientUserName());
			patientDetails = dbs.getPatientDetails(patientDetails);
			String subject = "Appointment dated " + oldAppointment.getAppointmentDate() + " " + oldAppointment.getAppointmentSlot() + " has been updated";
			String message = " Dear " + patientDetails.getUsername() + ", \n\n " + subject  + "\n Please login into site for more info. \n\n " + "Regards,\n MedPlus\n";
			mailer.mail(patientDetails.getEmail(), patientDetails.getUsername(), subject, message);
			ResponseBuilder res = Response.status(200).entity("Updated successfully");
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
	
	@Path("/addDonation")
	@OPTIONS
	@Produces("application/json")
	public Response returnHeaders6(@Context HttpHeaders headers) {
		ResponseBuilder res = Response.status(200);
		res.header("Access-Control-Allow-Origin", "*");
		res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
		res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
		return res.build();
	}

	/*
	 * This service is used to add a Organ Donation
	 */
	@Path("/addDonation")
	@GET
	@Produces("application/json")
	public Response addDonation(@Context HttpHeaders headers) {
		
		String organName = headers.getRequestHeader(ServiceConstants.ORGAN_NAME_HEADER).get(0);
		String organDonor = headers.getRequestHeader(ServiceConstants.ORGAN_DONOR_NAME_HEADER).get(0);
		String mobile = headers.getRequestHeader(ServiceConstants.ORGAN_MOBILE_HEADER).get(0);
		String email = headers.getRequestHeader(ServiceConstants.ORGAN_EMAIL_HEADER).get(0);
		String address = headers.getRequestHeader(ServiceConstants.ORGAN_ADDRESS_HEADER).get(0);
		
		
		OrganDonorDTO organDonationDetails = new OrganDonorDTO();
		organDonationDetails.setAddress(address);
		organDonationDetails.setEmail(email);
		organDonationDetails.setOrganDonater(organDonor);
		organDonationDetails.setMobile(mobile);
		organDonationDetails.setOrganName(organName);
		
		
		DBConnectorService dbs = new DBConnectorService();
		try {
			dbs.insertOrgan(organDonationDetails);
			ResponseBuilder res = Response.status(200).entity("Added successfully");
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
	
	@Path("/getAllDistinctDonations")
	@OPTIONS
	@Produces("application/json")
	public Response returnHeaders7(@Context HttpHeaders headers) {
		ResponseBuilder res = Response.status(200);
		res.header("Access-Control-Allow-Origin", "*");
		res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
		res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
		return res.build();
	}

	
	/*
	 * This service is used to get all distinct donations
	 */
	@Path("/getAllDistinctDonations")
	@GET
	@Produces("application/json")
	public Response getAllDistinctDonations(@Context HttpHeaders headers) {
		
		
		DBConnectorService dbs = new DBConnectorService();
		
		try {
			List<String> organs = dbs.getAllOrgans();
			ResponseBuilder res = Response.status(200).entity(StringUtils.join(organs, ","));
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
	
	@Path("/mailDonorDetails")
	@OPTIONS
	@Produces("application/json")
	public Response returnHeaders8(@Context HttpHeaders headers) {
		ResponseBuilder res = Response.status(200);
		res.header("Access-Control-Allow-Origin", "*");
		res.header("Access-Control-Allow-Headers", ServiceConstants.CORS_HEADERS);
		res.header("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
		return res.build();
	}

	
	/*
	 * This service is used to mail the organ donation details
	 */
	@Path("/mailDonorDetails")
	@GET
	@Produces("application/json")
	public Response mailDonorDetails(@Context HttpHeaders headers) {
		
		String organName = headers.getRequestHeader(ServiceConstants.ORGAN_NAME_HEADER).get(0);
		String email= headers.getRequestHeader(ServiceConstants.EMAIL_HEADER).get(0);
		
		OrganDonorDTO organDetails = new OrganDonorDTO();
		organDetails.setOrganName(organName);
		
		
		DBConnectorService dbs = new DBConnectorService();
		
		try {
			List<OrganDonorDTO> organDonorsList = dbs.getOrganDetails(organDetails);
			String message = "Registered donors for " + organName + ":\n\n";
			String subject = "Organ donors list for " + organName;
			for(int i = 0;i < organDonorsList.size();i++) {
				OrganDonorDTO temp = organDonorsList.get(i);
				message += "Donor " + (i + 1) + ": \n";
				message += temp.getOrganDonater() + ",\n";
				message += temp.getMobile() + ",\n";
				message += temp.getEmail() + ",\n";
				message += temp.getAddress() + "\n\n";
			}
			message += "Regards,\nMedplus";
			SendMailTLS mailer = new SendMailTLS();
			mailer.mail(email, "", subject, message);
			ResponseBuilder res = Response.status(200).entity("Mailed successfully");
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
