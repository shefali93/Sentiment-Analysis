package com.Model;
import java.util.Arrays;
import edu.smu.tspell.wordnet.AdjectiveSynset;
import edu.smu.tspell.wordnet.AdverbSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;
import edu.smu.tspell.wordnet.WordSense;
import rita.wordnet.RiWordnet;

public class Reverse_sentiment 
{
	
	public static boolean useList(String[] arr, String targetValue) 
	{
		return Arrays.asList(arr).contains(targetValue);
	}

	String reverse(String tweettobereversed) 
	{

		
	
		
		String[] result = tweettobereversed.split("\\s");
		String ans ="";
		int flag=0;
		
		int k;
		System.setProperty("wordnet.database.dir","C:\\Program Files (x86)\\WordNet\\2.1\\dict");
		AdjectiveSynset adjSynset; 
		AdjectiveSynset[] s = null; 
		WordNetDatabase database = WordNetDatabase.getFileInstance();
		RiWordnet wordnet = new RiWordnet(null);
		
			
		
		
		//******************************************************stem********************************************************
		
		for(k =0;k<result.length; k++)//awesome,movie
		{
			
			String[] partsofspeech = wordnet.getPos(result[k]);
			if(partsofspeech.length!=0)
			{
					boolean adj=useList(partsofspeech,"a");
					if(!adj)
					{
						Synset[] nounSynset=database.getSynsets(result[k],SynsetType.NOUN);
						if(nounSynset.length==0)
						{
							Synset[] advSynset=database.getSynsets(result[k],SynsetType.ADVERB);
							if(advSynset.length!=0)
							{
								WordSense[] wadv=((AdverbSynset) advSynset[0]).getPertainyms(result[k]);
								if(wadv.length!=0)
									result[k]=wadv[0].getWordForm();
							}
							
						}
						else
						{
							WordSense[] wn=nounSynset[0].getDerivationallyRelatedForms(result[k]);
							if(wn.length!=0)
								result[k]=wn[0].getWordForm();
						}
						
						
						
						
							
					}
			}
			
		}
		
		
		
		
		
		
		
		
		//***********************************************reverse**************************************************************
		
		for(k =0;k<result.length; k++)//awesome,movie
		{
		if(result[k].length()==1 || result[k].equalsIgnoreCase("in"))
		{
			ans+=result[k]+" ";
			continue;
		}
		
		try
		{
			if(result[k-1].equals("not") || result[k-1].equals("don't"))
		
			
		{
			ans = ans.replace(result[k-1], "");
			ans+= result[k]+" "; 
			continue;
					
		}
		}
		catch(Exception e)
		{
			
		}
			
		
		Synset[] synsets = database.getSynsets(result[k]);
		if(synsets.length!=0)											//word exists in wordnet
		{
			
			
			for(Synset synset : synsets)								//check if there is a direct antonym
			{
				WordSense[] ws = synset.getAntonyms(result[k]);
				if(ws.length==0)										//no direct, so check if there is indirect antonym
				{
									
						SynsetType type = synset.getType();
						if (type.equals(SynsetType.ADJECTIVE_SATELLITE)) 	//if synset of this type
						{
								flag=1;
								adjSynset = (AdjectiveSynset)(synset);
								s=adjSynset.getSimilar();	
								if(s.length!=0)							//check if no indirect antonym
								{
									    String[] l=s[0].getWordForms();
									    WordSense[] wordsenses = s[0].getAntonyms(l[0]);
										ans+=wordsenses[0].getWordForm()+" ";
										break;
									
								}
								else
								{
									ans+=result[k]+" ";
									flag=1;
									break;
								}
						}//if type is adj or satellite
						
							
				}//if no direct antonym
				else
				{
					ans+=ws[0].getWordForm()+" ";													//add  direct antonym
					flag=1;
					break;
					
				}
			
			
		 }//for each synset
			
			if(flag==0)
				ans+=result[k]+" ";
			
	 }//check if word exists in wordnet
		else
			ans+=result[k]+" ";
		flag=0;
		
		
	}//result	
		
return ans;
		

	
	}
}

