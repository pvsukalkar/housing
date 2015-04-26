import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Turney {
    public static void main(String args[]) throws FileNotFoundException, IOException{
        int totpos=0;
        int totneg=0;
        int totneu=0;
        File folder = new File("C:\\MAIN PROJECT\\TEXT FILES\\AFTER_POS_PSTIVE");
        File[] listOfFiles = folder.listFiles();
        for(int i=0;i< listOfFiles.length;i++){
            int poscount = 0;
            int negcount = 0;
            File file = listOfFiles[i];
            String str= file.getName();
            String str1=str;
            String[] strsp=str1.split("[.]");
            str1 = strsp[0] + ".txt";
            str = "C:\\MAIN PROJECT\\TEXT FILES\\AFTER_POS_PSTIVE\\" + str;
            String positivekey = "C:\\MAIN PROJECT\\TEXT FILES\\positivekeywords.txt";
            String negativekey = "C:\\MAIN PROJECT\\TEXT FILES\\negativekeywords.txt";
            BufferedReader review = new BufferedReader(new InputStreamReader(new FileInputStream(str),"UTF-8"));
            review.readLine();
            String line;
            ArrayList<String> reviews = new ArrayList<String>();
            while((line = review.readLine()) != null){
                reviews.add(line);
            }
            for(int j=0;j<reviews.size()-1;j++){
                String temp1=reviews.get(j);
                String temp2=reviews.get(j+1);
                int flag=0;
                if(j!=reviews.size()-2){
                    String temp3=reviews.get(j+2);
                    if(temp1.contains("JJ") && temp2.contains("NN")) flag=1;
                    else if(temp1.contains("JJ") && temp2.contains("JJ") && !temp3.contains("NN")) flag=1;
                    else if(temp1.contains("RB") && temp2.contains("JJ")&& !temp3.contains("NN")) flag=1;
                    else if(temp1.contains("RB") && temp2.contains("VM")) flag=1;
                    else if(temp1.contains("NN") && temp2.contains("JJ")&& !temp3.contains("NN")) flag=1;
                }
                else{
                    if(temp1.contains("JJ") && temp2.contains("NN")) flag=1;
                    else if(temp1.contains("JJ") && temp2.contains("JJ")) flag=1;
                    else if(temp1.contains("RB") && temp2.contains("JJ")) flag=1;
                    else if(temp1.contains("RB") && temp2.contains("VM")) flag=1;
                    else if(temp1.contains("NN") && temp2.contains("JJ")) flag=1;
                }
                if(flag==1){
                    String[] split1=temp1.split("[	]");
                    String[] split2=temp2.split("[	]");
                    System.out.println("Extracted phrases");
                    System.out.println("phrase1 : "+temp1);
                    System.out.println("phrase2 : "+temp2);
                    String word1=split1[0];System.out.println("Extracted words");
                    String word2=split2[0];
                    System.out.println("word1 : "+word1);
                    System.out.println("word2 : "+word2);
                    BufferedReader pkeyinput = new BufferedReader(new InputStreamReader(new FileInputStream(positivekey),"UTF-8"));
                    BufferedReader nkeyinput = new BufferedReader(new InputStreamReader(new FileInputStream(negativekey),"UTF-8"));
                    //searching for postive match
                    pkeyinput.readLine();
                    String poskey;
                    while((poskey = pkeyinput.readLine())!= null){
                        if(poskey.equals(word1)||poskey.equals(word2)) { poscount++;break;}
                    }
                    //searching for negative match
                    nkeyinput.readLine();
                    String negkey;
                    while((negkey = nkeyinput.readLine())!= null){
                        if(negkey.equals(word1)||negkey.equals(word2)) { negcount++;break;}
                    }
                }
            }
            System.out.println("filename: "+str);
            System.out.println("poscount"+poscount);
            System.out.println("negcount"+negcount);
            
            String filename= "C:\\MAIN PROJECT\\TEXT FILES\\reviews_output\\" + str1;
           PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filename,true)));
           int polarity = poscount - negcount;
           if(polarity>0) {pw.println("Turney method classifies as POSITIVE");totpos++;}
           else if(polarity==0) { pw.println("Turney method classifies as NEUTRAL");totneu++;}
           else {pw.println("Turney method classifies as NEGATIVE");totneg++;}
           pw.close();
           
            
            
        }
        System.out.println("totpos : "+totpos+"totneg :"+totneg+"totneu :"+totneu);
    }
}