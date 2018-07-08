package com.Model;

import rita.wordnet.RiWordnet;
import org.json.simple.parser.ParseException;
import java.util.*;


public class Clean_The_Data 
{
	
	
	private final static String REGEX = "((www\\.[\\s]+)|(https?://[^\\s]+))";
	
	private final static String STARTS_WITH_NUMBER = "[1-9]\\s*(\\w*)";
	static RiWordnet wordnet = new RiWordnet(null);
	
	 String clean(String re,String actor,String actress,String moviename) throws ParseException
	 {
		 String tweet=""; 
		 String actorregex = actor.trim().replaceAll(" +", "");							//remove multiple spaces
		 String actressregex = actress.trim().replaceAll(" +", "");						//remove multiple spaces
		 String movieregex = moviename.trim().replaceAll(" +", "");						//remove multiple spaces
   
	/*************************************************************************HASH TAGS AND URLS***************************************************************************************/
	 
			
		 re = re.replaceAll(REGEX, "");												//remove url
		 re=re.replaceAll("[-+.^:,!?()/[/]]"," ");									//remove special characters
		 re = re.replaceAll("-", " ");												//remove -
		 re= re.replaceAll(STARTS_WITH_NUMBER, "");
		 re=re.trim().replaceAll("\\.\\.+", " ");	
		 re=re.replaceAll("[.]", " .");
		 re=re.replaceAll("[,]", " , ");
		 re = re.trim().replaceAll(" +", " ");										//remove multiple spaces
			 
		// System.out.println("After special symbols removal : \n"+re+"\n \n");
		 
		 /********************************************************************PREPOSITIONS*************************************************************************************/
		 List<String> list = new ArrayList<String>();
		 list.addAll(Arrays.asList(actor,actress,moviename,actorregex,actressregex,movieregex,"you","i","this","that","of","for","to","the","with","where","there","here","from","my","your","her","and","his"));
		 
			 String[] result = re.split("\\s");
			 
			 int length=result.length;
			 int[] inDict=new int[length];
			 for(int k =0;k<length; k++)
			 {
			 inDict[k]=1;
			 }																//initializing inDict
	
			 for(int k =0;k<length; k++)
			 {
					 //System.out.println(result[k]);
					 if(result.length>1 &&(result[k].charAt(0)=='#' || result[k].charAt(0)=='@'))
					 {
						 	result[k]=result[k].substring(1);
						
					 }
					 else
					 {
						 if(list.contains(result[k]))
						 		continue;
						 	else
						 	{
						 		String[] partsofspeech = wordnet.getPos(result[k]);														//updating inDict
						 		if(partsofspeech.length==0)
						 		{
						 			inDict[k]=0;
						 		}
						 	}
					 }//else
					
			 }//for of hash
			
	 /********************************************************************EXTENDED WORDS*************************************************************************************/
		 for(int k =0;k<length; k++)
		 {	
				 if(inDict[k]==0)
				 {
		
						 int len=result[k].length();
						 for(int i=len-1;i>=1;i--)
						 	{
							 		if(result[k].charAt(i)==result[k].charAt(i-1)) //repeated
							 		{
												String[] partsofspeech = wordnet.getPos(result[k]);
												if(partsofspeech.length==0)														//not in dictionary
												{
														result[k]=result[k].substring(0,i)+result[k].substring(i+1);
														len=result[k].length();
														String[] partsofspeech1 = wordnet.getPos(result[k]);
														if(partsofspeech1.length>0)	
														{
															inDict[k]=1;
															break;
														}
														
															
													
												}
												
										
							 		}// outer if
						 	}// inner for
				 }//end of indict if
		 }// outer for
		
	 
	 /************************************************************************STEMMING******************************************************************************************/
	 
	   tweet = Arrays.toString(result);
	   tweet=tweet.substring(1,tweet.length()-1);
	   return tweet;
	 
}//main 
}

