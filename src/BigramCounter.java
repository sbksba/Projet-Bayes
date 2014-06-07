import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.lang.*;
import javax.swing.JTextArea;

public class BigramCounter {

    private Hashtable<String, Integer> myTable = new Hashtable<String,Integer>();
    public BigramCounter(){
	    Hashtable<String, Integer> myTable = new Hashtable<String,Integer>();
    }
    public void count(String str, int n) {
	Integer prevValue;
	//myTable.clear();

	//Get rid of all non-alpha characters (except spaces)
	String alphaString = str/*.replaceAll("[^A-Za-z]", "")*/;

	//Split the text into words
	StringTokenizer st = new StringTokenizer(alphaString);
	    int l;
	    //System.out.println("n="+ n);
	//for each word

	while(st.hasMoreTokens()){
	    String currentWord = st.nextToken()/*.toUpperCase()*/;
	    String currentBigram="";
		
		l=currentWord.length();
		if (n==2){ 
		    //System.out.println("$");
		    if (myTable.get("$")!=null) {
			prevValue = myTable.get("$");
			myTable.put("$", prevValue + 1);}
		    else myTable.put("$", new Integer(1));

		}
		//System.out.println(currentWord + "     " +  l);
		for(int i = 0; i<l; i++){
		    
		    // System.out.println(currentBigram);
		//if there are at least 2 characters in the current word
		
		if(currentWord.length() >= (i+n)){
		    //separate 2-letter bigram
		    currentBigram = currentWord.substring(i, i+n);
		    //System.out.println(" in if : " + currentBigram);
		}
		else{ //only 1 character left in current word
		    //separate 1-letter bigram
       
		    currentBigram = currentWord.substring(i, l)+"$";
			//System.out.println(" in else : " + currentBigram);
	  
		}
		//If bigram has been seen, add 1 to value
		if(myTable.containsKey(currentBigram)){
		    prevValue = myTable.get(currentBigram);
		    myTable.put(currentBigram, prevValue + 1);
		
		}
		//else create new bigram entry in table
		else{
		    myTable.put(currentBigram, new Integer(1));
	
			}
	    }
		
	}
	if (n>1)   count (str, n-1);
    }
    /* argument = degree of ngram */

	public String prt(int n) {
	    int totalSum = 0;
	    String ta = "";

	    //Grab all of the bigram and put them in a list
	    ArrayList<String> keys = new ArrayList<String>(myTable.keySet());
	    //Sort the list of bigrams
	    Collections.sort(keys);
	    //Go through list of keys
	    for(String curBigram : keys){
		totalSum += myTable.get(curBigram); //Update total count
		//Print bigram and count value
		ta += curBigram + "\t" + (Integer)myTable.get(curBigram) + "\n";
	    }
	    if (n>1) totalSum = (totalSum - (Integer)myTable.get("$"))/n;
	    ta += "TOTAL " + totalSum ;
	    return ta;
	}

}
