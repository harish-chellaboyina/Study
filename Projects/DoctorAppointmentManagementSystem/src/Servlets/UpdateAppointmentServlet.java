package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.AppointmentDTO;
import DTO.DoctorDTO;
import DatabaseConnector.DBConnectorService;

/**
 * Servlet implementation class UpdateAppointmentServlet
 */
@WebServlet("/UpdateAppointmentServlet")
public class UpdateAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAppointmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
		
		try {
			AppointmentDTO appointmentDetails = new AppointmentDTO();
			appointmentDetails.setAppointmentId(appointmentId);
			
			DBConnectorService dbs = new DBConnectorService();
			
			appointmentDetails = dbs.getAppointment(appointmentDetails);
			
			DoctorDTO doc = new DoctorDTO();
			doc.setDoctorId(appointmentDetails.getDoctorUserName());
			doc = dbs.getDoctorDetails(doc);
			request.setAttribute("appointment", appointmentDetails);
			request.setAttribute("email", doc.getEmail());
			request.setAttribute("username", doc.getUsername());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("UpdateAppointment.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
