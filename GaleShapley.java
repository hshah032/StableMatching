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
	
	
	    private int N, engagedCount;
	    private String[][] menPref;
	    private String[][] womenPref;
	    private String[] men;
	    private String[] women;
	    private String[] womenPartner;
	    private boolean[] menEngaged;
	 
	    /** Constructor **/
	    public GaleShapley(String[] m, String[] w, String[][] mp, String[][] wp)
	    {
	        N = mp.length;
	        engagedCount = 0;
	        men = m;
	        women = w;
	        menPref = mp;
	        womenPref = wp;
	        menEngaged = new boolean[N];
	        womenPartner = new String[N];
	        calcMatches();
	    }
	    /** function to calculate all matches **/
	    private void calcMatches()
	    {
	        while (engagedCount < N)
	        {
	            int free;
	            for (free = 0; free < N; free++)
	                if (!menEngaged[free])
	                    break;
	 
	            for (int i = 0; i < N && !menEngaged[free]; i++)
	            {
	                int index = womenIndexOf(menPref[free][i]);
	                if (womenPartner[index] == null)
	                {
	                    womenPartner[index] = men[free];
	                    menEngaged[free] = true;
	                    engagedCount++;
	                }
	                else
	                {
	                    String currentPartner = womenPartner[index];
	                    if (morePreference(currentPartner, men[free], index))
	                    {
	                        womenPartner[index] = men[free];
	                        menEngaged[free] = true;
	                        menEngaged[menIndexOf(currentPartner)] = false;
	                    }
	                }
	            }            
	        }
	        printCouples();
	    }
	    /** function to check if women prefers new partner over old assigned partner **/
	    private boolean morePreference(String curPartner, String newPartner, int index)
	    {
	        for (int i = 0; i < N; i++)
	        {
	            if (womenPref[index][i].equals(newPartner))
	                return true;
	            if (womenPref[index][i].equals(curPartner))
	                return false;
	        }
	        return false;
	    }
	    /** get men index **/
	    private int menIndexOf(String str)
	    {
	        for (int i = 0; i < N; i++)
	            if (men[i].equals(str))
	                return i;
	        return -1;
	    }
	    /** get women index **/
	    private int womenIndexOf(String str)
	    {
	        for (int i = 0; i < N; i++)
	            if (women[i].equals(str))
	                return i;
	        return -1;
	    }
	    /** print couples **/
	    public void printCouples()
	    {
	        System.out.println("Couples are : ");
	        for (int i = 0; i < N; i++)
	        {
	            System.out.println(womenPartner[i] +" "+ women[i]);
	        }
	    }
	    
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
	    	
	    	int i = 0;
	    	while( i < employees.size()) {
	    		
	    		List<String> studentsFromEFile = new ArrayList<String>();
		    	studentsFromEFile = readRows(filename1, employees.get(i));
		    	employeePrefers.put(employees.get(i), (ArrayList<String>) studentsFromEFile);
	    		
	    	}
	    	
//	    	System.out.println(Arrays.asList(employeePrefers));
	    	System.out.println("hi");

	    	
//	    	static Map<String, ArrayList<String>> employeePrefers = 
//	    			new HashMap<String, ArrayList<String>>(){{
//	    		        put(employees.get(0), studentsFromEEfile
//	    		            Arrays.asList("abi", "eve", "cath", "ivy", "jan", "dee", "fay",
//	    		            "bea", "hope", "gay"));
//	    		        put("bob",
//	    		            Arrays.asList("cath", "hope", "abi", "dee", "eve", "fay", "bea",
//	    		            "jan", "ivy", "gay"));
//	    		        put("col",
//	    		            Arrays.asList("hope", "eve", "abi", "dee", "bea", "fay", "ivy",
//	    		            "gay", "cath", "jan"));
//	    		        put("dan",
//	    		            Arrays.asList("ivy", "fay", "dee", "gay", "hope", "eve", "jan",
//	    		            "bea", "cath", "abi"));
//	    		        put("ed",
//	    		            Arrays.asList("jan", "dee", "bea", "cath", "fay", "eve", "abi",
//	    		            "ivy", "hope", "gay"));
//	    		        put("fred",
//	    		            Arrays.asList("bea", "abi", "dee", "gay", "eve", "ivy", "cath",
//	    		            "jan", "hope", "fay"));
//	    		        put("gav",
//	    		            Arrays.asList("gay", "eve", "ivy", "bea", "cath", "abi", "dee",
//	    		            "hope", "jan", "fay"));
//	    		        put("hal",
//	    		            Arrays.asList("abi", "eve", "hope", "fay", "ivy", "cath", "jan",
//	    		            "bea", "gay", "dee"));
//	    		        put("ian",
//	    		            Arrays.asList("hope", "cath", "dee", "gay", "bea", "abi", "fay",
//	    		            "ivy", "jan", "eve"));
//	    		        put("jon",
//	    		            Arrays.asList("abi", "fay", "jan", "gay", "eve", "bea", "dee",
//	    		            "cath", "ivy", "hope"));
//	    		    }};

	        
	        


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
