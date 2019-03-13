package app;

import java.io.*;

public class Start {
    public static void main(String[] args) {
        FileInputStream fis = null; //le programme récupère le fichier exterieur en flux d'octets, objet fis = ensemble de flux d'octets
        FileOutputStream fos = null; //le programme expulse le flux d'octets

        try {
            fis = new FileInputStream(new File(".\\src\\test.txt")); //Lire en recuperant en flux d'octets
            fos = new FileOutputStream(new File(".\\src\\test2.txt")); //Sortir sous forme de flux d'octets

            //crée un tableau de byte pour indiquer le nombre de bytes lus à chaque tour de boucle
            byte[] buf = new byte[8];

            //variable représentant resultat lecture : 0 = ok, -1 = fini
            int n = 0;

            // Tant que l'affectation dans la variable est possible, on boucle
            // Lorsque la lecture du fichier est terminée l'affectation n'est
            // plus possible !
            // On sort donc de la boucle
            while ((n = fis.read(buf)) >= 0) { //Lis 8 octets de l'objet fis qui as le fichier sous forme de paquets d'octets
                /*IMPORTANT fis.read fait 2 choses
                    1. retour à n la valeur 0 si il reste encore des octets dans le flux non lus, -1 si il n'y en a plus
                    2. donne aux 8 octets composant le tableau buf la valeur issue de la lecture
                    */
                fos.write(buf);

                for (byte bit : buf){
                    System.out.print("\t" + bit + "(" + (char) bit + ")");
                }
                System.out.println();
                //Nous réinitialisons le buffer à vide
                //au cas où les derniers byte lus ne soient pas un multiple de 8
                //Ceci permet d'avoir un buffer vierge à chaque lecture et ne pas avoir de doublon en fin de fichier
                buf = new byte[8];
            }

            System.out.println("fin");
        } catch (FileNotFoundException e){ //l'objet fileInputStream ne trouve pas le fichier
            e.printStackTrace();
        } catch (IOException e) { //erreur d'ecriture ou de lecture
            e.printStackTrace();
        } finally {
            //fermer ici les flux pour que dans tous les cas meme en cas d'excpetion levée les flux soient fermés
            try{
                if(fis != null) //si l'objet fis est crée et contient bien un fichier en flux d'octets
                    fis.close();
            } catch (IOException e){ //en cas d'erreur de lecture ecriture, meme en simple cas de fermeture du fichier
                e.printStackTrace();
            }

            //fermer egalement le flux d'octets pour la sortie
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
