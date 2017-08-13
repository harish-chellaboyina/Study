package Constants;

/*
 * This class contains the sql constants
 */
public class SQLConstants {
	
	public static final String PATIENT_ID_COLUMN = "patientId";
	public static final String PATIENT_USERNAME_COLUMN = "username";
	public static final String PATIENT_PASSWORD_COLUMN = "password";
	public static final String PATIENT_EMAIL_COLUMN = "email";
	
	
	public static final String DOCTOR_ID_COLUMN = "doctorId";
	public static final String DOCTOR_USERNAME_COLUMN = "username";
	public static final String DOCTOR_PASSWORD_COLUMN = "password";
	public static final String DOCTOR_EMAIL_COLUMN = "email";
	public static final String DOCTOR_SPECIALIZATION_COLUMN = "specialization";
	

	public static final String APPOINTMENT_ID_COLUMN = "appointmentId";
	public static final String APPOINTMENT_PATIENT_ID_COLUMN = "patientId";
	public static final String APPOINTMENT_DOCTOR_ID_COLUMN = "doctorId";
	public static final String APPOINTMENT_DATE_COLUMN = "appointmentDate";
	public static final String APPOINTMENT_PROBLEM_DISCRIPTION_COLUMN = "problem_description";
	public static final String APPOINTMENT_PROBLEM_RESOLUTION_COLUMN = "problem_resolution";
	public static final String APPOINTMENT_SLOT_COLUMN = "appointmentSlot";
	
	public static final String ORGAN_ID_COLUMN = "organId";
	public static final String ORGAN_NAME_COLUMN = "organName";
	public static final String ORGAN_DONOR_NAME_COLUMN = "organDonater";
	public static final String ORGAN_EMAIL_COLUMN = "email";
	public static final String ORGAN_MOBILE_COLUMN = "mobile";
	public static final String ORGAN_ADDRESS_COLUMN = "address";
	
	
	public static final String INSERT_PATIENT = "INSERT into patients(username, password, email) values(?,?,?)";
	public static final String INSERT_DOCTOR = "INSERT into doctors(username, password, email, specialization) values(?,?,?,?)";
	public static final String INSERT_APPOINTMENT = "INSERT into appointments(patientId, doctorId, appointmentDate, problem_description, problem_resolution, appointmentSlot) values(?,?,?,?,?,?)";
	public static final String GET_PATIENT = "SELECT * from patients WHERE patientId=?";
	public static final String GET_PATIENT_WITH_EMAIL = "SELECT * from patients WHERE email=?";
	public static final String GET_DOCTOR = "SELECT * from doctors WHERE doctorId=?";
	public static final String GET_DOCTOR_WITH_EMAIL = "SELECT * from doctors WHERE email=?";
	public static final String GET_APPOINTMENTS_OF_PATIENT = "SELECT * from appointments WHERE patientId in (SELECT patientId from patients where email=?)";
	public static final String GET_APPOINTMENTS_OF_DOCTOR = "SELECT * from appointments WHERE doctorId in (SELECT doctorId from doctors where email=?)";
	public static final String IS_PATIENT_ID_EXISTS = "SELECT COUNT(*) from patients WHERE email=?";
	public static final String IS_DOCTOR_ID_EXISTS = "SELECT COUNT(*) from doctors WHERE email=?";
	public static final String GET_ALL_PATIENTS = "SELECT * from patients";
	public static final String GET_ALL_DOCTORS = "SELECT * from doctors";
	public static final String GET_ALL_DISTINCT_SPECIALIZATIONS = "SELECT DISTINCT specialization FROM doctors";
	public static final String GET_ALL_DOCTORS_FOR_SPECIALIZATION = "SELECT username, doctorId from doctors where specialization=?";
	public static final String GET_COUNT_FOR_SLOTS = "select COUNT(*) from appointments where doctorId=? and appointmentSlot=? and appointmentDate=?"; 
	public static final String DELETE_APPOINTMENT = "delete from appointments where appointmentId=?";
	public static final String GET_APPOINTMENT = "select * from appointments where appointmentId=?";
	public static final String UPDATE_APPOINTMENT = "UPDATE appointments SET appointmentDate=?, appointmentSlot=?, problem_resolution=? where appointmentId=?";
	public static final String INSERT_ORGAN_DONOR_ENTRY = "INSERT into organs(organName, organDonater, mobile, email, address) values(?,?,?,?,?)";
	public static final String GET_ORGANS = "SELECT DISTINCT organName from organs";
	public static final String GET_ORGANS_INFO = "SELECT * from organs where organName=?";
	
	
	
	
}
