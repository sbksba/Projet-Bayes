import java.util.Scanner;
import java.lang.*;

public class Projet
{
    public static void main(String[] args)
    {
	Scanner sc = new Scanner(System.in);
	Scanner mo = new Scanner(System.in);
	Scanner lang = new Scanner(System.in);
	Scanner rep = new Scanner(System.in);
	char reponse=' ', mode=' ', choix=' ';
	
	do{
	    do{                                                                                                                       
                mode = ' ';
		Banniere();
                System.out.println("Choisissez l'action que vous-voulez faire : ");
                System.out.println("1 - Creer les fichiers de probabilite");
                System.out.println("2 - Tester le programme");
		System.out.println("3 - Quitter\n");
                mode = sc.nextLine().charAt(0);
		
		if (mode == '3') {System.exit(0);}
		
                if(mode != '1' && mode != '2')
                    System.out.println("Mode inconnu, veuillez recommencer.");
		
            }while (mode != '1' && mode != '2');
	    
	    if(mode == '1')
		{
		    Banniere();
		    System.out.println("Creation des fichiers\n");
		    System.out.println("Entrer le nom du fichier corpus : ");
		    String mot = mo.nextLine();
		    System.out.println("Entrer le nom du fichier destination : ");
		    String langue = lang.nextLine();
		    String[] t = {mot,langue};
		    ReadTextFile.read(t);
		}
            else
		{
		    do{
			choix = ' ';
			Banniere();
			System.out.println("Choisissez l'action que vous-voulez faire : ");
			System.out.println("1 - Tester un mot sachant la langue");
			System.out.println("2 - Tester un mot");
			System.out.println("3 - Effetuer les tests par defaut");
			System.out.println("4 - Revenir au menu precedent\n");
			choix = sc.nextLine().charAt(0);
			
			if (choix == '4') {break;}
			
			if(choix != '1' && mode != '2' && choix != '3')
			    System.out.println("Choix inconnu, veuillez recommencer.");
			
		    }while (choix != '1' && mode != '2' && choix != '3');
		    
		    if(choix == '1')
			{
			    System.out.println("Entrer un mot : ");
			    String mot = mo.nextLine();
			    System.out.println("Entrer une langue : (english,french,dutch,italian) ");
			    String langue = lang.nextLine();

			    System.out.println(Proba.Proba(mot,langue));
			}
		    else if (choix == '2') 
			{
			    System.out.println("Entrer un mot : ");
			    String mot = mo.nextLine();

			    System.out.println(Proba.Proba(mot));
			    System.out.println("langue : "+Proba.devineLangue(mot));
			}   
		    else if (choix == '3') 
			{
			    Proba.TestDefaut();
			}
		}
                                                                                                   
            do{
                System.out.println("\nVoulez-vous continuer ?(o/n)");
                reponse = sc.nextLine().charAt(0);

            }while(reponse != 'o' && reponse != 'n');

        }while(reponse == 'o');

        System.out.println("Au revoir !");

    }

    public static void Banniere()
    {
	for (int i=0; i<60;++i)
	    System.out.println();
	System.out.println("---------------------------------------------");
	System.out.println("|   PROJET 1 LI323 : DETECTION DE LANGUES   |");
        System.out.println("---------------------------------------------\n");
    }
}
