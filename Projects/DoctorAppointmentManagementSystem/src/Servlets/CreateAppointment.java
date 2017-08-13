package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constants.ServiceConstants;
import DTO.PatientDTO;
import DatabaseConnector.DBConnectorService;

/**
 * Servlet implementation class CreateAppointment
 */
@WebServlet("/CreateAppointment")
public class CreateAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAppointment() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * This servlet handles the case of creating appointment of patient
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String patientId = request.getParameter(ServiceConstants.PATIENT_ID_HEADER);
		request.setAttribute("patientId", patientId);
		DBConnectorService dbs = new DBConnectorService();
		PatientDTO patientDTO = new PatientDTO();
		
		patientDTO.setPatientId(Integer.parseInt(patientId));
		patientDTO = dbs.getPatientDetails(patientDTO);
		request.setAttribute("email", patientDTO.getEmail());
		request.setAttribute("username", patientDTO.getUsername());
		request.getRequestDispatcher("CreateAppointment.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
