package Constants;


/*
 * This class contains the header constants
 */
public class ServiceConstants {
	public static String APPOINTMENT_STATUS_IN_PROGRESS = "INPROGRESS";
	public static String APPOINTMENT_STATUS_COMPLETED = "COMPLETED";
	
	//Header Constants
	public static final String USERNAME_HEADER = "username";
	public static final String PASSWORD_HEADER = "password";
	public static final String PATIENT_ID_HEADER = "patientId";
	public static final String DOCTOR_ID_HEADER = "doctorId";
	public static final String APPOINTMENT_ID_HEADER = "appointmentId";
	public static final String EMAIL_HEADER = "email";
	public static final String SPECIALIZATION_HEADER = "specialization";
	public static final String DATE_HEADER = "appointmentDate";
	public static final String SLOT_HEADER = "slot";
	public static final String PROBLEM_DESCRIPTION_HEADER = "problemDescription";
	public static final String ORGAN_ID_HEADER = "organId";
	public static final String ORGAN_NAME_HEADER = "organName";
	public static final String ORGAN_DONOR_NAME_HEADER = "organDonater";
	public static final String ORGAN_EMAIL_HEADER = "email";
	public static final String ORGAN_MOBILE_HEADER = "mobile";
	public static final String ORGAN_ADDRESS_HEADER = "address";
	
	//Responses
	public static final String AUTH_SUCCESS_REPONSE = "Authentication Success";
	public static final String AUTH_FAILURE_REPONSE = "Authentication Failed";
	
	public static final String CORS_HEADERS = "organName, organId, organDonater, mobile, address, username, password, patientId, doctorId, email, specialization, appointmentId, specialization, appointmentDate, slot, problemDescription, doctorRemarks";
	
}
