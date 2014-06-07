import java.io.*;
import java.util.Scanner;
import java.lang.*;
import java.math.*;

public class Proba
{
    public static void main (String[] args) throws IOException
    {
	int size = args.length;
		
	if (size == 3)
	    System.out.println(Proba(args[0],args[1],args[2]));
	else if (size == 2)
	    {
		System.out.println(Proba(args[0],args[1]));
		System.out.println("langue : "+devineLangue2(args[0], args[1]));
	    }
	else if (size == 1)
	    {
		TestDefaut(args[0]);
	    }
    }
    
    public static void TestDefaut(String repertoire)
    {
	int totalMot=0,err=0;
	String[] italian ={"fatta","ora","che","dato","volta"};
	String[] english ={"by","other","mean","statistics","chocolate","president","thanks","potatoes","peter","briefcase", "university", "sheet", "pocket", "dolphin", "band", "loft", "scar", "surgeon", "war", "scales"};
	String[] dutch ={"daar", "ander", "laken", "litteken", "aardappelen"};
	String[] french ={"pomme","constitutionnellement", "autre", "drap", "cicatrice","mallette"};
	String result="";
	
	System.out.println("Test : italian\n---------------");
	for (int i=0;i<italian.length;i++)
	    {
		//result = devineLangue(italian[i], repertoire);
		result = devineLangue2(italian[i], repertoire);
		System.out.println("Mot :"+italian[i]+", langue : "+result);
		if (result != "italian") {err++;}
		totalMot++;
	    }
	System.out.println("\nTest : english\n---------------");
	for (int i=0;i<english.length;i++)
	    {
		//	result = devineLangue(english[i],repertoire);
		result = devineLangue2(english[i],repertoire);
		System.out.println("Mot :"+english[i]+", langue : "+ result);
		if (result != "english") {err++;}
		totalMot++;
	    }
	System.out.println("\nTest : dutch\n-------------");
	for (int i=0;i<dutch.length;i++)
	    {
		//result = devineLangue(dutch[i],repertoire);
		result = devineLangue2(dutch[i],repertoire);
		System.out.println("Mot :"+dutch[i]+", langue : "+ result);
		if (result != "dutch") {err++;}
		totalMot++;
	    }
	System.out.println("\nTest : french\n--------------");
	for (int i=0;i<french.length;i++)
	    {
		//result = devineLangue(french[i],repertoire);
		result = devineLangue2(french[i],repertoire);
		System.out.println("Mot :"+french[i]+", langue : "+ result);
		if (result != "french") {err++;}
		totalMot++;
	    }
	System.out.println("\n TotalMot : "+totalMot+", Nombre erreur : "+err);
	System.out.println("Pourcentage de réussite : "+(1.0-((double)err/(double)totalMot)));
    }

    /*Rend la probabilite d un mot sachant un langue*/
    public static double Proba(String mot, String langue, String corpus)
    {    
	double probas[]= new double[26]; 
	int i=0; 
	int total=0;
	Scanner sc = null;
	
	try {
	    try {
		sc = new Scanner(new File(corpus+"/"+langue+".cnt.1g"));
		total = Integer.parseInt(sc.next());
		while (sc.hasNextLine() && i<26) 
		    {
			probas[i] = Double.parseDouble(sc.next());
			//System.out.println("proba : "+probas[i]);
			i++;
		    }
	    }
	    finally 
		{
		    if (sc != null)
			sc.close();
		}
	    
	} catch (FileNotFoundException e) 
	    {
		//TODO Auto-generated catch block
		e.printStackTrace();
	    }
	
	double p=0; // p=1 
	for (char c : mot.toCharArray())
	    {
		//p *= probas[c-'a'];
		p += Math.log(probas[c-'a']/total);
	    }	
	return Math.exp(p);
    }
    
    public static double Proba(String mot,String repertoire)
    {    
	double probas[]= new double[26]; 
	int i=0; 
	Scanner sc = null;
	
	try {
	    try {
		sc = new Scanner(new File(repertoire+"/corpusTotal.cnt.1g"));
		while (sc.hasNextLine() && i<26) 
		    {
			probas[i] = Double.parseDouble(sc.next());
			//System.out.println("proba : "+probas[i]);
			i++;
		    }
	    }
	    finally 
		{
		    if (sc != null)
			sc.close();
		}
	    
	} catch (FileNotFoundException e) 
	    {
		//TODO Auto-generated catch block
		e.printStackTrace();
	    }
	
	double p=0; // p=1
	for (char c : mot.toCharArray())
	    {
		//p *= probas[c-'a'];
		p += Math.log(probas[c-'a']);
	    }	
	return Math.exp(p);
    }

    /* devine la langue du mot en supposant p(l) uniforme) */
    public static String devineLangue (String mot, String corpus)
    {
	
	double eng = Proba(mot,"english", corpus);
	double fr  = Proba(mot,"french", corpus);
	double du  = Proba(mot,"dutch", corpus);
	double it  = Proba(mot,"italian", corpus);
	//System.out.println("EN : "+ eng + " FR: "+ fr + " DU: "+ du + " IT : "+it );
	
	if (eng > fr && eng>du && eng >it) return "english";
	if (fr > eng && fr>du && fr>it) return "french";  
	if (du> eng && du>fr && du>it) return "dutch";  
	if (it > eng && it>fr && it>du) return "italian";  
	else
	    return "connait pas";
    }

    /* Devine la langue en prenant en compte p(l), probabilite a priori d'une langue */
    public static String devineLangue2 (String mot, String corpus)
    {
	int pen = 4344510;
	int pfr = 1433296; 
        int pdu = 1698960;
	int pit = 1068553;
	

	double eng = Proba(mot,"english", corpus) * pen;
	double fr  = Proba(mot,"french", corpus) * pfr;
	double du  = Proba(mot,"dutch", corpus) * pdu;
	double it  = Proba(mot,"italian", corpus) * pit;
	
	if (eng > fr && eng>du && eng >it) return "english";
	if (fr > eng && fr>du && fr>it) return "french";  
	if (du> eng && du>fr && du>it) return "dutch";  
	if (it > eng && it>fr && it>du) return "italian";  
	else
	    return "connait pas";
    }
}
