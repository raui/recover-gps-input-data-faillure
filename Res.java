package projects.mesf;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;
import java.net.*;
public class Res{
public static File file;

public  void findFiles(String directoryPath) {
		File directory = new File(directoryPath);
		if(!directory.exists()){
			System.out.println("Le fichier/répertoire '"+directoryPath+"' n'existe pas");
		}else if(!directory.isDirectory()){
			System.out.println("Le chemin '"+directoryPath+"' correspond à un fichier et non à un répertoire");
		}else{
			File[] subfiles = directory.listFiles();
			String message = "Le répertoire '"+directoryPath+"' contient "+ subfiles.length+" fichier"+(subfiles.length>1?"s":"");
			System.out.println(message);
			for(int i=0 ; i<subfiles.length; i++){
				System.out.println(subfiles[i].getName());
			}
		}
	}
public static void main(String args[]){  
try{
   System.out.println("Enter the path of the file: \n");
    BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
    String path = buf.readLine();
    file = new File(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
  try {
     BufferedReader br = new BufferedReader(new FileReader(file));
     String line;
	 int i = 0;
	 int j = 0;
     while ((line = br.readLine()) != null) {
	   if (line.contains("cgi-bin/g?")) { 
	   String newline = fromquery(line);
	   requete(newline);	   
	   } 
	    j++;
	   System.out.println("Lines Readed :"+(j));
	   i++;
      }
      br.close();
	  System.out.println("Total Readed :"+ (i++));
      } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
}
  public static String fromquery(String line){
   int start = line.lastIndexOf("?");
   int fin = line.lastIndexOf(".1"); 
   String newStr = line.substring(start+1, fin+1);     
  return  newStr+"1";
 }
 public static void requete(String reqstline){
   String urlS = "your-domain-url?"+ reqstline;
    
     try{
	     URL url = new URL(urlS);
		 URLConnection connection = url.openConnection();
		 BufferedReader in = new BufferedReader(
         new InputStreamReader(connection.getInputStream()));
         String urlString = "";
         String current;
		 
         while((current = in.readLine()) != null)
         {
          urlString += current;
         }
         System.out.println("Restoration status: "+urlString);		 		 
	  } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
}
