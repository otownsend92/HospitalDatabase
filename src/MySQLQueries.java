/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Brian
 */
public class MySQLQueries {
    
    private static Statement st;
    private static ResultSet rs;

    
    public MySQLQueries()
    {
        st = null;
        rs = null;
    }
    public int patientView(String Table, String PID)
        throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException
    {
        st = null;
		st = xmlparse.hisCon.createStatement();
		String query = "CREATE OR REPLACE VIEW Patient_View" +
		            "    AS SELECT * " +
		            "    FROM " + Table +
		            "    WHERE " + Table + ".PatientID = " + PID;
		st.executeUpdate(query);
		String query2 = "SELECT * FROM Patient_View";
		rs = st.executeQuery(query2);
		if (Table.equals("Patient"))
		{
		    System.out.println("PatientID\t" +
		    		"GuardianNo\t" +
		    		"GivenName\t" +
		    		"FamilyName\t" +
		    		"Suffix\t" +
		    		"Gender\t" +
		    		"Birthtime\t" +
		    		"ProviderID\t" +
		    		"xmlHealthCreationDateTime");
		    System.out.println("--------------------------------------------");
		    while (rs.next()) {
		        String PatientID = rs.getString("PatientID");
		        String GuardianNo = rs.getString("GuardianNo");
		        String GivenName = rs.getString("GivenName");
		        String FamilyName = rs.getString("FamilyName");
		        String Suffix = rs.getString("Suffix");
		        String Gender = rs.getString("Gender");
		        String Birthtime = rs.getString("Birthtime");
		        String ProviderID = rs.getString("ProviderID");
		        String xmlHealthCreationDateTime = rs.getString("xmlHealthCreationDateTime");
		        System.out.println(PatientID +"\t"+ GuardianNo +"\t"+ 
		        		GivenName +"\t"+ FamilyName +"\t"+ Suffix +"\t"+ 
		        		Gender +"\t"+ Birthtime +"\t"+ ProviderID +"\t"+
		        		xmlHealthCreationDateTime);
		    }
		}
		else if (Table.equals("Has_Guardian"))
		{
		    System.out.println("PatientID\t" +
		    		"GuardianNo\t" +
		    		"FirstName\t" +
		    		"LastName\t" +
		    		"Relationship\t" +                		
		    		"Phone\t" +
		    		"Address\t" +
		    		"City\t" +
		    		"State\t" +
		    		"Zip");
		    System.out.println("--------------------------------------------");
		    while (rs.next()) {
		        String PatientID = rs.getString("PatientID");
		        String GuardianNo = rs.getString("GuardianNo");
		        String FirstName = rs.getString("FirstName");
		        String LastName = rs.getString("LastName");
		        String Relationship = rs.getString("Relationship");
		        String Phone = rs.getString("Phone");
		        String Address = rs.getString("Address");
		        String City = rs.getString("City");
		        String State = rs.getString("State");
		        String Zip = rs.getString("Zip");
		        System.out.println(PatientID +"\t"+ GuardianNo +"\t"+ FirstName +"\t"+ 
		        		LastName +"\t"+ Relationship +"\t"+ Phone +"\t"+ Address +"\t"+ 
		        		City +"\t"+ State +"\t"+ Zip);
		    }
		}
		else if (Table.equals("Has_Author"))
		{
		    System.out.println("PatientID\t" +
		    		"AuthorID\t" +
		    		"AuthorTitle\t" +
		    		"AuthorFirstName\t" +
		    		"AuthorLastName\t" +
		    		"ParticipatingRole");
		    System.out.println("--------------------------------------------");
		    while (rs.next()) {
		        String PatientID = rs.getString("PatientID");
		        String AuthorID = rs.getString("AuthorID");
		        String AuthorTitle = rs.getString("AuthorTitle");
		        String AuthorFirstName = rs.getString("AuthorFirstName");
		        String AuthorLastName = rs.getString("AuthorLastName");
		        String ParticipatingRole = rs.getString("ParticipatingRole");
		        System.out.println(PatientID +"\t"+ AuthorID +"\t"+
		        		AuthorTitle +"\t"+ AuthorFirstName +"\t"+ AuthorLastName +"\t"+ 
		        		ParticipatingRole); 
		    }
		}
		else if (Table.equals("Has_Insurance"))
		{
		    System.out.println("PatientID\t" +
		    		"PayerID\t" +
		    		"Name\t" +
		    		"Purpose\t" +
		    		"PolicyHolder\t" +
		    		"PolicyType");
		    System.out.println("--------------------------------------------");
		    while (rs.next()) {
		        String PatientID = rs.getString("PatientID");
		        String PayerID = rs.getString("PayerID");
		        String Name = rs.getString("Name");
		        String Purpose = rs.getString("Purpose");
		        String PolicyHolder = rs.getString("PolicyHolder");
		        String PolicyType = rs.getString("PolicyType");
		        System.out.println(PatientID +"\t"+ PayerID +"\t"+ Name +"\t"+ 
		        		Purpose +"\t"+ PolicyHolder +"\t"+ PolicyType);
		    }
		}
		else if (Table.equals("Has_History"))
		{
		    System.out.println("PatientID\t" +
		    		"RelativeID\t" +
		    		"Relation\t" +
		    		"Age\t" +
		    		"Diagnosis");
		    System.out.println("--------------------------------------------");
		    while (rs.next()) {
		        String PatientID = rs.getString("PatientID");
		        String RelativeID = rs.getString("RelativeID");
		        String Relation = rs.getString("Relation");
		        String Age = rs.getString("Age");
		        String Diagnosis = rs.getString("Diagnosis");
		        System.out.println(PatientID +"\t"+ RelativeID +"\t"+ Relation +"\t"+ 
		        		Age +"\t"+ Diagnosis);
		    }
		}
		else if (Table.equals("Has_Allergies"))
		{
		    System.out.println("PatientID\t" +
		    		"AllergyID\t" +         		
		    		"Substance\t" +
		    		"Reaction\t" +
		    		"Status");
		    System.out.println("--------------------------------------------");
		    while (rs.next()) {
		        String PatientID = rs.getString("PatientID");
		        String AllergyID = rs.getString("AllergyID");
		    	String Substance = rs.getString("Substance");
		        String Reaction = rs.getString("Reaction");
		        String Status = rs.getString("Status");
		        System.out.println(PatientID +"\t"+ AllergyID +"\t"+ Substance +"\t"+ Reaction +"\t"+ 
		        		Status);
		    }
		}
		else if (Table.equals("Has_LabReports"))
		{
		    System.out.println("PatientID\t" +
		    		"LabTestResultID\t" +
		    		"PatientVisitID\t" +
		    		"LabTestPerformedDate\t" +
		    		"LabTestType\t" +
		    		"TestResultValue\t" +
		    		"ReferenceRangeHigh\t" +
		    		"ReferenceRangeLow");
		    System.out.println("--------------------------------------------");
		    while (rs.next()) {
		        String PatientID = rs.getString("PatientID");
		    	String LabTestResultID = rs.getString("LabTestResultID");
		        String PatientVisitID = rs.getString("PatientVisitID");
		        String LabTestPerformedDate = rs.getString("LabTestPerformedDate");
		        String LabTestType = rs.getString("LabTestType");
		        String TestResultValue = rs.getString("TestResultValue");
		        String ReferenceRangeHigh = rs.getString("ReferenceRangeHigh");
		        String ReferenceRangeLow = rs.getString("ReferenceRangeLow");
		        System.out.println(PatientID +"\t"+ LabTestResultID +"\t"+ PatientVisitID +"\t"+ 
		        		LabTestPerformedDate +"\t"+ LabTestType +"\t"+ TestResultValue +"\t"+ 
		        		ReferenceRangeHigh +"\t"+ ReferenceRangeLow);
		    }
		}
		else if (Table.equals("Has_Plan"))
		{
		    System.out.println("PatientID\t" +
		    		"PlanId\t" +
		    		"Activity\t" +
		    		"ScheduledDate");
		    System.out.println("--------------------------------------------");
		    while (rs.next()) {
		        String PatientID = rs.getString("PatientID");
		        String PlanId = rs.getString("PlanId");
		        String Activity = rs.getString("Activity");
		        String ScheduledDate = rs.getString("ScheduledDate");
		        System.out.println(PatientID +"\t"+ PlanId +"\t"+ Activity +"\t"+ ScheduledDate);
		    }
		}
		System.out.println();
		String query3 = "DROP VIEW IF EXISTS Patient_View";
		st.executeUpdate(query3);
		return 0;
    }
    public int doctorView(String Table) 
    throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException
{
    	st = null;
        st = xmlparse.hisCon.createStatement();
        String query = "CREATE OR REPLACE VIEW Doctor_View" +
                    "    AS SELECT *\n" +
                    "    FROM " + Table;
		st.executeUpdate(query);
		String query2 = "SELECT * FROM Doctor_View";
		rs = st.executeQuery(query2);
		if (Table.equals("Patient"))
		{
		    System.out.println("PatientID\t" +
		    		"GuardianNo\t" +
		    		"GivenName\t" +
		    		"FamilyName\t" +
		    		"Suffix\t" +
		    		"Gender\t" +
		    		"Birthtime\t" +
		    		"ProviderID\t" +
		    		"xmlHealthCreationDateTime");
		    System.out.println("--------------------------------------------");
		    while (rs.next()) {
		        String PatientID = rs.getString("PatientID");
		        String GuardianNo = rs.getString("GuardianNo");
		        String GivenName = rs.getString("GivenName");
		        String FamilyName = rs.getString("FamilyName");
		        String Suffix = rs.getString("Suffix");
		        String Gender = rs.getString("Gender");
		        String Birthtime = rs.getString("Birthtime");
		        String ProviderID = rs.getString("ProviderID");
		        String xmlHealthCreationDateTime = rs.getString("xmlHealthCreationDateTime");
		        System.out.println(PatientID +"\t"+ GuardianNo +"\t"+ 
		        		GivenName +"\t"+ FamilyName +"\t"+ Suffix +"\t"+ 
		        		Gender +"\t"+ Birthtime +"\t"+ ProviderID +"\t"+
		        		xmlHealthCreationDateTime);
		    }
		}
		else if (Table.equals("Has_Guardian"))
		{
		    System.out.println("PatientID\t" +
		    		"GuardianNo\t" +
		    		"FirstName\t" +
		    		"LastName\t" +
		    		"Relationship\t" +                		
		    		"Phone\t" +
		    		"Address\t" +
		    		"City\t" +
		    		"State\t" +
		    		"Zip");
		    System.out.println("--------------------------------------------");
		    while (rs.next()) {
		        String PatientID = rs.getString("PatientID");
		        String GuardianNo = rs.getString("GuardianNo");
		        String FirstName = rs.getString("FirstName");
		        String LastName = rs.getString("LastName");
		        String Relationship = rs.getString("Relationship");
		        String Phone = rs.getString("Phone");
		        String Address = rs.getString("Address");
		        String City = rs.getString("City");
		        String State = rs.getString("State");
		        String Zip = rs.getString("Zip");
		        System.out.println(PatientID +"\t"+ GuardianNo +"\t"+ FirstName +"\t"+ 
		        		LastName +"\t"+ Relationship +"\t"+ Phone +"\t"+ Address +"\t"+ 
		        		City +"\t"+ State +"\t"+ Zip);
		    }
		}
		else if (Table.equals("Has_Author"))
		{
		    System.out.println("PatientID\t" +
		    		"AuthorID\t" +
		    		"AuthorTitle\t" +
		    		"AuthorFirstName\t" +
		    		"AuthorLastName\t" +
		    		"ParticipatingRole");
		    System.out.println("--------------------------------------------");
		    while (rs.next()) {
		        String PatientID = rs.getString("PatientID");
		        String AuthorID = rs.getString("AuthorID");
		        String AuthorTitle = rs.getString("AuthorTitle");
		        String AuthorFirstName = rs.getString("AuthorFirstName");
		        String AuthorLastName = rs.getString("AuthorLastName");
		        String ParticipatingRole = rs.getString("ParticipatingRole");
		        System.out.println(PatientID +"\t"+ AuthorID +"\t"+
		        		AuthorTitle +"\t"+ AuthorFirstName +"\t"+ AuthorLastName +"\t"+ 
		        		ParticipatingRole); 
		    }
		}
		else if (Table.equals("Has_Insurance"))
		{
		    System.out.println("PatientID\t" +
		    		"PayerID\t" +
		    		"Name\t" +
		    		"Purpose\t" +
		    		"PolicyHolder\t" +
		    		"PolicyType");
		    System.out.println("--------------------------------------------");
		    while (rs.next()) {
		        String PatientID = rs.getString("PatientID");
		        String PayerID = rs.getString("PayerID");
		        String Name = rs.getString("Name");
		        String Purpose = rs.getString("Purpose");
		        String PolicyHolder = rs.getString("PolicyHolder");
		        String PolicyType = rs.getString("PolicyType");
		        System.out.println(PatientID +"\t"+ PayerID +"\t"+ Name +"\t"+ 
		        		Purpose +"\t"+ PolicyHolder +"\t"+ PolicyType);
		    }
		}
		else if (Table.equals("Has_History"))
		{
		    System.out.println("PatientID\t" +
		    		"RelativeID\t" +
		    		"Relation\t" +
		    		"Age\t" +
		    		"Diagnosis");
		    System.out.println("--------------------------------------------");
		    while (rs.next()) {
		        String PatientID = rs.getString("PatientID");
		        String RelativeID = rs.getString("RelativeID");
		        String Relation = rs.getString("Relation");
		        String Age = rs.getString("Age");
		        String Diagnosis = rs.getString("Diagnosis");
		        System.out.println(PatientID +"\t"+ RelativeID +"\t"+ Relation +"\t"+ 
		        		Age +"\t"+ Diagnosis);
		    }
		}
		else if (Table.equals("Has_Allergies"))
		{
		    System.out.println("PatientID\t" +
		    		"AllergyID\t" +         		
		    		"Substance\t" +
		    		"Reaction\t" +
		    		"Status");
		    System.out.println("--------------------------------------------");
		    while (rs.next()) {
		        String PatientID = rs.getString("PatientID");
		        String AllergyID = rs.getString("AllergyID");
		    	String Substance = rs.getString("Substance");
		        String Reaction = rs.getString("Reaction");
		        String Status = rs.getString("Status");
		        System.out.println(PatientID +"\t"+ AllergyID +"\t"+ Substance +"\t"+ Reaction +"\t"+ 
		        		Status);
		    }
		}
		else if (Table.equals("Has_LabReports"))
		{
		    System.out.println("PatientID\t" +
		    		"LabTestResultID\t" +
		    		"PatientVisitID\t" +
		    		"LabTestPerformedDate\t" +
		    		"LabTestType\t" +
		    		"TestResultValue\t" +
		    		"ReferenceRangeHigh\t" +
		    		"ReferenceRangeLow");
		    System.out.println("--------------------------------------------");
		    while (rs.next()) {
		        String PatientID = rs.getString("PatientID");
		    	String LabTestResultID = rs.getString("LabTestResultID");
		        String PatientVisitID = rs.getString("PatientVisitID");
		        String LabTestPerformedDate = rs.getString("LabTestPerformedDate");
		        String LabTestType = rs.getString("LabTestType");
		        String TestResultValue = rs.getString("TestResultValue");
		        String ReferenceRangeHigh = rs.getString("ReferenceRangeHigh");
		        String ReferenceRangeLow = rs.getString("ReferenceRangeLow");
		        System.out.println(PatientID +"\t"+ LabTestResultID +"\t"+ PatientVisitID +"\t"+ 
		        		LabTestPerformedDate +"\t"+ LabTestType +"\t"+ TestResultValue +"\t"+ 
		        		ReferenceRangeHigh +"\t"+ ReferenceRangeLow);
		    }
		}
		else if (Table.equals("Has_Plan"))
		{
		    System.out.println("PatientID\t" +
		    		"PlanId\t" +
		    		"Activity\t" +
		    		"ScheduledDate");
		    System.out.println("--------------------------------------------");
		    while (rs.next()) {
		        String PatientID = rs.getString("PatientID");
		        String PlanId = rs.getString("PlanId");
		        String Activity = rs.getString("Activity");
		        String ScheduledDate = rs.getString("ScheduledDate");
		        System.out.println(PatientID +"\t"+ PlanId +"\t"+ Activity +"\t"+ ScheduledDate);
		    }
		}
		System.out.println();
		String query3 = "DROP VIEW IF EXISTS Doctor_View";
		st.executeUpdate(query3);
        return 0;
}
    public int updateTable(String Table, String Column, String ColumnValue, String PatientID)
        throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException
    {   
        System.out.println("Updating " + Column + " to be " + ColumnValue + " for PatientID " + PatientID + "In " + Table);        
        	st = null;
            st = xmlparse.hisCon.createStatement();
            String query = "UPDATE IGNORE " + Table + 
                            " SET " + Column + " = " + "\""  + ColumnValue + "\"" + 
                            " WHERE " + Table + ".PatientID = " + PatientID + ";";            
            System.out.println("query is " + query);
            System.out.println (st.executeUpdate(query) + " row(s) affected.");
            
            
            DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
			Date date = new Date();
			String xmlCreationDate1 = dateFormat.format(date);
			System.out.println("Report Date: " + xmlCreationDate1);
			
			String updateXML = "UPDATE Patient SET xmlHealthCreationDateTime  = \""+xmlCreationDate1+"\" WHERE PatientID = \"" + PatientID+"\";";
			st.executeUpdate(updateXML);
            return 0;          
    
    }
    public int updatePlanTable(String Table, String Column, String ColumnValue, String PlanId)
            throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException
        {   
            System.out.println("Updating " + Column + " to be " + ColumnValue + " for PlanId " + PlanId + "In " + Table);        
            	st = null;
                st = xmlparse.hisCon.createStatement();
                String query = "UPDATE IGNORE " + Table + 
                                " SET " + Column + " = " + "\""  + ColumnValue + "\"" + 
                                " WHERE " + Table + ".PlanId = " + PlanId;
                System.out.println("query is " + query);
                System.out.println (st.executeUpdate(query) + " row(s) affected.");
          		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
				Date date = new Date();
				String xmlCreationDate1 = dateFormat.format(date);
				System.out.println("Report Date: " + xmlCreationDate1);
				String getPatientID = "SELECT PatientID FROM Has_Plan WHERE PlanID = \"" + PlanId +"\"";
				rs = st.executeQuery(getPatientID);
				String PatientID= null;
				while(rs.next()) {
					PatientID = rs.getString(1);
				}				
				if (PatientID != null)
				{
				String updateXML = "UPDATE Patient SET xmlHealthCreationDateTime  = \""+xmlCreationDate1+"\" WHERE PatientID = \"" +PatientID+"\";";
				st.executeUpdate(updateXML);
				}
                return 0;          
        
        }
    public int Administrator_numPatientsPerAllergy()
        throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException
    {
           	st = null;
            st = xmlparse.hisCon.createStatement();
            String query = "CREATE OR REPLACE VIEW Administrator_numPatientsPerAllergy " +
                            "AS SELECT COUNT(DISTINCT PatientID) AS numPatients, Substance " +
                            "FROM Has_Allergies " +
                            "GROUP BY Substance;";
            System.out.println("query is " + query);
    		st.executeUpdate(query);
    		String query2 = "SELECT * FROM Administrator_numPatientsPerAllergy";
    		rs = st.executeQuery(query2);
            System.out.println("PatientID\t" +
            		"Count");
            System.out.println("--------------------------------------------");
            while (rs.next()) {
                String Substance = rs.getString(1);
                String Count = rs.getString(2);
                System.out.println(Count + "\t" + Substance);
            }
            return 0;
    }

     public int Administrator_patientsWithAllergies()
        throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException
    {
        	st = null;
            st = xmlparse.hisCon.createStatement();
            String query = "CREATE OR REPLACE VIEW Administrator_patientsWithAllergies " +
                            "AS SELECT DISTINCT PatientID " +
                            "FROM Has_Allergies " +
                            "GROUP BY PatientID " +
                            "HAVING COUNT(*) > 1;";
    		st.executeUpdate(query);
    		String query2 = "SELECT * FROM Administrator_patientsWithAllergies";
    		rs = st.executeQuery(query2);
            System.out.println("PatientID");
            System.out.println("--------------------------------------------");
            while (rs.next()) {
                String PatientID = rs.getString(1);
                System.out.println(PatientID);
            }
            return 0;
    }
    
    public int Administrator_patientsWithPlans()
        throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException
    {
        	st = null;
            st = xmlparse.hisCon.createStatement();
            DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
            Date date = new Date();
            String datestring = dateFormat.format(date); 
            String query = "CREATE OR REPLACE VIEW Administrator_patientsWithPlans\n" +
                            "    AS SELECT DISTINCT Plan.PatientID\n" +
                            "    FROM Has_Plan Plan\n" +
                            "    WHERE Plan.ScheduledDate = " + "\""+ datestring +"\"";
            System.out.println(query);
    		st.executeUpdate(query);
    		String query2 = "SELECT * FROM Administrator_patientsWithPlans";
    		rs = st.executeQuery(query2);
            System.out.println("PatientID");
            System.out.println("--------------------------------------------");
            while (rs.next()) {
                String PatientID = rs.getString(1);
                System.out.println(PatientID);
            }
            return 0;
    }
    public int Administrator_authorsWithPatients()
        throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException
    {
        	st = null;
            st = xmlparse.hisCon.createStatement();
            String query = "CREATE OR REPLACE VIEW Administrator_authorsWithPatients " +
                            "AS SELECT COUNT(DISTINCT PatientID) AS count, AuthorID " +
                            "FROM Has_Author " +
                            "GROUP BY AuthorID " +
                            "HAVING count > 1;";
            System.out.println(query);
    		st.executeUpdate(query);
    		String query2 = "SELECT * FROM Administrator_authorsWithPatients";
    		rs = st.executeQuery(query2);
            System.out.println("AuthorID\tCount");
            System.out.println("--------------------------------------------");
            while (rs.next()) {
                String Count = rs.getString(1);
                String AuthorID = rs.getString(2);
                System.out.println(AuthorID +"\t\t"+ Count);
            }
            return 0;
    }

}
