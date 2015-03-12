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
		System.out.println("query is " + query);
		rs = st.executeQuery(query);  
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
		    		"ScheduledDate\t" +
		    		"Status");
		    System.out.println("--------------------------------------------");
		    while (rs.next()) {
		        String PatientID = rs.getString("PatientID");
		        String PlanId = rs.getString("PlanId");
		        String Activity = rs.getString("Substance");
		        String ScheduledDate = rs.getString("Reaction");
		        String Status = rs.getString("Status");
		        System.out.println(PatientID +"\t"+ PlanId +"\t"+ Activity +"\t"+ ScheduledDate +"\t"+ 
		        		Status);
		    }
		}
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
        System.out.println("query is " + query);
        rs = st.executeQuery(query);
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
            		"ScheduledDate\t" +
            		"Status");
            System.out.println("--------------------------------------------");
            while (rs.next()) {
                String PatientID = rs.getString("PatientID");
                String PlanId = rs.getString("PlanId");
                String Activity = rs.getString("Substance");
                String ScheduledDate = rs.getString("Reaction");
                String Status = rs.getString("Status");
                System.out.println(PatientID +"\t"+ PlanId +"\t"+ Activity +"\t"+ ScheduledDate +"\t"+ 
                		Status);
            }
        }
        return 0;
}
    public int updateTable(String Table, String Column, String ColumnValue, String PatientID)
        throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException
    {   
        System.out.println("In " + Table);
        System.out.println("Updating " + Column + " to be " + ColumnValue + " for PatientID " + PatientID);        
        	st = null;
            st = xmlparse.hisCon.createStatement();
            String query = "UPDATE " + Table + 
                            " SET " + Column + " = " + ColumnValue + 
                            " WHERE " + Table + ".PatientID = " + PatientID + ";";            
            System.out.println("query is " + query);
            rs = st.executeQuery(query);
            System.out.println (rs + " row(s) affected.");
            return 0;          
    
    }
    public int Administrator_numPatientsPerAllergy()
        throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException
    {
           	st = null;
            st = xmlparse.hisCon.createStatement();
            String query = "CREATE OR REPLACE VIEW Administrator_numPatientsPerAllergy\n" +
                            "    AS SELECT A.Substance, COUNT (A.PatientID) AS numPatients\n" +
                            "    FROM Has_Allergies A\n" +
                            "    GROUP BY A.Substance";
            System.out.println("query is " + query);
            rs = st.executeQuery(query);
            System.out.println("PatientID\t" +
            		"Count");
            System.out.println("--------------------------------------------");
            while (rs.next()) {
                String Substance = rs.getString(1);
                String Count = rs.getString(2);
                System.out.println(Substance +"\t"+ Count);
            }
            return 0;
    }

     public int Administrator_patientsWithAllergies()
        throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException
    {
        	st = null;
            st = xmlparse.hisCon.createStatement();
            String query = "CREATE OR REPLACE VIEW Administrator_patientsWithAllergies\n" +
                            "    AS SELECT DISTINCT A.PatientID\n" +
                            "    FROM Has_Allergies A\n" +
                            "    WHERE COUNT (PatientID) > 1";
            System.out.println("query is " + query);
            rs = st.executeQuery(query);   
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
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String datestring = dateFormat.format(date); 
            String query = "CREATE OR REPLACE VIEW Administrator_patientsWithPlans\n" +
                            "    AS SELECT DISTINCT Plan.PatientID\n" +
                            "    FROM Has_Plan Plan\n" +
                            "    WHERE Plan.ScheduleDate = " + datestring;
            System.out.println("query is " + query);
            rs = st.executeQuery(query);
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
            String query = "CREATE OR REPLACE VIEW Administrator_authorsWithPatients\n" +
                            "    AS SELECT DISTINCT A.AuthorID, A.AuthorFirstName, A.AuthorLastName\n" +
                            "    FROM Has_Author A\n" +
                            "    GROUP BY A.AuthorID\n" +
                            "    HAVING COUNT (DISTINCT PatientID) > 1";
            System.out.println("query is " + query);
            rs = st.executeQuery(query);    
            System.out.println("AuthorID\t" +
            		"AuthorFirstName\t" +
            		"AuthorLastName");
            System.out.println("--------------------------------------------");
            while (rs.next()) {
                String AuthorID = rs.getString(1);
                String AuthorFirstName = rs.getString(2);
                String AuthorLastName = rs.getString(3);
                System.out.println(AuthorID +"\t"+ AuthorFirstName +"\t"+ 
                		AuthorLastName);
            }
            return 0;
    }

}

    
    
/*POSSIBLY UNECESSARY FUNCTIONS */
/*
    public int dropAllViews()
    throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException
{
    try{       	
        st = xmlparse.hisCon.createStatement();
        String query = "DROP VIEW IF EXISTS" + 
                        "Patient_View, Doctor_View";
        System.out.println("query is " + query);
        rs = st.executeQuery(query);    
        return 0;
}catch(ParserConfigurationException pce) {
	pce.printStackTrace();
            return (-1);
}catch(SAXException se) {
	se.printStackTrace();
            return (-1);
}catch(IOException ioe) {
	ioe.printStackTrace();
            return (-1);
}
      
}
    
    public int planUpdateActivity(String Activity, Date ScheduledDate, String PatientID)
        throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException
    {
        try{       	
            st = xmlparse.hisCon.createStatement();
            String incomplete_query = "UPDATE Has_Plan\n" +
                                        "    SET Activity = ";
            String incomplete_query2 = incomplete_query.concat(Activity);
            String incomplete_query3 = incomplete_query2.concat(" WHERE Has_Plan.PatientID =  ");
            String query = incomplete_query3.concat(PatientID);
            System.out.println("query is " + query);
            rs = st.executeQuery(query);    
            return 0;
	}catch(ParserConfigurationException pce) {
		pce.printStackTrace();
                return (-1);
	}catch(SAXException se) {
		se.printStackTrace();
                return (-1);
	}catch(IOException ioe) {
		ioe.printStackTrace();
                return (-1);
	}
    }
    public int planUpdateScheduledDate(String Activity, Date ScheduledDate, String PatientID)
        throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException
    {
        try{       	
            st = xmlparse.hisCon.createStatement();
            String incomplete_query = "UPDATE Has_Plan\n" +
                                        "    SET ScheduledDate = ";
            String incomplete_query2 = incomplete_query.concat(ScheduledDate.toString());
            String incomplete_query3 = incomplete_query2.concat(" WHERE Has_Plan.PatientID =  ");
            String query = incomplete_query3.concat(PatientID);
            rs = st.executeQuery(query);    
            return 0;
	}catch(ParserConfigurationException pce) {
		pce.printStackTrace();
                return (-1);
	}catch(SAXException se) {
		se.printStackTrace();
                return (-1);
	}catch(IOException ioe) {
		ioe.printStackTrace();
                return (-1);
	}
    }
    public int allergiesUpdateSubstance (String Substance, String PatientID)
        throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException
    {
        try{       	
            st = xmlparse.hisCon.createStatement();
            String incomplete_query = "UPDATE Has_Allergies\n" +
                                        "    SET Substance = ";
            String incomplete_query2 = incomplete_query.concat(Substance);
            String incomplete_query3 = incomplete_query2.concat(" WHERE Has_Plan.PatientID =  ");
            String query = incomplete_query3.concat(PatientID);
            rs = st.executeQuery(query);    
            return 0;
	}catch(ParserConfigurationException pce) {
		pce.printStackTrace();
                return (-1);
	}catch(SAXException se) {
		se.printStackTrace();
                return (-1);
	}catch(IOException ioe) {
		ioe.printStackTrace();
                return (-1);
	}
    }
    public int allergiesUpdateReaction(String Reaction, String PatientID)
        throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException
    {
        try{       	
            st = xmlparse.hisCon.createStatement();
            String incomplete_query = "UPDATE Has_Allergies\n" +
                                        "    SET Reaction = ";
            String incomplete_query2 = incomplete_query.concat(Reaction);
            String incomplete_query3 = incomplete_query2.concat(" WHERE Has_Plan.PatientID =  ");
            String query = incomplete_query3.concat(PatientID);
            rs = st.executeQuery(query);    
            return 0;
	}catch(ParserConfigurationException pce) {
		pce.printStackTrace();
                return (-1);
	}catch(SAXException se) {
		se.printStackTrace();
                return (-1);
	}catch(IOException ioe) {
		ioe.printStackTrace();
                return (-1);
	}
    }
    public int allergiesUpdateStatus (String Status, String PatientID)
        throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException
    {
        try{       	
            st = xmlparse.hisCon.createStatement();
            String incomplete_query = "UPDATE Has_Allergies\n" +
                                        "    SET Status = ";
            String incomplete_query2 = incomplete_query.concat(Status);
            String incomplete_query3 = incomplete_query2.concat(" WHERE Has_Plan.PatientID =  ");
            String query = incomplete_query3.concat(PatientID);
            rs = st.executeQuery(query);    
            return 0;
	}catch(ParserConfigurationException pce) {
		pce.printStackTrace();
                return (-1);
	}catch(SAXException se) {
		se.printStackTrace();
                return (-1);
	}catch(IOException ioe) {
		ioe.printStackTrace();
                return (-1);
	}
    }
    public int guardianView (String PatientID)
        throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException
    {
        try{       	
            st = xmlparse.hisCon.createStatement();
            String query = "CREATE OR REPLACE VIEW Guardian_View" +
                        "    AS SELECT *\n" +
                        "    FROM Has_Guardian G, Patient P\n" +
                        "    WHERE G.GuardianID = P.PatientID";
            System.out.println("query is " + query);
            rs = st.executeQuery(query);    
            return 0;
	}catch(ParserConfigurationException pce) {
		pce.printStackTrace();
                return (-1);
	}catch(SAXException se) {
		se.printStackTrace();
                return (-1);
	}catch(IOException ioe) {
		ioe.printStackTrace();
                return (-1);
	}
          
    }
    
}
*/