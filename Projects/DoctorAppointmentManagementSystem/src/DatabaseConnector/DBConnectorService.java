package DatabaseConnector;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.mysql.jdbc.Statement;

import Constants.ExceptionConstants;
import Constants.SQLConstants;
import DTO.AppointmentDTO;
import DTO.DoctorDTO;
import DTO.OrganDonorDTO;
import DTO.PatientDTO;
import ProjectUtils.Utils;
import exception.CustomException;

public class DBConnectorService {
	
	/*
	 * This method takes the PatientDTO and creates the record in the Patient table
	 * The method first checks whether the email of the patient already exists in the table.
	 * If it exists, it throws an exception with message 'Email already exists'
	 * It won't return anything on successful record creation
	 */
	public int registerPatient(PatientDTO patientDetails) throws Exception {
		if (this.isAlreadyExistsForPatient(patientDetails))
			throw new Exception(ExceptionConstants.EMAIL_ALREADY_EXISTS);
		
		Connection dataBaseConnection = Utils.getDBConnection(); 
		PreparedStatement ps;
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.INSERT_PATIENT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, patientDetails.getUsername());
			ps.setString(2, patientDetails.getPassword());
			ps.setString(3, patientDetails.getEmail());
			
			int affectedRows = ps.executeUpdate();
			
			
			if (affectedRows == 0) {
	            throw new SQLException("Creating patient failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                return generatedKeys.getInt(1);
	            }
	            else {
	                throw new SQLException("Creating patient failed, no ID obtained.");
	            }
	        }
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	
	/*
	 * This method takes the DoctorDTO and creates a record in the Doctors table
	 * The method first checks whether the email of doctor already exists in the table
	 * If it exists, it throws an exception with message 'Email already exists'
	 * It won't return anything on successful record creation
	 */
	public int registerDoctor(DoctorDTO doctorDetails) throws Exception {
		if (this.isAlreadyExistsForDoctor(doctorDetails))
			throw new CustomException(ExceptionConstants.EMAIL_ALREADY_EXISTS);
		
		Connection dataBaseConnection = Utils.getDBConnection(); 
		PreparedStatement ps;
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.INSERT_DOCTOR, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, doctorDetails.getUsername());
			ps.setString(2, doctorDetails.getPassword());
			ps.setString(3, doctorDetails.getEmail());
			ps.setString(4, doctorDetails.getSpecialization());
			int affectedRows = ps.executeUpdate();
			
			
			if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                return generatedKeys.getInt(1);
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	/*
	 * This method checks whether the doctor is free for the requested appointment
	 * It takes a parameter AppointmentDTO which contains the appointment details like slot and date
	 * It returns a boolean whether the doctor is free for the requested slot or not
	 */
	public boolean isDoctorFree(AppointmentDTO appointmentDetails) {
		Connection dataBaseConnection = Utils.getDBConnection(); 
		PreparedStatement ps;
		
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.GET_COUNT_FOR_SLOTS);
			ps.setInt(1, appointmentDetails.getDoctorUserName());
			ps.setString(2, appointmentDetails.getAppointmentSlot());
			ps.setString(3, appointmentDetails.getAppointmentDate());
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next() && rs.getInt(1) > 0) {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	
	/*
	 * This method is used to register an appointment
	 * It takes AppointmentDTO as parameter
	 * It internally checks whether the doctor is free for the requested slot
	 * It returns nothing on successful record creation
	 * Or it throws an error incase of any exception
	 */
	public void registerAppointment(AppointmentDTO appointmentDetails) throws Exception {
		Connection dataBaseConnection = Utils.getDBConnection(); 
		PreparedStatement ps;
		
		if (!this.isDoctorFree(appointmentDetails))
			throw new Exception("Slot is busy, Please Reschedule");
		
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.INSERT_APPOINTMENT);
			ps.setInt(1, appointmentDetails.getPatientUserName());
			ps.setInt(2, appointmentDetails.getDoctorUserName());
			ps.setString(3, appointmentDetails.getAppointmentDate());
			ps.setString(4, appointmentDetails.getPatientRemarks());
			ps.setString(5, appointmentDetails.getDoctorRemarks());
			ps.setString(6, appointmentDetails.getAppointmentSlot());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/*
	 * This method is used to get the details of the patient based on patient id
	 * It takes the parameter PatientDTO which contains the patient id
	 * It queries the database and returns the patient details
	 */
	public PatientDTO getPatientDetails(PatientDTO patientDTO) {
		Connection dataBaseConnection = Utils.getDBConnection(); 
		PreparedStatement ps;
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.GET_PATIENT);
			ps.setInt(1, patientDTO.getPatientId());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				patientDTO.setUsername(rs.getString(SQLConstants.PATIENT_USERNAME_COLUMN));
				patientDTO.setPassword(rs.getString(SQLConstants.PATIENT_PASSWORD_COLUMN));
				patientDTO.setEmail(rs.getString(SQLConstants.PATIENT_EMAIL_COLUMN));
				return patientDTO;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	 * This method is used to get the details of the patient based on the patient email
	 * It takes the parameter PatientDTO which contains the patient email
	 * It queries the patient table and returns the requested patient details
	 */
	public PatientDTO getPatientDetailsWithEmail(PatientDTO patientDTO) {
		Connection dataBaseConnection = Utils.getDBConnection(); 
		PreparedStatement ps;
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.GET_PATIENT_WITH_EMAIL);
			ps.setString(1, patientDTO.getEmail());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				patientDTO.setUsername(rs.getString(SQLConstants.PATIENT_USERNAME_COLUMN));
				patientDTO.setPassword(rs.getString(SQLConstants.PATIENT_PASSWORD_COLUMN));
				patientDTO.setPatientId(rs.getInt(SQLConstants.PATIENT_ID_COLUMN));
				return patientDTO;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	 * This method is used to get the details of the doctor based on the doctor id
	 * It takes the parameter DoctorDTO which contains the doctor id
	 * It queries the doctor table and returns the requested doctor details
	 */	
	public DoctorDTO getDoctorDetails(DoctorDTO doctorDTO) {
		Connection dataBaseConnection = Utils.getDBConnection(); 
		PreparedStatement ps;
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.GET_DOCTOR);
			ps.setInt(1, doctorDTO.getDoctorId());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				doctorDTO.setUsername(rs.getString(SQLConstants.DOCTOR_USERNAME_COLUMN));
				doctorDTO.setPassword(rs.getString(SQLConstants.DOCTOR_PASSWORD_COLUMN));
				doctorDTO.setEmail(rs.getString(SQLConstants.DOCTOR_EMAIL_COLUMN));
				doctorDTO.setSpecialization(rs.getString(SQLConstants.DOCTOR_SPECIALIZATION_COLUMN));
				return doctorDTO;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	 * This method is used to get the details of the doctor based on the doctor email
	 * It takes the parameter DoctorDTO which contains the doctor email
	 * It queries the doctor table and returns the requested doctor details
	 */	
	public DoctorDTO getDoctorDetailsWithEmail(DoctorDTO doctorDTO) {
		Connection dataBaseConnection = Utils.getDBConnection(); 
		PreparedStatement ps;
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.GET_DOCTOR_WITH_EMAIL);
			ps.setString(1, doctorDTO.getEmail());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				doctorDTO.setUsername(rs.getString(SQLConstants.DOCTOR_USERNAME_COLUMN));
				doctorDTO.setPassword(rs.getString(SQLConstants.DOCTOR_PASSWORD_COLUMN));
				doctorDTO.setDoctorId(rs.getInt(SQLConstants.DOCTOR_ID_COLUMN));
				doctorDTO.setSpecialization(rs.getString(SQLConstants.DOCTOR_SPECIALIZATION_COLUMN));
				return doctorDTO;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/*
	 * This method is used to get the appointments of the patient based on the patient email
	 * It takes the parameter PatientDTO which contains the patient email
	 * It queries the appointments table and returns the requested patient appointments
	 */	
	public List<AppointmentDTO> getAppointmentsOfPatient(PatientDTO patientDTO) {
		Connection dataBaseConnection = Utils.getDBConnection(); 
		PreparedStatement ps;
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.GET_APPOINTMENTS_OF_PATIENT);
			ps.setString(1, patientDTO.getEmail());
			ResultSet rs = ps.executeQuery();
			
			List<AppointmentDTO> appointments = new ArrayList<>();
			while (rs.next()) {
				AppointmentDTO a = new AppointmentDTO();
				a.setAppointmentDate(rs.getString(SQLConstants.APPOINTMENT_DATE_COLUMN));
				a.setAppointmentSlot(rs.getString(SQLConstants.APPOINTMENT_SLOT_COLUMN));
				a.setDoctorRemarks(rs.getString(SQLConstants.APPOINTMENT_PROBLEM_RESOLUTION_COLUMN));
				a.setPatientRemarks(rs.getString(SQLConstants.APPOINTMENT_PROBLEM_DISCRIPTION_COLUMN));
				a.setDoctorUserName(rs.getInt(SQLConstants.APPOINTMENT_DOCTOR_ID_COLUMN));
				a.setAppointmentId(rs.getInt(SQLConstants.APPOINTMENT_ID_COLUMN));
				appointments.add(a);
			}
			return appointments;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	 * This method is used to get the appointments of the doctor based on the doctor email
	 * It takes the parameter DoctorDTO which contains the doctor email
	 * It queries the appointments table and returns the requested doctor appointments
	 */	
	public List<AppointmentDTO> getAppointmentsOfDoctor(DoctorDTO doctorDTO) {
		Connection dataBaseConnection = Utils.getDBConnection(); 
		PreparedStatement ps;
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.GET_APPOINTMENTS_OF_DOCTOR);
			ps.setString(1, doctorDTO.getEmail());
			ResultSet rs = ps.executeQuery();
			
			List<AppointmentDTO> appointments = new ArrayList<>();
			while (rs.next()) {
				AppointmentDTO a = new AppointmentDTO();
				a.setAppointmentDate(rs.getString(SQLConstants.APPOINTMENT_DATE_COLUMN));
				a.setAppointmentSlot(rs.getString(SQLConstants.APPOINTMENT_SLOT_COLUMN));
				a.setDoctorRemarks(rs.getString(SQLConstants.APPOINTMENT_PROBLEM_RESOLUTION_COLUMN));
				a.setPatientRemarks(rs.getString(SQLConstants.APPOINTMENT_PROBLEM_DISCRIPTION_COLUMN));
				a.setPatientUserName(rs.getInt(SQLConstants.APPOINTMENT_PATIENT_ID_COLUMN));
				a.setDoctorUserName(rs.getInt(SQLConstants.APPOINTMENT_DOCTOR_ID_COLUMN));
				a.setAppointmentId(rs.getInt(SQLConstants.APPOINTMENT_ID_COLUMN));
				appointments.add(a);
			}
			return appointments;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	 * This method returns the list of patients who ever are registered as patients
	 * This method doesn't take any parameters and returns a list of PatientDTO
	 */
	public List<PatientDTO> getAllPatients() {
		Connection dataBaseConnection = Utils.getDBConnection(); 
		PreparedStatement ps;
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.GET_ALL_PATIENTS);
			ResultSet rs = ps.executeQuery();
			
			List<PatientDTO> patients = new ArrayList<>();
			while (rs.next()) {
				PatientDTO patientDTO = new PatientDTO();
				patientDTO.setUsername(rs.getString(SQLConstants.PATIENT_USERNAME_COLUMN));
				patientDTO.setEmail(rs.getString(SQLConstants.PATIENT_EMAIL_COLUMN));
				patients.add(patientDTO);
			}
			return patients;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	 * This method returns the list of doctors who ever are registered as doctors
	 * This method doesn't take any parameters and returns a list of DoctorDTO
	 */
	public List<DoctorDTO> getAllDoctors() {
		Connection dataBaseConnection = Utils.getDBConnection(); 
		PreparedStatement ps;
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.GET_ALL_DOCTORS);
			ResultSet rs = ps.executeQuery();
			
			List<DoctorDTO> doctors = new ArrayList<>();
			while (rs.next()) {
				DoctorDTO doctor = new DoctorDTO();
				doctor.setUsername(SQLConstants.DOCTOR_USERNAME_COLUMN);
				doctor.setEmail(rs.getString(SQLConstants.DOCTOR_EMAIL_COLUMN));
				doctor.setSpecialization(rs.getString(SQLConstants.DOCTOR_SPECIALIZATION_COLUMN));
				doctors.add(doctor);
			}
			return doctors;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	 * This method checks whether the patient email already exists in patients table
	 * This method is called while a new patient is getting registered
	 * It takes a PatientDTO as parameter which contains the patient email
	 * And returns a boolean of whether the patient email exists or not
	 */
	public boolean isAlreadyExistsForPatient(PatientDTO patientDetails) {
		Connection dataBaseConnection = Utils.getDBConnection();
		PreparedStatement ps;
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.IS_PATIENT_ID_EXISTS);
			ps.setString(1, patientDetails.getEmail());
			ResultSet rs = ps.executeQuery();
			if (rs.next() && rs.getInt(1) > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	/*
	 * This method checks whether the doctor email already exists in doctors table
	 * This method is called while a new doctor is getting registered
	 * It takes a DoctorDTO as parameter which contains the doctor email
	 * And returns a boolean of whether the doctor email exists or not
	 */
	public boolean isAlreadyExistsForDoctor(DoctorDTO doctorDetails) {
		Connection dataBaseConnection = Utils.getDBConnection();
		PreparedStatement ps;
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.IS_DOCTOR_ID_EXISTS);
			ps.setString(1, doctorDetails.getUsername());
			ResultSet rs = ps.executeQuery();
			if (rs.next() && rs.getInt(1) > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/*
	 * This method is used to get all the specializations of all the doctors available
	 * It doesn't take any input parameters
	 * It returns a list of Strings of Specializations
	 */
	public List<String> getAllSpecilizations() {
		Connection dataBaseConnection = Utils.getDBConnection(); 
		PreparedStatement ps;
		List<String> specializationsList = new ArrayList<>();
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.GET_ALL_DISTINCT_SPECIALIZATIONS);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				specializationsList.add(rs.getString(SQLConstants.DOCTOR_SPECIALIZATION_COLUMN));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return specializationsList;
	}
	
	
	/*
	 * This method is used to get the list of doctors for specific specialization
	 * This takes a String as parameter which is the specializations
	 * It returns a JSONObject which contains the list of doctor names and ids
	 */
	public JSONObject getDoctorsForSpecilization(String specialization) {
		Connection dataBaseConnection = Utils.getDBConnection(); 
		JSONObject result = new JSONObject();
		PreparedStatement ps;
		List<String> doctorsList = new ArrayList<>();
		List<String> doctorIdsList = new ArrayList<>();
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.GET_ALL_DOCTORS_FOR_SPECIALIZATION);
			ps.setString(1, specialization);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				doctorsList.add(rs.getString(SQLConstants.DOCTOR_USERNAME_COLUMN));
				doctorIdsList.add(rs.getString(SQLConstants.DOCTOR_ID_COLUMN));
			}
			result.append("doctornames", doctorsList);
			result.append("doctorIds", doctorIdsList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/*
	 * This method is used to delete an appointment
	 * This takes AppointmentDTO as parameter which should contain the appointment id
	 * It deletes if it finds a valid appointment with the provided id
	 * If not it throws the appropriate exception
	 * It doesn't return anything on successful deletion
	 */
	public void deleteAppointment(AppointmentDTO appointmentDetails) throws Exception {
		Connection dataBaseConnection = Utils.getDBConnection();
		PreparedStatement ps;
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.DELETE_APPOINTMENT);
			ps.setInt(1, appointmentDetails.getAppointmentId());
			ps.executeUpdate();
		} catch(Exception e) {
			throw e;
		}
	}
	
	
	/*
	 * This method is used to get the details of the appointment
	 * It takes the AppointmentDTO as parameter which contains the appointment id
	 * It returns the AppointmentDTO which contains the appointment details
	 * It throws appropriate exception on any unexpected error
	 */
	public AppointmentDTO getAppointment(AppointmentDTO appointmentDetails) throws Exception {
		Connection dataBaseConnection = Utils.getDBConnection();
		PreparedStatement ps;
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.GET_APPOINTMENT);
			ps.setInt(1, appointmentDetails.getAppointmentId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				appointmentDetails.setAppointmentDate(rs.getString(SQLConstants.APPOINTMENT_DATE_COLUMN));
				appointmentDetails.setAppointmentSlot(rs.getString(SQLConstants.APPOINTMENT_SLOT_COLUMN));
				appointmentDetails.setDoctorUserName(rs.getInt(SQLConstants.DOCTOR_ID_COLUMN));
				appointmentDetails.setPatientUserName(rs.getInt(SQLConstants.PATIENT_ID_COLUMN));
				appointmentDetails.setPatientRemarks(rs.getString(SQLConstants.APPOINTMENT_PROBLEM_DISCRIPTION_COLUMN));
				appointmentDetails.setDoctorRemarks(rs.getString(SQLConstants.APPOINTMENT_PROBLEM_RESOLUTION_COLUMN));
			}
			return appointmentDetails;

		} catch(Exception e) {
			throw e;
		}
		
	}
	
	
	/*
	 * This method is used to update the appointment
	 * It takes the AppointmentDTO as parameter which contains the details to be updated
	 * It returns nothing on successful update
	 * It throws an error  if the provided appointment details are invalid
	 */
	public void updateAppointment(AppointmentDTO appointmentDetails) throws Exception {
		Connection dataBaseConnection = Utils.getDBConnection();
		PreparedStatement ps;
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.UPDATE_APPOINTMENT);
			ps.setString(1, appointmentDetails.getAppointmentDate());
			ps.setString(2, appointmentDetails.getAppointmentSlot());
			ps.setString(3, appointmentDetails.getDoctorRemarks());
			ps.setInt(4, appointmentDetails.getAppointmentId());
			ps.executeUpdate();
		} catch(Exception e) {
			throw e;
		}
	}
	
	
	/*
	 * This method is used to insert an organ donation details into organ donations table
	 * This takes the OrganDTO as parameter which contains the organ donation details
	 * It returns nothing on successful insertion
	 * It throws an exception on invalid organ donation details
	 */
	public void insertOrgan(OrganDonorDTO organDonationDetails) throws Exception {
		Connection dataBaseConnection = Utils.getDBConnection();
		PreparedStatement ps;
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.INSERT_ORGAN_DONOR_ENTRY);
			ps.setString(1, organDonationDetails.getOrganName());
			ps.setString(2, organDonationDetails.getOrganDonater());
			ps.setString(3, organDonationDetails.getMobile());
			ps.setString(4, organDonationDetails.getEmail());
			ps.setString(5, organDonationDetails.getAddress());
			ps.executeUpdate();
		} catch(Exception e) {
			throw e;
		}
	}
	
	
	/*
	 * This method is used to get the list of organs available for donation
	 * It doesn't require any input parameters
	 * It returns a list of organ names
	 */
	public List<String> getAllOrgans() throws Exception{
		Connection dataBaseConnection = Utils.getDBConnection();
		PreparedStatement ps;
		List<String> organsList = new ArrayList<>();
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.GET_ORGANS);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) 
				organsList.add(rs.getString(SQLConstants.ORGAN_NAME_COLUMN));

			return organsList;
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	
	/*
	 * This method is used to get the available donations for an organ
	 * This takes OrganDonorDTO as input parameter
	 * It returns a list of OrganDTOs which will contain the available donations 
	 */
	public List<OrganDonorDTO> getOrganDetails(OrganDonorDTO organDonationDetails) throws Exception{
		Connection dataBaseConnection = Utils.getDBConnection();
		PreparedStatement ps;
		List<OrganDonorDTO> organDonorList = new ArrayList<>();
		try {
			ps = dataBaseConnection.prepareStatement(SQLConstants.GET_ORGANS_INFO);
			ps.setString(1, organDonationDetails.getOrganName());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				OrganDonorDTO organDonor = new OrganDonorDTO();
				organDonor.setOrganDonater(rs.getString(SQLConstants.ORGAN_DONOR_NAME_COLUMN));
				organDonor.setAddress(rs.getString(SQLConstants.ORGAN_ADDRESS_COLUMN));
				organDonor.setMobile(rs.getString(SQLConstants.ORGAN_MOBILE_COLUMN));
				organDonor.setEmail(rs.getString(SQLConstants.ORGAN_EMAIL_COLUMN));
				organDonorList.add(organDonor);
			}

			return organDonorList;
		}
		catch(Exception e) {
			throw e;
		}
	}
}
