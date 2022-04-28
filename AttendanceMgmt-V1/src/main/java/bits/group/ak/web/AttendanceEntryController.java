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

import org.apache.xml.resolver.apps.resolver;

import bits.group.ak.dao.StudentRepositoryService;
import bits.group.ak.model.AttendanceForm;
import bits.group.ak.model.AttendanceInfo;
import bits.group.ak.model.StudentInfo;


@WebServlet("/aentrymgmt")
public class AttendanceEntryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB
    private StudentRepositoryService srep;
   
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		int sid=Integer.parseInt(request.getParameter("sid"));
	//	HttpSession session=request.getSession();
		List<AttendanceForm> af=srep.findStudentsByCourseIdAndSemester(id, sid);
		StudentInfo st = srep.findById(id);
		request.setAttribute("stlist", af);
		request.getRequestDispatcher("courseDetails.jsp").forward(request, response);
			//sf.forEach(s->System.out.println(s));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String[] sids=request.getParameterValues("sid");
				String[] cids=request.getParameterValues("cid");
				String[] satts=request.getParameterValues("satt");
//				for(String s:satts) {
//					System.out.println(s);
//				}
				
				srep.updateAttendance(cids, sids, satts);
				response.sendRedirect("index.htm");
		
	}

}
