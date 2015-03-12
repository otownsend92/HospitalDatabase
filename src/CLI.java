import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public class CLI {

	
	public static String xmlCreationDate1;
	public static MySQLQueries msq;
    public static boolean exit;
    public static boolean patientStatus, doctorStatus, administratorStatus;
    public static String Table;
    public static String Column;
    public static String ColumnValue;
    public static String PatientID;
    public static String choice, ID, privelege, columnChoice, columnValue, view;
	
    public static void enterCLIMode() throws IOException, ClassNotFoundException, SQLException, SAXException, ParserConfigurationException {
		
		msq = new MySQLQueries();
        BufferedReader input = new BufferedReader (new InputStreamReader (System.in));
        exit = false;
        while (!exit)
        {
            System.out.println("HOME MENU:");
            System.out.println("Type 'exit' at anytime to exit");
            System.out.println("What are you?");
            System.out.println("1) Patient");
            System.out.println("2) Doctor");
            System.out.println("3) Administrator");            
            privelege = input.readLine();
            if (privelege.equals("exit")) {exit();}
            while (!privelege.equals("1") && !privelege.equals("2") && !privelege.equals("3"))
            {
                System.out.println(privelege + " is an invalid input. Try again.");
                privelege = input.readLine();
                if (privelege.equals("exit")) {exit();}
            }        
            if (privelege.equals("1"))
            {
                patientStatus = true;
                System.out.println("Please enter your Patient ID");
                ID = input.readLine();
                if (ID.equals("exit")) {exit();}
                while (patientStatus)
                {
                    System.out.println("What would you like to do Patient " + ID + "?");
                    System.out.println("1) View your data");
                    System.out.println("2) Edit patient data");
                    System.out.println("3) Edit guardian data");
                    System.out.println("4) Return to the HOME MENU (if you want to change your patient id)");
                    choice = input.readLine();
                    if (choice.equals("exit")) {exit();}
                    while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4"))
                    {
                        System.out.println(choice + " is an invalid input. Try again.");
                        choice = input.readLine();
                        if (choice.equals("exit")) {exit();}
                    }
                    if (choice.equals("1"))
                    {
                        System.out.println("What table would you like to view Patient " + ID + "?");
                        System.out.println("1) Patient");
                        System.out.println("2) Guardian");
                        System.out.println("3) Author");
                        System.out.println("4) Insurance");
                        System.out.println("5) History");
                        System.out.println("6) Allergies");
                        System.out.println("7) Lab Report");
                        System.out.println("8) Plan");
                        view = input.readLine();
                        if (view.equals("exit")) {exit();}
                        while (!view.equals("1") && !view.equals("2") && !view.equals("3") && !view.equals("4") && !view.equals("5") && !view.equals("6") && !view.equals("7") && !view.equals("8"))
                        {
                            System.out.println(view + " is an invalid input. Try again.");
                            view = input.readLine();
                            if (view.equals("exit")) {exit();}
                        }
                        if (view.equals("1")) {Table = "Patient";}
                        else if (view.equals("2")) {Table = "Has_Guardian";}
                        else if (view.equals("3")) {Table = "Has_Author";}
                        else if (view.equals("4")) {Table = "Has_Insurance";}
                        else if (view.equals("5")) {Table = "Has_History";}
                        else if (view.equals("6")) {Table = "Has_Allergies";}
                        else if (view.equals("7")) {Table = "Has_LabReports";}
                        else if (view.equals("8")) {Table = "Has_Plan";}
                        msq.patientView(Table, ID);
                    }
                    else if (choice.equals("2"))
                    {
                        Table = "Patient";
                        System.out.println("What column would you like to edit in the patient table?");
                        System.out.println("1) GivenName");
                        System.out.println("2) FamilyName");
                        System.out.println("3) Suffix");
                        System.out.println("4) Gender");
                        System.out.println("5) Birthtime");
                        System.out.println("6) ProviderID");
                        columnChoice = input.readLine();
                        if (columnChoice.equals("exit")) {exit();}
                        while (!columnChoice.equals("1") && !columnChoice.equals("2") && !columnChoice.equals("3") && !columnChoice.equals("4") && !columnChoice.equals("5") && !columnChoice.equals("6"))
                        {
                            System.out.println(columnChoice + " is an invalid input. Try again.");
                            columnChoice = input.readLine();
                            if (columnChoice.equals("exit")) {exit();}
                        }
                        if (columnChoice.equals("1"))
                        {
                            columnChoice = "GivenName";
                            System.out.println("What value would you like to insert for your GivenName?");
                        }                        
                        else if (columnChoice.equals("2"))
                        {
                            columnChoice = "FamilyName";
                            System.out.println("What value would you like to insert for your FamilyName?");
                        }                        
                        else if (columnChoice.equals("3"))
                        {
                            columnChoice = "Suffix";
                            System.out.println("What value would you like to insert for your Suffix?");
                        }                        
                        else if (columnChoice.equals("4"))
                        {
                            columnChoice = "Gender";
                            System.out.println("What value would you like to insert for your Gender?");
                        }                        
                        else if (columnChoice.equals("5"))
                        {
                            columnChoice = "Birthtime";
                            System.out.println("What value would you like to insert for your Birthtime?");
                        }                        
                        else if (columnChoice.equals("6"))
                        {
                            columnChoice = "ProviderID";
                            System.out.println("What value would you like to insert for your ProviderID?");
                        }
                        columnValue = input.readLine();
                        if (columnValue.equals("exit")) {exit();}
                        msq.updateTable(Table, columnChoice, columnValue, ID);
                        
                    }
                    else if (choice.equals("3"))
                    {
                        Table = "Has_Guardian";
                        System.out.println("What column would you like to edit in the guardian table for patient ID " + ID + "?");
                        System.out.println("1) FirstName");
                        System.out.println("2) LastName");
                        System.out.println("3) Phone");
                        System.out.println("4) Address");
                        System.out.println("5) City");
                        System.out.println("6) State");
                        System.out.println("7) Zip");
                        System.out.println("8) Relationship");                        
                        System.out.println("9) Nothing. Go back.");
                        columnChoice = input.readLine();
                        if (columnChoice.equals("exit")) {exit();}
                        while (!columnChoice.equals("1") && !columnChoice.equals("2") && !columnChoice.equals("3") && !columnChoice.equals("4") && !columnChoice.equals("5") && !columnChoice.equals("6") && !columnChoice.equals("7") && !columnChoice.equals("8"))
                        {
                            System.out.println(columnChoice + " is an invalid input. Try again.");
                            columnChoice = input.readLine();
                            if (columnChoice.equals("exit")) {exit();}
                        }
                        if (columnChoice.equals("1"))
                        {
                            columnChoice = "FirstName";
                            System.out.println("What value would you like to insert for 'GivenName'?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }
                        else if (columnChoice.equals("2"))
                        {
                            columnChoice = "LastName";
                            System.out.println("What value would you like to insert for 'FamilyName'?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }                        
                        else if (columnChoice.equals("3"))
                        {
                            columnChoice = "Phone";
                            System.out.println("What value would you like to insert for 'Phone'?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }                        
                        else if (columnChoice.equals("4"))
                        {
                            columnChoice = "Address";
                            System.out.println("What value would you like to insert for 'Address'?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }                        
                        else if (columnChoice.equals("5"))
                        {
                            columnChoice = "City";
                            System.out.println("What value would you like to insert for 'City'?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }                        
                        else if (columnChoice.equals("6"))
                        {
                            columnChoice = "State";
                            System.out.println("What value would you like to insert for 'State'?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }                        
                        else if (columnChoice.equals("7"))
                        {
                            columnChoice = "Zip";
                            System.out.println("What value would you like to insert for 'Zip'?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }
                        else if (columnChoice.equals("8"))
                        {
                            columnChoice = "Relationship";
                            System.out.println("What value would you like to insert for 'Relationship'?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }
                        else
                        {
                            //do nothing. go back to the previous menu.
                        }
                    }
                    else //choice 4
                    {
                        System.out.println("Taking you back to the HOME MENU...");
                        patientStatus = false;
                    }
                }
            }
            else if (privelege.equals("2"))
            {
                doctorStatus = true;
                while (doctorStatus)
                {
                    System.out.println("What would you like to do, doctor?");
                    System.out.println("1) View all patient data");
                    System.out.println("2) Edit plan data");
                    System.out.println("3) Edit allergy data");
                    System.out.println("4) Return to the HOME MENU");
                    choice = input.readLine();
                    if (choice.equals("exit")) {exit();}
                    while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4"))
                    {
                        System.out.println(choice + " is an invalid input. Try again.");
                        choice = input.readLine();
                        if (choice.equals("exit")) {exit();}
                    }
                    if (choice.equals("1"))
                    {
                        System.out.println("What table would you like to view for all patients, doctor?");
                        System.out.println("1) Patient");
                        System.out.println("2) Guardian");
                        System.out.println("3) Author");
                        System.out.println("4) Insurance");
                        System.out.println("5) History");
                        System.out.println("6) Allergies");
                        System.out.println("7) Lab Report");
                        System.out.println("8) Plan");
                        view = input.readLine();
                        if (view.equals("exit")) {exit();}
                        while (!view.equals("1") && !view.equals("2") && !view.equals("3") && !view.equals("4") && !view.equals("5") && !view.equals("6") && !view.equals("7") && !view.equals("8") && !view.equals("9"))
                        {
                            System.out.println(view + " is an invalid input. Try again.");
                            view = input.readLine();
                            if (view.equals("exit")) {exit();}
                        }
                        if (view.equals("1")) {Table = "Patient";}
                        else if (view.equals("2")) {Table = "Has_Guardian";}
                        else if (view.equals("3")) {Table = "Has_Author";}
                        else if (view.equals("4")) {Table = "Has_Insurance";}
                        else if (view.equals("5")) {Table = "Has_History";}
                        else if (view.equals("6")) {Table = "Has_Allergies";}
                        else if (view.equals("7")) {Table = "Has_LabReports";}
                        else if (view.equals("8")) {Table = "Has_Plan";}
                        msq.doctorView(Table);
                    }
                    else if (choice.equals("2"))
                    {
                        Table = "Has_Plan";
                        System.out.println("What Plan Id in the Plan Table would you like to edit?");
                        ID = input.readLine();
                        if (ID.equals("exit")) {exit();}
                        System.out.println("What would you like to edit in the Plan Table for Plan Id " + ID + ", doctor?");
                        System.out.println("1) Activity");
                        System.out.println("2) ScheduledDate");
                        System.out.println("3) Nothing. Go back.");
                        columnChoice = input.readLine();
                        if (columnChoice.equals("exit")) {exit();}
                        while (!columnChoice.equals("1") && !columnChoice.equals("2") && !columnChoice.equals("3"))
                        {
                            System.out.println(columnChoice + " is an invalid input. Try again.");
                            columnChoice = input.readLine();
                            if (columnChoice.equals("exit")) {exit();}
                        }
                        if (columnChoice.equals("1"))
                        {
                            columnChoice = "Activity";
                            System.out.println("What value would you like to insert for the Activity?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updatePlanTable(Table, columnChoice, columnValue, ID);
                        }
                        else if (columnChoice.equals("2"))
                        {
                            columnChoice = "ScheduledDate";
                            System.out.println("What value would you like to insert for the ScheduledDate?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updatePlanTable(Table, columnChoice, columnValue, ID);
                        }
                        else
                        {
                            //Do nothing and go back to the previous menu
                        }
                    }
                    else if (choice.equals("3")) //Edit allergy table 
                    {
                        Table = "Has_Allergies";
                        System.out.println("What Patient ID in the Allergies Table would you like to edit?");
                        ID = input.readLine();
                        if (ID.equals("exit")) {exit();}
                        System.out.println("What would you like to edit in the Allergies Table for Patient ID " + ID + ", doctor?");
                        System.out.println("1) Substance");
                        System.out.println("2) Reaction");
                        System.out.println("3) Status");
                        System.out.println("4) Nothing. Go back.");
                        columnChoice = input.readLine();
                        if (columnChoice.equals("exit")) {exit();}
                        while (!columnChoice.equals("1") && !columnChoice.equals("2") && !columnChoice.equals("3") && !columnChoice.equals("4"))
                        {
                            System.out.println(columnChoice + " is an invalid input. Try again.");
                            columnChoice = input.readLine();
                            if (columnChoice.equals("exit")) {exit();}
                        }
                        if (columnChoice.equals("1"))
                        {
                            columnChoice = "Substance";
                            System.out.println("What value would you like to insert for the Substance?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }
                        else if (columnChoice.equals("2"))
                        {
                            columnChoice = "Reaction";
                            System.out.println("What value would you like to insert for the Reaction?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }
                        else if (columnChoice.equals("3"))
                        {
                            columnChoice = "Status";
                            System.out.println("What value would you like to insert for the Status?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }
                        else
                        {
                            //Do nothing and go back to the previous menu
                        	break;
                        }
                    }
                    else //return to home menu
                    {
                        System.out.println("Taking you back to the HOME MENU...");
                        doctorStatus = false;
                    }
                }
            }
            else //administrator
            {
                administratorStatus = true;
                while (administratorStatus)
                {
                    System.out.println("What would you like to do?");
                    System.out.println("1) View number of patients for each type of allergy (substance)");
                    System.out.println("2) List the patients who have more than one allergy");
                    System.out.println("3) List the patients who have a plan for surgery today");
                    System.out.println("4) Identify authors with more than one patient");
                    System.out.println("5) Return to the HOME MENU");

                    choice = input.readLine();
                    if (choice.equals("exit")) {exit();} 
                    while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5"))
                    {
                        System.out.println(choice + " is an invalid input. Try again.");
                        choice = input.readLine();
                        if (choice.equals("exit")) {exit();}
                    }
                    if (choice.equals("1"))
                    {
                        //View number of patients for each type of allergy (substance)
                        msq.Administrator_numPatientsPerAllergy();
                    }
                    else if (choice.equals("2"))
                    {
                        //List the patients who have more than one allergy
                        msq.Administrator_patientsWithAllergies();
                    }
                    else if (choice.equals("3"))
                    {
                        //List the patients who have a plan for surgery today
                        msq.Administrator_patientsWithPlans();
                    }
                    else if (choice.equals("4"))
                    {
                        //List the authors with more than one patient
                        msq.Administrator_authorsWithPatients();
                    }
                    else //return to home menu
                    {
                        System.out.println("Taking you back to the HOME MENU...");
                        administratorStatus = false;
                    }
                }
            }
        }

    }
	
	public static void exit()
    {
        System.out.println("Goodbye.");
        System.exit(0); 
    }
	
	
}
