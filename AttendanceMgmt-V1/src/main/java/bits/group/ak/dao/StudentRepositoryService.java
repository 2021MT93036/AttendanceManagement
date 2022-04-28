package bits.group.ak.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import bits.group.ak.model.AttendanceForm;
import bits.group.ak.model.AttendanceInfo;
import bits.group.ak.model.CourseInfo;
import bits.group.ak.model.FacultyInfo;
import bits.group.ak.model.StudentInfo;

@Stateless
public class StudentRepositoryService {

	private Connection getConnection() throws SQLException {
		Connection f=DriverManager.getConnection("jdbc:mysql://localhost:3306/attendancemanagement","root","admin#123");
		return f;
	}
	
	public List<StudentInfo> findAll() {
		String query="select StudentId, FName, LName, DegreeName, SpecName, BatchYear , CurrentSemester from student s join degree d on s.DegreeId=d.DegreeId join specialization sp on s.SpecId=sp.SpecId";
		List<StudentInfo> slist=new ArrayList<>();
		try(Connection con=getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);	
				){
			while(rs.next()) {
				StudentInfo s=new StudentInfo();
				s.setId(rs.getString(1));
				s.setFirstName(rs.getString(2));
				s.setLastName(rs.getString(3));
			s.setDegreeName(rs.getString(4));
				s.setSpecName(rs.getString(5));
				s.setBatchYear(Year.of(rs.getInt(6)));
				s.setCurrentSemester(rs.getInt(7));
				slist.add(s);
			}
			
		}catch(SQLException ex) {
				ex.printStackTrace();
		}
		return slist;
	}
	
	public List<AttendanceInfo> findAttendanceByStudentId(int id) {
		String query="select StudentId, CourseName, PercAttend * 100 from (select Attend.StudentId as StudentId, Attend.CourseId as CourseId, avg(Attend.RecordedAttendance) as PercAttend from attendancemanagement.attendance as Attend group by 1,2) as Cumulative  join course c on Cumulative.CourseId = c.CourseId where Cumulative.StudentId =?";
		List<AttendanceInfo> alist=new ArrayList<>();
		try(Connection con=getConnection();
			PreparedStatement st=con.prepareStatement(query)){
			st.setInt(1, id);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				AttendanceInfo s=new AttendanceInfo();
				s.setId(rs.getInt(1));
				s.setCourseName(rs.getString(2));
				s.setPerAttendance(rs.getInt(3));
				alist.add(s);
			}
			
		}catch(SQLException ex) {
				ex.printStackTrace();
		}
		return alist;
	}
	
	public StudentInfo findById(int id) {
		String query="select StudentId, FName, LName, DegreeName, SpecName, BatchYear , CurrentSemester from student s join degree d on s.DegreeId=d.DegreeId join specialization sp on s.SpecId=sp.SpecId where StudentId ="+id;
		StudentInfo s=new StudentInfo();
		try(Connection con=getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);	
				){
			if(rs.next()) {
				s.setId(rs.getString(1));
				s.setFirstName(rs.getString(2));
				s.setLastName(rs.getString(3));
				s.setDegreeName(rs.getString(4));
				s.setSpecName(rs.getString(5));
				s.setBatchYear(Year.of(rs.getInt(6)));
				s.setCurrentSemester(rs.getInt(7));
				
			}
			
		}catch(SQLException ex) {
				ex.printStackTrace();
		}
		return s;
	}
	public List<CourseInfo> findCourseByFacultyId(int id,int year, int sem) {
		String query="select Taught_by.CourseId as CourseId, Course.CourseName from attendancemanagement.taught_by as Taught_by join attendancemanagement.course as Course where Course.CourseId = Taught_by.CourseId and taught_by.Semester = ? and taught_by.BatchYear = ? and taught_by.FacultyId = ?";
		List<CourseInfo> clist=new ArrayList<>();
		try(Connection con=getConnection();
			PreparedStatement st=con.prepareStatement(query)){
			st.setInt(1, sem);
			st.setInt(2, year);
			st.setInt(3, id);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				CourseInfo s=new CourseInfo();
				s.setId(rs.getInt(1));
				s.setCourseName(rs.getString(2));
				clist.add(s);
			}
			
		}catch(SQLException ex) {
				ex.printStackTrace();
		}
		return clist;
	}
	public FacultyInfo findByFacultyId(int id) {
		String query="SELECT * FROM attendancemanagement.faculty where FacultyId ="+id;
		FacultyInfo s=new FacultyInfo();
		try(Connection con=getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);	
				){
			if(rs.next()) {
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2)+ " " + rs.getString(3));
				s.setEmail(rs.getString(4));
			}
			
		}catch(SQLException ex) {
				ex.printStackTrace();
		}
		return s;
	}
	
	public List<AttendanceForm> findStudentsByCourseIdAndSemester(int cid, int sid){
		String query="select Student.StudentId as StudentId, CONCAT(Student.Fname, ' ', Student.Lname) as Name from attendancemanagement.courseware as Courseware join attendancemanagement.Student as Student where Student.SpecId = Courseware.SpecId and Courseware.CourseId = ? and Semester = ?";
		List<AttendanceForm> clist=new ArrayList<>();
		try(Connection con=getConnection();
			PreparedStatement st=con.prepareStatement(query)){
			st.setInt(1, cid);
			st.setInt(2, sid);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				AttendanceForm s=new AttendanceForm();
				s.setStudentId(rs.getInt(1));
				s.setCourseId(cid);
				s.setSemesterId(sid);
				s.setStudentName(rs.getString(2));
				s.setRecordedAttendance(0);
				clist.add(s);
			}
			
		}catch(SQLException ex) {
				ex.printStackTrace();
		}
		return clist;
	}
	public boolean updateAttendance(String[] cids, String[] sids, String[] satts) {
		String query="INSERT INTO attendancemanagement.attendance VALUES(?, ?, ?, ?)";
		try(Connection con=getConnection();
				PreparedStatement st=con.prepareStatement(query)){
		for(int i=0; i<cids.length; i++) {
			st.setInt(1,Integer.parseInt(cids[i]));
			st.setInt(2,Integer.parseInt(sids[i]));
			LocalDateTime now=LocalDateTime.now();
			DateTimeFormatter f=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String s=now.format(f);
			st.setString(3, s);
			st.setInt(4, Integer.parseInt(satts[i]));
			//System.out.println(st.toString());
			int n=st.executeUpdate();
			
		}
		}catch(SQLException ex) {
			ex.printStackTrace();
	   }
		return true;
	}
}
