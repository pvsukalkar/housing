
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
import java.util.Arrays;

public class Sentencemidpos {
    public static void main(String args[]) throws FileNotFoundException, IOException{
        int totpos = 0;
        int totneg = 0;
        int totneu = 0;
        String negators = "E:\\MAIN PROJECT\\TEXT FILES\\negators.txt"; 
        BufferedReader negatorsinn  = new BufferedReader(new InputStreamReader(new FileInputStream(negators),"UTF-8"));
        negatorsinn.readLine();
        ArrayList<String> negatorsList = new ArrayList<String>();
        String negtkey;
        while((negtkey = negatorsinn.readLine()) != null){
            negatorsList.add(negtkey);
        }
        negatorsList.remove("ಆದರೆ");
        File folder = new File("E:\\MAIN PROJECT\\TEXT FILES\\Separate Files\\Separate Files\\lo\\Positive Separate Files");
        File[] listOfFiles = folder.listFiles();
        for(int i=0;i< listOfFiles.length;i++){
            int poscount = 0;
            int negcount = 0;
            File file = listOfFiles[i];
            String str= file.getName();
            String str1 = str;
            str = "E:\\MAIN PROJECT\\TEXT FILES\\Separate Files\\Separate Files\\lo\\Positive Separate Files\\" + str;
            String positivekey = "E:\\MAIN PROJECT\\TEXT FILES\\positivekeywords.txt";
            String negativekey = "E:\\MAIN PROJECT\\TEXT FILES\\negativekeywords.txt";
            BufferedReader review = new BufferedReader(new InputStreamReader(new FileInputStream(str),"UTF-8"));
            review.readLine();
            String line;
            String reviews = null;
            line=review.readLine();
            reviews = line;
            while((line=review.readLine())!=null){
                reviews=reviews+line;
            }
            reviews = reviews.replaceAll("[ ]+"," ");
            reviews = reviews.replaceAll("\\.[ ]+", ".");
            reviews = reviews.replaceAll(",[ ]+", ",");
            reviews = reviews.replaceAll("[ ]*:[ ]+", ":");
            String[] temp = reviews.split("[.]");
            int indx=(temp.length-1)/2;
            String sentence = temp[indx];
            System.out.println(sentence);
            String[] sent_split=sentence.split("[ ,]");
            
            
            for(int i1=0;i1<sent_split.length;i1++){
                String tword=sent_split[i1];
                BufferedReader pkeyinput = new BufferedReader(new InputStreamReader(new FileInputStream(positivekey),"UTF-8"));
                pkeyinput.readLine();
                String poskey;
                while((poskey=pkeyinput.readLine())!=null){
                    if(tword.equals(poskey)){ 
                        System.out.println("matched word: "+tword);poscount++;
                        int index1=i1-3;
                        if(index1<0) index1=0;
                        int index2=i1+3;
                        if(index2>=sent_split.length-1) index2=sent_split.length-1;
                        for(int index=index1;index<=index2;index++){
                            String nword=sent_split[index];
                            for(int i2=0;i2<negatorsList.size();i2++){
                                String ngtr=negatorsList.get(i2);
                                if(nword.equals(ngtr)) {System.out.println("matched neg : "+nword);poscount=poscount-2;break; }
                            }
                        }
                        break;
                    }
                }
               
            }
            for(int i1=0;i1<sent_split.length;i1++){
                String tword=sent_split[i1];
                BufferedReader nkeyinput = new BufferedReader(new InputStreamReader(new FileInputStream(negativekey),"UTF-8"));
                nkeyinput.readLine();
                String negkey;
                while((negkey=nkeyinput.readLine())!=null){
                    if(tword.equals(negkey)){ 
                        System.out.println("matched word: "+tword);negcount++;                    
                        int index1=i1-3;
                        if(index1<0) index1=0;
                        int index2=i1+3;
                        if(index2>=sent_split.length-1) index2=sent_split.length-1;
                        for(int index=index1;index<=index2;index++){
                            String nword=sent_split[index];
                            for(int i2=0;i2<negatorsList.size();i2++){
                                String ngtr=negatorsList.get(i2);
                                if(nword.equals(ngtr)) {System.out.println("matched neg : "+nword);negcount=negcount-2;break; }
                            }
                        }
                        break;
                    }
                }
               
            }
             
           String filename = "E:\\MAIN PROJECT\\TEXT FILES\\reviews_output\\" + str1 ;
           PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filename,true)));
           System.out.println("FILENAME      : "+file.getName());
           System.out.println("POSITIVE COUNT: "+poscount);
           System.out.println("NEGATIVE COUNT: "+negcount);
           int polarity = poscount - negcount;
           if(polarity>0) { pw.println("Sentence_mid algorithm classifies it as POSITIVE");totpos++;}
           else if(polarity==0) { pw.println("Sentence_mid algorithm classifies it as NEUTRAL"); totneu++;}
           else { pw.println("Sentence_mid algorithm classifies it as NEGATIVE");totneg++;}
           pw.close();        
            
        }
        //display results
        System.out.println("FOLDER NAME           : "+folder.getName());
        System.out.println("TOTAL POSITIVE REVIEWS: "+totpos);
        System.out.println("TOTAL NEGATIVE REVIEWS: "+totneg);
        System.out.println("TOTAL NEUTRAL REVIEWS : "+totneu);
    }
}    