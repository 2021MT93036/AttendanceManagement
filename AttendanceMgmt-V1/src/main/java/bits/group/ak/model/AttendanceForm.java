package bits.group.ak.model;

public class AttendanceForm {
	private int studentId;
	private int courseId;
	private String studentName;
	private int semesterId;
	private int recordedAttendance;
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getSemesterId() {
		return semesterId;
	}
	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}
	public int getRecordedAttendance() {
		return recordedAttendance;
	}
	public void setRecordedAttendance(int recordedAttendance) {
		this.recordedAttendance = recordedAttendance;
	}
	
	
}
