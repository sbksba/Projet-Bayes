import java.io.*;
import java.util.Scanner;
import java.lang.*;
 
public class ReadTextFile
{
    public static void main (String[] args) throws IOException
    {
	read(args);
    }
    public static void read(String[] args) {
     /* n = valeur du degré de ngram qu'on veut */
	int n=2;
	BigramCounter B = new BigramCounter(); 


	System.out.println("File : "+args[0]+"\n");
	PrintWriter outPut = null;
	
	try
	    {
		outPut = new PrintWriter(new FileOutputStream(args[1]));
	    }
	catch(FileNotFoundException e)
	    {    System.out.println("Erreur d’ouverture "+args[1]);
		System.exit(0);
	    }

	
	Scanner sc = null;
        try {
            try {
                sc = new Scanner(new File(args[0]));
                while (sc.hasNextLine()) 
		    {
			
			String s = sc.nextLine();
			//System.out.println(s+"\n\n");
			B.count(s, n);
			
				
		    }
            } finally 
		{
		    if (sc != null)
			sc.close();
		}
	    
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


	outPut.print(B.prt(n));
	outPut.close();
    }
}
