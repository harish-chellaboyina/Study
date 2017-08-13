package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.AppointmentDTO;
import DTO.DoctorDTO;
import DTO.PatientDTO;
import DatabaseConnector.DBConnectorService;

/**
 * Servlet implementation class PatientAppointments
 */
@WebServlet("/PatientAppointments")
public class PatientAppointments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientAppointments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setEmail(email);
		
		
		DBConnectorService dbs = new DBConnectorService();
		patientDTO = dbs.getPatientDetailsWithEmail(patientDTO);
		request.setAttribute("username", patientDTO.getUsername());
		request.setAttribute("patientId", patientDTO.getPatientId());
		
		List<AppointmentDTO> appointments = dbs.getAppointmentsOfPatient(patientDTO);
		for(int i = 0;i < appointments.size();i++)
		{
			AppointmentDTO temp = appointments.get(i);
			DoctorDTO t = new DoctorDTO();
			t.setDoctorId(temp.getDoctorUserName());
			t = dbs.getDoctorDetails(t);
			temp.setDoctorName(t.getUsername());
		}
		request.setAttribute("appointments", appointments);
		
		request.getRequestDispatcher("PatientAppointments.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
