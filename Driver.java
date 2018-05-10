import java.io.*;
import java.util.*;
import java.lang.*;

public class Driver
{
	public static void main(String[] args) 
	{
		//create an instance of my hashtable class
		HashTable myStringHashTable = new HashTable(1000);
		//create instance of java's hashtable
		HashMap<String, String> javaStringHashMap = new HashMap<>();
		long myTime=0;
	 	long javaTime=0;
	 	ArrayList <Long> myStringTimes = new ArrayList<>();
	 	ArrayList <Long> javaStringTimes = new ArrayList<>();
	   //hashing both built-in and my own hashtable class at the same time
		try
		{
			//read from file using bufferedreader
			String line = null;
			File file = new File("/Users/ankitakhatri/Documents/CS245/Project4/10-million-combos.txt");
	    	BufferedReader br = new BufferedReader(new FileReader(file));
	 		int count = 0;
	    	while ((line=br.readLine())!=null && count<=1000001)
	    	{	
	    		//split by whitespace, so first string is the username and second is the password
	    		String [] values = line.split("\\s+");
	    		
	    		//only store a username and password if both the username and password exist
	    		if (values.length==2)
	    		{
	    			String username = values[0];
	    			String password = values[1];
	    			//put the username and password into the hashtable as key,value
	    			//my hashtable, and java hashtable
	    			myStringHashTable.put(username, password);
	    			javaStringHashMap.put(username, password);
	    			count++;

	    			//get lookup times for both hashtables
	    			//code to get lookup time for string for my hashtable
	    			long mystrstart = System.currentTimeMillis();
					Object mystrv = myStringHashTable.get(username);
					long mystrend = System.currentTimeMillis();
					myTime+=(mystrend-mystrstart);

					//code for lookup time for java hashtable
					long javastrstart=System.currentTimeMillis();
					Object javastrv = javaStringHashMap.get(username);
					long javastrend=System.currentTimeMillis();
					javaTime+=(javastrend-javastrstart);

					if (count==1 || count==250000 || count==500000 || count==750000 || count == 990000)
					{
						myStringTimes.add(myTime);
						javaStringTimes.add(javaTime);
					}
	    		}
	    	}
	    	br.close();
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}

    	//System.out.println("Lookup Times for 1 Million Values");
    	//System.out.println();
    	//System.out.println("Total Time for My String Lookups: " + myTime);
    	//System.out.println("Total Time for Java String Lookups: " + javaTime);

		//code to generate random integers and hash into my own hashtable
		//hash same numbers into a java hashtable
		Random random = new Random();
        HashTable myIntHashTable = new HashTable(100);
        HashMap<Integer, Integer> javaIntHashMap = new HashMap<>();

        for (int i = 0; i<1000000; i++)
        {
        	int randomKey = random.nextInt(Integer.MAX_VALUE) + Integer.MIN_VALUE;
        	int randomValue = random.nextInt(Integer.MAX_VALUE) + Integer.MIN_VALUE;
        	myIntHashTable.put(randomKey, randomValue);
        	javaIntHashMap.put(randomKey, randomValue);
        }

        //code to get total lookup time of the integer hashtable with my hashtable class and built in at the same time
        //arraylist to hold the benchmarks
        ArrayList <Long> myIntTimes = new ArrayList<>();
        ArrayList <Long> javaIntTimes = new ArrayList<>();
       	long myTotalTime=0;
       	long javaTotalTime=0;
        for (int i = 0; i<=1000001; i++)
        {
        	//get random key to lookup
        	int randomKey = random.nextInt(Integer.MAX_VALUE) + Integer.MIN_VALUE;
        	//get start time
        	long intstart = System.currentTimeMillis();
        	//lookup random key
        	Object intmv = myIntHashTable.get(randomKey);
        	long intend = System.currentTimeMillis();
        	//get total time
        	myTotalTime+=(intend-intstart);
        	//get start time
        	long jintstart = System.currentTimeMillis();
        	//lookup random key
        	Object intjv = javaIntHashMap.get(randomKey);
        	long jintend = System.currentTimeMillis();
        	//get total time
        	javaTotalTime+=(jintend-jintstart);
        	if (i==1 || i==250000 || i==500000 || i==750000 || i==990000)
        	{
        		myIntTimes.add(myTotalTime);
        		javaIntTimes.add(javaTotalTime);
        	}
        }

        //write to 2 csv's to create graphs

        //string graph
        try(FileWriter fw = new FileWriter("StringGraph.csv"))
        {
        	fw.append("Size,MyHashTable,JavaHashTable");
        	fw.append("\n");
        	fw.append("0,"+myStringTimes.get(0)+","+javaStringTimes.get(0));
        	fw.append("\n");
        	fw.append("250000," + myStringTimes.get(1) + "," + javaStringTimes.get(1));
        	fw.append("\n");
        	fw.append("500000," + myStringTimes.get(2) + "," + javaStringTimes.get(2));   
        	fw.append("\n");
        	fw.append("750000," + myStringTimes.get(3) + "," + javaStringTimes.get(3));
        	fw.append("\n");
        	fw.append("1000000," + myStringTimes.get(4) + "," + javaStringTimes.get(4));
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }

        //integer graph
        try(FileWriter fw = new FileWriter("IntegerGraph.csv"))
        {
        	fw.append("Size,MyHashTable,JavaHashTable");
        	fw.append("\n");
        	fw.append("0,"+myIntTimes.get(0)+","+javaIntTimes.get(0));
        	fw.append("\n");
        	fw.append("250000," + myIntTimes.get(1) + "," + javaIntTimes.get(1));
        	fw.append("\n");
        	fw.append("500000," + myIntTimes.get(2) + "," + javaIntTimes.get(2));   
        	fw.append("\n");
        	fw.append("750000," + myIntTimes.get(3) + "," + javaIntTimes.get(3));
        	fw.append("\n");
        	fw.append("1000000," + myIntTimes.get(4) + "," + javaIntTimes.get(4));
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
	}
}