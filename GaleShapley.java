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
	    	 
	    	 return Prefers;
	    	
	    }
	    
	    public static Map<String, String> match(List<String> employers, Map<String, ArrayList<String>> employerPrefers, Map<String, ArrayList<String>> studentPrefers) {
	       
	    	Map<String, String> matchedTo = new TreeMap<String, String>();
	        
	    	List<String> freeEmployers = new LinkedList<String>();
	        
	    	freeEmployers.addAll(employers);
	        
	        while(!freeEmployers.isEmpty()){
	            
	        	String thisEmployer = freeEmployers.remove(0); 
	            List<String> thisEmployerPrefers = employerPrefers.get(thisEmployer);
	            
	            for(String student:thisEmployerPrefers){
	                
	            	if(matchedTo.get(student) == null){
	            		
	                    matchedTo.put(student, thisEmployer); 
	                    break;
	                    
	                }else{
	                   
	                	String otherEmployer = matchedTo.get(student);
	                    List<String> thisStudentPrefers = studentPrefers.get(student);
	                  
	                    if(thisStudentPrefers.indexOf(thisEmployer) < thisStudentPrefers.indexOf(otherEmployer)){
	                        
	                    	matchedTo.put(student, thisEmployer);
	                        freeEmployers.add(otherEmployer);
	                        
	                        break;
	                    }
	                }
	            }
	        }
	        return matchedTo;
	    }
	    
	    public static boolean checkMatches(List<String> employers, List<String> students,
	            Map<String, String> matches, Map<String, ArrayList<String>> employerPrefers,
	            Map<String, ArrayList<String>> studentPrefers) {
	        if(!matches.keySet().containsAll(students)){
	            return false;
	        }
	 
	        if(!matches.values().containsAll(employers)){
	            return false;
	        }
	 
	        Map<String, String> invertedMatches = new TreeMap<String, String>();
	        for(Map.Entry<String, String> couple:matches.entrySet()){
	            invertedMatches.put(couple.getValue(), couple.getKey());
	        }
	 
	        for(Map.Entry<String, String> couple:matches.entrySet()){
	            List<String> stuPrefers = studentPrefers.get(couple.getKey());
	            List<String> stuLikesBetter = new LinkedList<String>();
	            stuLikesBetter.addAll(stuPrefers.subList(0, stuPrefers.indexOf(couple.getValue())));
	            List<String> empPrefers = employerPrefers.get(couple.getValue());
	            List<String> empLikesBetter = new LinkedList<String>();
	            empLikesBetter.addAll(empPrefers.subList(0, empPrefers.indexOf(couple.getKey())));
	 
	            for(String employer : stuLikesBetter){
	                String employerMatch = invertedMatches.get(employer);
	                List<String> thisEmpPrefers = employerPrefers.get(employer);
	                if(thisEmpPrefers.indexOf(employerMatch) >
	                        thisEmpPrefers.indexOf(couple.getKey())){
	                    System.out.printf("%s likes %s better than %s and %s"
	                            + " likes %s better than their current partner\n",
	                       couple.getKey(), employer, couple.getValue(),
	                       employer, couple.getKey());
	                    return false;
	                }
	            }
	 
	            for(String student : empLikesBetter){
	                String stuMatch = matches.get(student);
	                List<String> thisStuPrefers = studentPrefers.get(student);
	                if(thisStuPrefers.indexOf(stuMatch) >
	                        thisStuPrefers.indexOf(couple.getValue())){
	                    System.out.printf("%s prefers %s to %s and %s"
	                            + " prefers %s to their current match\n",
	                       couple.getValue(), student, couple.getKey(),
	                        student, couple.getValue());
	                    return false;
	                }
	            }
	        }
	        return true;
	    }
	 
	 
	    
	    
	  
	    /** main function **/
	    public static void main(String[] args) 
	    {
	    	List<String> employers = new ArrayList<String>();
	    	List<String> students = new ArrayList<String>();
	    	
	    	String filename1 = "/Users/ayeshaabid/Downloads/StableMatching-master/coop_e_10x10.csv";
	    	String filename2 = "/Users/ayeshaabid/Downloads/StableMatching-master/coop_s_10x10.csv";

	    	employers = readColumnOne(filename1);
	    	students = readColumnOne(filename2);
	    	
	    	Map<String, ArrayList<String>> employerPrefers = new HashMap<String, ArrayList<String>>();	
	    	Map<String, ArrayList<String>> studentPrefers = new HashMap<String, ArrayList<String>>();	
	    	
	    	employerPrefers = Prefers(employers, filename1);
	    	studentPrefers = Prefers(students, filename2);
	    	
	    	
	    	Map<String, String> matches = new HashMap<String, String>();
	    	matches = match(employers, employerPrefers, studentPrefers);
	    	
	        for(Map.Entry<String, String> couple:matches.entrySet()){
	            System.out.println(
	                    couple.getKey() + " is matched to " + couple.getValue());
	        }
	        if(checkMatches(employers, students, matches, employerPrefers, studentPrefers)){
	            System.out.println("Matches are stable");
	        }else{
	            System.out.println("Matches are unstable");
	        }
	        String tmp = matches.get(students.get(0));
	        matches.put(students.get(0), matches.get(students.get(1)));
	        matches.put(students.get(1), tmp);
	        System.out.println(
	                students.get(0) +" and " + students.get(1) + " have switched employers");
	        if(checkMatches(employers, students, matches, employerPrefers, studentPrefers)){
	            System.out.println("Matches are stable");
	        }else{
	            System.out.println("Matches are unstable");
	        }
	    	
	    	
	    	


	    }
	    	
                       
	  

}
