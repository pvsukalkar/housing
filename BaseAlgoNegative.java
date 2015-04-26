
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BaseAlgoNegative{
    public static void main(String args[]) throws FileNotFoundException, IOException{
        
        int totpos = 0;
        int totneg = 0;
        int totneu = 0;
        File folder = new File("E:\\MAIN PROJECT\\TEXT FILES\\Separate Files\\Separate Files\\Negative Separate Files");
        File[] listOfFiles = folder.listFiles();
        for(int i=0;i< listOfFiles.length;i++){
            int poscount = 0;
            int negcount = 0;
            File file = listOfFiles[i];
            String str= file.getName();
            String str1=str;
            str = "E:\\MAIN PROJECT\\TEXT FILES\\Separate Files\\Separate Files\\Negative Separate Files\\" + str;
            String positivekey = "E:\\MAIN PROJECT\\TEXT FILES\\positivekeywords.txt";
            String negativekey = "E:\\MAIN PROJECT\\TEXT FILES\\negativekeywords.txt";
            BufferedReader review = new BufferedReader(new InputStreamReader(new FileInputStream(str),"UTF-8"));
            review.readLine();
            String line;
            ArrayList<String> wordList = new ArrayList<String>();
            while((line = review.readLine()) != null){
                 line = line.replaceAll("[ ]+"," ");
                 line = line.replaceAll("\\.[ ]+", ".");
                 line = line.replaceAll(",[ ]+", ",");
                 line = line.replaceAll("[ ]*:[ ]+", ":");
                 String[] words_line = line.split("[ ,:.]");
                 for(int i1=0;i1<words_line.length;i1++){
                     wordList.add(words_line[i1]);
                 }
             }
            System.out.println(wordList);
            int list_size = wordList.size();
            
            /* read all positive keyword and find the match*/
            BufferedReader pkeyinput = new BufferedReader(new InputStreamReader(new FileInputStream(positivekey),"UTF-8"));
            pkeyinput.readLine();
            String poskey;
            while((poskey = pkeyinput.readLine())!= null){
                String[] subposkey=poskey.split("[ ]");
                int lenspk = subposkey.length;
                for(int i3=0;i3<list_size;i3++){
                    if(lenspk>1){
                        if(i3<list_size-1){
                            if(wordList.get(i3).equals(subposkey[0]) && wordList.get(i3+1).equals(subposkey[1]) ) { System.out.println(poskey);poscount++; } 
                        }   
                    }
                    else if(wordList.get(i3).equals(poskey)) { System.out.println(poskey);poscount++; } 
                }
            }
            // end of pos keyword scanning
            
            /* read all negative keyword and find the match*/
            BufferedReader nkeyinput = new BufferedReader(new InputStreamReader(new FileInputStream(negativekey),"UTF-8"));
            nkeyinput.readLine();
            String negkey;
            while((negkey = nkeyinput.readLine())!= null){
                String[] subnegkey=negkey.split("[ ]");
                int lensnk = subnegkey.length;
                for(int i4=0;i4<list_size;i4++){
                    if(lensnk>1){
                        if(i4<list_size-1){
                            if(wordList.get(i4).equals(subnegkey[0]) && wordList.get(i4+1).equals(subnegkey[1]) ) { System.out.println(negkey);negcount++; }
                        }
                    }
               if(wordList.get(i4).equals(negkey)) { System.out.println(negkey);negcount++; } 
               }
            }
           // end of neg keyword scanning
           String filename = "E:\\MAIN PROJECT\\TEXT FILES\\reviews_output\\" + str1 ;
           PrintWriter pw = new PrintWriter(new FileOutputStream(filename));
           System.out.println("FILENAME      : "+file.getName());
           System.out.println("POSITIVE COUNT: "+poscount);
           System.out.println("NEGATIVE COUNT: "+negcount);
           int polarity = poscount - negcount;
           if(polarity>0) { pw.println("Base algorithm classifies it as POSITIVE");totpos++;}
           else if(polarity==0) { pw.println("Base algorithm classifies it as NEUTRAL"); totneu++;}
           else { pw.println("Base algorithm classifies it as NEGATIVE");totneg++;}
           pw.close();
        }
        //display results
        System.out.println("FOLDER NAME           : "+folder.getName());
        System.out.println("TOTAL POSITIVE REVIEWS: "+totpos);
        System.out.println("TOTAL NEGATIVE REVIEWS: "+totneg);
        System.out.println("TOTAL NEUTRAL REVIEWS : "+totneu);
        
    }
    
}
