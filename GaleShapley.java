import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
	 *    Java Program to Implement Gale Shapley Algorithm 
	 **/
	 
	/** Class GaleShapley **/
public class GaleShapley {

	    
	    public static List<String> readColumnOne(String filename) {
	    	

	        String csvFile = filename;
	        BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";
	        List<String> data = new ArrayList<String>( );

	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	           
	            while ((line = br.readLine()) != null) {

	                // use comma as separator
	                String[] allEData = line.split(cvsSplitBy);
	                data.add(allEData[0]);


	            }

	        } catch (FileNotFoundException e) {
	            e.printStackTrace(); 
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        
	        return data;
	    	
	    }
	    
	    public static List<String> readRows(String filename, String key) {
	    	

	        String csvFile = filename;
	        BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";
	        List<String> data = new ArrayList<String>();

	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	            

	            while ((line = br.readLine()) != null) {

	                // use comma as separator
	                String[] allData = line.split(cvsSplitBy);
		            
		            int i = 1;
		            
		            if(allData[0].equals(key)) {
		            	
		            	while( i < allData.length) {
		            		
		            		data.add(allData[i]);
		            		i++;
		            		
		            	}
		            }

	            }

	        } catch (FileNotFoundException e) {
	            e.printStackTrace(); 
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        
	        return data;
	    	
	    }
	    
	    public static Map<String, ArrayList<String>> Prefers(List<String> keyArray, String filename){
	
	    	 Map<String, ArrayList<String>> Prefers = new HashMap<String, ArrayList<String>>();	
	    	 
	    	 int i = 0;
	    	 while( i < keyArray.size()) {
		    		
		    	List<String> valuesFromFile = new ArrayList<String>();
   		   	    valuesFromFile = readRows(filename, keyArray.get(i));
			   	Prefers.put(keyArray.get(i), (ArrayList<String>) valuesFromFile);
		    	
			   	i++;
		    	
		    	}
	    	
	    	
//	    	 Map<String, ArrayList<String>> employeePrefers = new HashMap<String, ArrayList<String>>();	    	
//		    	
//		    	int i = 0;
//		    	while( i < employees.size()) {
//		    		
//		    	List<String> studentsFromEFile = new ArrayList<String>();
//			   	studentsFromEFile = readRows(filename1, employees.get(i));
//			   	employeePrefers.put(employees.get(i), (ArrayList<String>) studentsFromEFile);
//
//		    		
//		    		i++;
//		    	
//		    	}
	    	 
	    	 
	    	 return Prefers;
	    	
	    }
	    
	    
	  
	    /** main function **/
	    public static void main(String[] args) 
	    {
	    	List<String> employees = new ArrayList<String>();
	    	List<String> students = new ArrayList<String>();
	    	
	    	String filename1 = "/Users/ayeshaabid/Downloads/StableMatching-master/coop_e_10x10.csv";
	    	String filename2 = "/Users/ayeshaabid/Downloads/StableMatching-master/coop_s_10x10.csv";

	    	employees = readColumnOne(filename1);
	    	students = readColumnOne(filename2);
	    	
	    	Map<String, ArrayList<String>> employeePrefers = new HashMap<String, ArrayList<String>>();	
	    	Map<String, ArrayList<String>> studentPrefers = new HashMap<String, ArrayList<String>>();	
	    	
	    	employeePrefers = Prefers(employees, filename1);
	    	studentPrefers = Prefers(students, filename2);



	    }
	    	
//	        System.out.println("Gale Shapley Marriage Algorithm\n");
//	        /** list of men **/
//	        String[] m = {"M1", "M2", "M3", "M4", "M5"};
//	        /** list of women **/
//	        String[] w = {"W1", "W2", "W3", "W4", "W5"};
//	 
//	        /** men preference **/
//	        String[][] mp = {{"W5", "W2", "W3", "W4", "W1"}, 
//	                         {"W2", "W5", "W1", "W3", "W4"}, 
//	                         {"W4", "W3", "W2", "W1", "W5"}, 
//	                         {"W1", "W2", "W3", "W4", "W5"},
//	                         {"W5", "W2", "W3", "W4", "W1"}};
//	        /** women preference **/                      
//	        String[][] wp = {{"M5", "M3", "M4", "M1", "M2"}, 
//	                         {"M1", "M2", "M3", "M5", "M4"}, 
//	                         {"M4", "M5", "M3", "M2", "M1"},
//	                         {"M5", "M2", "M1", "M4", "M3"}, 
//	                         {"M2", "M1", "M4", "M3", "M5"}};
//	 
//	        GaleShapley gs = new GaleShapley(m, w, mp, wp);                        
	  

}
