package Servlets;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class MedicalHistoryServlet
 */
@WebServlet("/MedicalHistoryServlet")
public class MedicalHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MedicalHistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
		
		AppointmentDTO appointmentDetails = new AppointmentDTO();
		appointmentDetails.setAppointmentId(appointmentId);
		
		try {
			DBConnectorService dbs = new DBConnectorService();
			DoctorDTO doc = new DoctorDTO();
			appointmentDetails = dbs.getAppointment(appointmentDetails);
			doc.setDoctorId(appointmentDetails.getDoctorUserName());
			doc = dbs.getDoctorDetails(doc);
			request.setAttribute("email", doc.getEmail());
			request.setAttribute("username", doc.getUsername());
			PatientDTO patientDetails = new PatientDTO();
			patientDetails.setPatientId(appointmentDetails.getPatientUserName());
			patientDetails = dbs.getPatientDetails(patientDetails);
			List<AppointmentDTO> appointments = dbs.getAppointmentsOfPatient(patientDetails);
			List<AppointmentDTO> appointmentsNew = new ArrayList<>();
			for(int i = 0;i < appointments.size();i++)
			{
				AppointmentDTO temp = appointments.get(i);
				
				DoctorDTO t = new DoctorDTO();
				t.setDoctorId(temp.getDoctorUserName());
				t = dbs.getDoctorDetails(t);
				temp.setDoctorName(t.getUsername());
				if (temp.getDoctorRemarks() == null || temp.getDoctorRemarks().isEmpty())
					continue;
				appointmentsNew.add(temp);
			}
			request.setAttribute("appointments", appointmentsNew);
			request.getRequestDispatcher("MedicalHistory.jsp").forward(request, response);
			
		} catch (Exception e) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
