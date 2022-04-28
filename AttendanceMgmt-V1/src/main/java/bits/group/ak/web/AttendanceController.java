package bits.group.ak.web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bits.group.ak.dao.StudentRepositoryService;
import bits.group.ak.model.AttendanceInfo;
import bits.group.ak.model.StudentInfo;


@WebServlet("/amgmt")
public class AttendanceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB
    private StudentRepositoryService srep;
   
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));	
		List<AttendanceInfo> af=srep.findAttendanceByStudentId(id);
		StudentInfo st = srep.findById(id);
		request.setAttribute("alist", af);
		request.setAttribute("student", st);
		request.getRequestDispatcher("attendanceDetails.jsp").forward(request, response);
			//sf.forEach(s->System.out.println(s));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
