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
import bits.group.ak.model.CourseInfo;
import bits.group.ak.model.FacultyInfo;
import bits.group.ak.model.StudentInfo;


@WebServlet("/cmgmt")
public class CourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB
    private StudentRepositoryService srep;
   
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("fid"));
		int year=Integer.parseInt(request.getParameter("yrs"));
		int sid=Integer.parseInt(request.getParameter("sid"));
		HttpSession session=request.getSession();
		FacultyInfo f=srep.findByFacultyId(id);
		List<CourseInfo> af=srep.findCourseByFacultyId(id, year, sid);
		session.setAttribute("year", year);
		session.setAttribute("sem", sid);
		session.setAttribute("faculty", f);
		session.setAttribute("clist", af);
		//request.setAttribute("student", st);
		request.getRequestDispatcher("courseDetails.jsp").forward(request, response);
			//sf.forEach(s->System.out.println(s));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
