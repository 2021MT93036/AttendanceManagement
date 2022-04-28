package bits.group.ak.model;

import java.time.Year;

public class StudentInfo {
		private String id;
		private String firstName;
		private String lastName;
		private String degreeName;
		private String specName;
		private Year batchYear;
		private int currentSemester;
		
		
		public StudentInfo() {
			super();
		}
		public StudentInfo(String id, String firstName, String lastName, String degreeName, String specName,
				Year batchYear, int currentSemester) {
			super();
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.degreeName = degreeName;
			this.specName = specName;
			this.batchYear = batchYear;
			this.currentSemester = currentSemester;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getDegreeName() {
			return degreeName;
		}
		public void setDegreeName(String degreeName) {
			this.degreeName = degreeName;
		}
		public String getSpecName() {
			return specName;
		}
		public void setSpecName(String specName) {
			this.specName = specName;
		}
		public Year getBatchYear() {
			return batchYear;
		}
		public void setBatchYear(Year batchYear) {
			this.batchYear = batchYear;
		}
		public int getCurrentSemester() {
			return currentSemester;
		}
		public void setCurrentSemester(int currentSemester) {
			this.currentSemester = currentSemester;
		}
		@Override
		public String toString() {
			return "StudentInfo [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", degreeName="
					+ degreeName + ", specName=" + specName + ", batchYear=" + batchYear + ", currentSemester="
					+ currentSemester + "]";
		}
		
		

}
