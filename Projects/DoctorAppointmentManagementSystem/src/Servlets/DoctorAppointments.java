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
 * Servlet implementation class DoctorAppointments
 */
@WebServlet("/DoctorAppointments")
public class DoctorAppointments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorAppointments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		DBConnectorService dbs = new DBConnectorService();
		DoctorDTO doc = new DoctorDTO();
		doc.setEmail(email);
		doc = dbs.getDoctorDetailsWithEmail(doc);
		request.setAttribute("doctorId", doc.getDoctorId());
		request.setAttribute("username", doc.getUsername());
		
		List<AppointmentDTO> appointments = dbs.getAppointmentsOfDoctor(doc);
		
		for(int i = 0;i < appointments.size();i++)
		{
			AppointmentDTO temp = appointments.get(i);
			PatientDTO t = new PatientDTO();
			t.setPatientId(temp.getPatientUserName());
			t = dbs.getPatientDetails(t);
			temp.setPatientName(t.getUsername());
		}
		request.setAttribute("appointments", appointments);
		request.getRequestDispatcher("DoctorAppointments.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
