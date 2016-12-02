import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;


public class ReaderTXT {
	

	public static void main(String[] args) throws Exception {	
	
		String filename1 = "C:/Users/CBCXCOM-KG.CBC-XCOM-1/Downloads/Daten/IntraDayData/dax_201109";  
		
		String filename;	
		String sFileResult1 =  "C:/Users/CBCXCOM-KG.CBC-XCOM-1/Downloads/Daten/IntradayMin/dax_201109";
		String sFileResult ;
		
		
		
			
				
		for (int i = 1; i < 32; i++) {
			try {
			
			 String day = String.valueOf(i);
		
		if (i<10) {
			filename = filename1 +"0" + day +".txt";
		     sFileResult = sFileResult1+"0" + day +".txt";
		}
		else{
			filename = filename1 + day +".txt";
			sFileResult = sFileResult1 + day +".txt";
		}	
		 	
	    PrintWriter writer = new PrintWriter(new FileWriter(sFileResult));	 
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		TreeMap<String, Double > contain = new TreeMap<String, Double>() ;
		
		Date dTimeNow = new Date();  
		long lTimeNow;
		
		Date dTimeMinute = new Date();
		Date lastTimeMinute = new Date();
		lastTimeMinute= null;
		
		long lTimeMinute;	
	
		Double lastMinPrice;
		
		
		String binTime ;
		
		  String line =null;
          String data = null;
          String sRic = null;
		
          
          String[] temp;
          
          Event test = new Event();
          SimpleDateFormat sdfToDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss,SSS");
          
		   while((line = reader.readLine()) != null ){
              
			   temp = line.split(";");
			   test.sDate = temp[1];
			   test.sTime = temp[2];
			   String newTagesdatum = test.sDate+ " " + test.sTime; //.substring(0,8);
			  
			   int  minute = Integer.parseInt(test.sTime.substring(3,5)) +1;
			   String newTagesdatum2 = test.sDate+ " " + test.sTime.substring(0,3) + String.valueOf(minute) +":00,000" ;
			  
			   binTime = test.sTime.substring(0,5);
			   
			   try {
		            dTimeNow = sdfToDate.parse(newTagesdatum);
		            lTimeNow = dTimeNow.getTime();
		            dTimeMinute = sdfToDate.parse(newTagesdatum2);
		            lTimeMinute =dTimeMinute.getTime();
		            
		        } catch (ParseException ex2) {
		            ex2.printStackTrace();
		        }
		        try {
		        	test.lastPrice = Double.parseDouble(temp[4]);
				} catch (Exception e) {
					// TODO: handle exception
				}
			    
				   
			   
			  if( lastTimeMinute==null){
				  lastTimeMinute =  dTimeMinute;
			  }  
			    
			  if(lastTimeMinute.getTime()-1< dTimeMinute.getTime() ){
				  dTimeMinute=null;
				  contain.put(binTime, test.lastPrice);
				  // System.out.println(binTime +  " " + test.lastPrice);	
			  }
			   
			//  System.out.println(test.sDate + " " + test.sTime +  " " + test.lastPrice);	
			   
		}
		   
		
		   
		 for(String sBinTime : contain.keySet() ){
			 
			 writer.println( sBinTime +  " " + contain.get(sBinTime));   
			 
			// System.out.println(sBinTime +  " " + contain.get(sBinTime));
			 
		 }  
		   writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
   
		} //end of for loop
		
		
			   		
	}	 
}
		   
