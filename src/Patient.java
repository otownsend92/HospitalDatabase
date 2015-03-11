import java.util.Date;

public class Patient {
	private String patientid;
	private String guardianNo;
	private String givenname;
	private String familyname;
	private String suffix;
	private String gender;
	private String birthtime;
	private String providerId;
	private String xmlCreationdate;

	public Patient(String patientId2, String guardianNo, String firstName, String givenname,
			String familyname, String suffix, String gender, String birthtime,
			String providerid, String xmlCreationDate) {
		this.setPatientid(patientId2);
		this.setPatientrole(guardianNo);
		this.setGivenname(givenname);
		this.setFamilyname(familyname);
		this.setSuffix(suffix);
		this.setGender(gender);
		this.setBirthtime(birthtime);
		this.setProviderId(providerid);
		this.setXmlCreationdate(xmlCreationDate);
	}

	public String getPatientrole() {
		return guardianNo;
	}

	public void setPatientrole(String guardianNo) {
		this.guardianNo = guardianNo;
	}

	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}

	public String getGivenname() {
		return givenname;
	}

	public void setGivenname(String givenname) {
		this.givenname = givenname;
	}

	public String getFamilyname() {
		return familyname;
	}

	public void setFamilyname(String familyname) {
		this.familyname = familyname;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthtime() {
		return birthtime;
	}

	public void setBirthtime(String birthtime) {
		this.birthtime = birthtime;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getXmlCreationdate() {
		return xmlCreationdate;
	}

	public void setXmlCreationdate(String xmlCreationdate) {
		this.xmlCreationdate = xmlCreationdate;
	}
}
