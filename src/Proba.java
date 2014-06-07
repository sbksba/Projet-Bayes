import java.io.*;
import java.util.Scanner;
import java.lang.*;
import java.math.*;
import java.util.Hashtable;
import java.util.Iterator;

public class Proba
{
    public static void main (String[] args) throws IOException
    {
	int size = args.length;
		
	if (size == 2)
	    System.out.println(Proba(args[0],args[1]));
	else if (size == 1)
	    {
		System.out.println(Proba(args[0]));
		System.out.println("langue : "+devineLangue(args[0]));
	    }
	else if (size == 0)
	    {
		TestDefaut();
	    }
    }
    
    public static void TestDefaut()
    {
	int totalMot=0,err=0;
	String[] italian ={"fatta","ora","che","dato","volta", "abito", "paradiso", "manto", "pulce", "granito", "angolo", "ostrica", "strada", "segno", "colore","subacqueo","schermo","testa","pavone","pane","scimmia","ciambella","cuscino","generatore","onda","smeraldo","fiocco", "crateri", "dito", "arbitrio", "caldo", "cemento"};
	String[] english ={"by","other","mean","statistics","thanks","potatoes","briefcase", "university", "sheet", "pocket", "dolphin", "band", "loft", "scar", "surgeon", "war", "scales", "gown", "paradise", "blanket", "flea", "granite", "angle", "oyster", "road", "sign","colour", "diver", "screen", "chime", "headache", "laser", "peacock", "bread", "monkey", "doughnut", "pillow", "generator", "wave", "emerald", "flake", "crater",   "finger",   "freewill",   "hottest"};
	String[] dutch ={"daar", "ander", "laken", "litteken", "aardappelen", "jurk", "deken", "vlooien", "graniet", "hoek", "oester", "weg", "teken", "kleur", "schotel", "duiker","scherm","bel","hoofdpijn","pauw","brood","aap","kussen","smaragd","vlok", "krater", "vinger", "vrije", "heetste"};
	String[] french ={"pomme","constitutionnellement","chocolat", "autre", "drap","bateau", "paradis", "couverture", "puces", "huitre", "signe", "couleur", "soucoupe", "plongeur", "ecran", "carillon","president", "maux", "paon", "pain", "singe", "beignet", "oreiller", "generateur", "vague", "emeraude", "flocon", "cratere", "doigt", "arbitre", "chaud", "ciment"};

	String result="";
	
	System.out.println("Test : italian\n---------------");
	for (int i=0;i<italian.length;i++)
	    {
	
		result = devineLangue(italian[i]);

	           
		   if (result !="italian") {
		       err++;
		       result += " X";
		   }
		   System.out.println("Mot :"+italian[i]+", langue : "+result);
		   totalMot++;
	    }
	System.out.println("\nTest : english\n---------------");

	for (int i=0;i<english.length;i++)
	    {
	
		result = devineLangue(english[i]);
	
		
		if (result!="english") {err++; result+=" X";}
		System.out.println("Mot :"+english[i]+", langue : "+ result);
		totalMot++;
	    }
	System.out.println("\nTest : dutch\n-------------");
	for (int i=0;i<dutch.length;i++)
	    {
		
		result = devineLangue(dutch[i]);
		
		if (result!="dutch") {err++;result+=" X";}
		System.out.println("Mot :"+dutch[i]+", langue : "+ result);
		totalMot++;
	    }
	System.out.println("\nTest : french\n--------------");
	for (int i=0;i<french.length;i++)
	    {
		
		result = devineLangue(french[i]);
		
		if (result!="french") {err++;result+=" X";}
		System.out.println("Mot :"+french[i]+", langue : "+ result);
		totalMot++;
	    }
	System.out.println("\n TotalMot : "+totalMot+", Nombre erreurs : "+err);
	System.out.println("Pourcentage de réussite : "+(1.0-((double)err/(double)totalMot)));
    }


    public static double Proba(String mot, String langue){
	int n=2;
	return Proba(mot, "corpus","corpus/"+langue+"."+ n + "g", n);

	
    }

    /*Rend la probabilite d un mot sachant un langue*/
    public static double Proba(String mot, String corpus, String file, int n)
    {
	Hashtable<String, Integer> myTable = new Hashtable<String,Integer>();
	
	int i;
	int total=0;
	Scanner sc = null;
	String bigram="";
	int count;
	String mot2 = mot+"$";
	char [] word = mot2.toCharArray();


	try {
	    try {
		sc = new Scanner(new File(file));

		
		while (true) 
		    {
			bigram = sc.next();
			if (bigram.equals("TOTAL")) break;
			count = sc.nextInt();
		        //System.out.println(bigram + " " + count);
			myTable.put(bigram, count);
		    }
		total = sc.nextInt();
	    }
	    finally 
		{
		    if (sc != null)
			sc.close();
		}
	    
	} catch (FileNotFoundException e) 
	    {
		e.printStackTrace();
	    }
	
	int cnt=0;
	double p = 1;
	String tmp = "";
	String s = null;
	double pa =0,pb=0,pab=0;
	int l = mot2.length();

	double [] t1 = new double[l];
	double [] t2 = new double[l];

	for (i=0; i<l; i++) {t1[i]=0;t2[i]=0;}

	if (l > 1) {
	    /*P(A) = somme P(A|B) pour tous les Bi / nombre total de lettre 
	      (=nb bigrammes) */

	    s = new StringBuilder().append(word[0]).toString();
	    if (myTable.get(s)!= null) { 
		pa = (double)myTable.get(s);
		}
	    else {pa =0;incrementall(myTable, s, t1,t2);}
	    

	    t1[0]+=pa;
	    t2[0]+=total;
	    
	    if (n==1) {
		for (i=1; i < l-1 ; i++){
		   	s = new StringBuilder().append(word[i]).toString();

			if (myTable.get(s)!=null) pb=(double)myTable.get(s); 
			else { 
			    pb=1.0;
			    incrementall(myTable,s,t1,t2);}
			//	System.out.println("P("+s+") ="+pb);
			t1[i]+=pb;
			t2[i]+=total;
		}
		t1[l-1]=1;t2[l-1]=1;
	    }
	    else if (n==2){
		for (i=0; i < l-2; i++)
		    {
			
			s = new StringBuilder().append(word[i]).toString();
			if (myTable.get(s)!=null) pb=(double)myTable.get(s); 
			else pb=1.0;
			t2[i+1]+=pb;
			
			s = new StringBuilder().append(word[i]).append(word[i+1]).toString();
			
			/* P(B|A) = P(A,B)/P(B)*/
			if (myTable.get(s)!=null) {
			    pab =myTable.get(s); 
			}
			else {
			    pab=0;
			    incrementall(myTable,s,t1,t2);
			}
			t1[i+1]+=pab;
	
		    }
		t1[i+1] = pab*0.6 + t1[i+1]*0.4;
		t2[i+1] = pb*0.6 + t2[i+1]*0.4;	 
		s = new StringBuilder().append(word[i]).toString();
	 
	  
	 if (myTable.get(s)!=null) {
	     pb =myTable.get(s);
	 }
	 else {
		pb=0;
		incrementall(myTable,s,t1,t2);
	 }
	 
	 s = new StringBuilder().append(word[i]).append(word[i+1]).toString();
	 if (myTable.get(s)!=null) {
	     pab =myTable.get(s); 
	 }
	 else {
		pab=0;
		incrementall(myTable,s,t1,t2);
	 }
	 t1[i+1]+=pab;
	 t2[i+1]+=pb;

	    }
	    	 
	 for (i=0; i<l; i++)   { 
	     p*= t1[i]/t2[i];
	 }
	 return (p);
	}
	else return 0;
			   
    }
    
    public static double Proba(String mot)
    {   
	
	return Proba(mot, "corpusTotal");
    }

    
    /* Devine la langue en prenant en compte p(l), probabilite a priori d'une langue */
    public static String devineLangue (String mot)
    {
	int pen = 58293;
	int pfr = 58481;
        int pdu = 54423;
	int pit = 59629;
	

	double eng = Proba(mot,"english") * pen;
	double fr  = Proba(mot,"french") * pfr;
	double du  = Proba(mot,"dutch") * pdu;
	double it  = Proba(mot,"italian") * pit;
	
	if (eng > fr && eng>du && eng >it) return "english";
	if (fr > eng && fr>du && fr>it) return "french";  
	if (du> eng && du>fr && du>it) return "dutch";  
	if (it > eng && it>fr && it>du) return "italian";  
	else
	    return "connait pas";
    }

    /* fonction appelee par Proba lorsqu'on cherche la probabilite d'une lettre ou bigram qui n'est pas dans le corpus */
    /* elle ajoute le nouveau avec un compte de 1 (pour evite une probabilite de mot a 0) et augmente de 1 tous les comptes */

    public static void incrementall(Hashtable <String, Integer> t, String nw, double[]t1, double[]t2){

	t.put(nw,1); 
	Iterator it = t.keySet().iterator();
	
	while (it.hasNext()){
	    String s = (String)it.next();
	    Integer prevValue = t.get(s);
	    t.put(s, prevValue + 1);
	}
	for (int i=0; i<t1.length; i++){
	    t1[i]++;
	    t2[i]++;
	}
    }
}
