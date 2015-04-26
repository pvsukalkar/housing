
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class POSnegmodi{
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
        File folder = new File("E:\\MAIN PROJECT\\TEXT FILES\\AFTER_POS_NEG");
        File[] listOfFiles = folder.listFiles();
        for(int i=0;i< listOfFiles.length;i++){
            int poscount = 0;
            int negcount = 0;
            File file = listOfFiles[i];
            String str= file.getName();
            String str1=str;
            String[] strsp=str1.split("[.]");
            str1 = strsp[0] + ".txt";
            str = "E:\\MAIN PROJECT\\TEXT FILES\\AFTER_POS_NEG\\" + str;
            String positivekey = "E:\\MAIN PROJECT\\TEXT FILES\\positivekeywords.txt";
            String negativekey = "E:\\MAIN PROJECT\\TEXT FILES\\negativekeywords.txt";
            BufferedReader review = new BufferedReader(new InputStreamReader(new FileInputStream(str),"UTF-8"));
            review.readLine();
            String line;
            ArrayList<String> adjWordList = new ArrayList<String>();
            ArrayList<String> wordList = new ArrayList<String>();
            while((line = review.readLine()) != null){
            
                 line = line.replaceAll("[ ]+"," ");
                 String[] line_split = line.split("[	]");
                 wordList.add(line_split[0]);
                 if(line.contains("JJ")) adjWordList.add(line_split[0]);
             }
            System.out.println(adjWordList);
            System.out.println(wordList);
            int list_size = adjWordList.size();
            int word_size = wordList.size();
            /* read all positive keyword and find the match*/
            BufferedReader pkeyinput = new BufferedReader(new InputStreamReader(new FileInputStream(positivekey),"UTF-8"));
            pkeyinput.readLine();
            String poskey;
            int indx=0;
            while((poskey = pkeyinput.readLine())!= null){
                int i32=0;
                for(int i3=0;i3<list_size;i3++){
                    if(adjWordList.get(i3).equals(poskey)) { 
                        poscount =poscount + 1;
                        System.out.println("1:: "+poskey);
                        for(int i31=i32;i31<word_size;i31++){
                            if(wordList.get(i31).equals(adjWordList.get(i3))){
                                indx=i31;
                                i32=i31+1;
                                //print adjective match index
                                System.out.println("adjective match index : "+i31);
                                int indx1=indx-1;
                                if(indx1<0) indx1=0;
                                int indx2=indx+1;
                                if(indx2>=word_size) indx2=word_size-1;
                                String w1=wordList.get(indx1);
                                System.out.println(w1);
                                String w2=wordList.get(indx2); System.out.println(w2);
                                for(int i32a=0;i32a<negatorsList.size();i32a++){
                                        if(negatorsList.get(i32a).equals(w1)) { System.out.println("1.negator : "+negatorsList.get(i32a)+" index is :"+i32a);poscount =poscount -2;break;}
                                    }
                                for(int i32a=0;i32a<negatorsList.size();i32a++){
                                        if(negatorsList.get(i32a).equals(w2)) { System.out.println("1.negator : "+negatorsList.get(i32a)+" index is :"+i32a);poscount =poscount -2;break;}
                                    }
                                
                                break;
                            }        
                           }
                    }    
                    
                }
            }
            // end of pos keyword scanning
            
            /* read all negative keyword and find the match*/
            BufferedReader nkeyinput = new BufferedReader(new InputStreamReader(new FileInputStream(negativekey),"UTF-8"));
            nkeyinput.readLine();
            String negkey;
            while((negkey = nkeyinput.readLine())!= null){
                int i32=0;
                for(int i3=0;i3<list_size;i3++){
                    if(adjWordList.get(i3).equals(negkey)) { 
                        negcount =negcount + 1;
                        System.out.println("1:: "+negkey);
                        for(int i31=i32;i31<word_size;i31++){
                            if(wordList.get(i31).equals(adjWordList.get(i3))){
                                indx=i31;
                                i32=i31+1;
                                //print adjective match index
                                System.out.println("adjective match index : "+i31);
                                int indx1=indx-1;
                                if(indx1<0) indx1=0;
                                int indx2=indx+1;
                                if(indx2>=word_size) indx2=word_size-1;
                                String w1=wordList.get(indx1);
                                System.out.println(w1);
                                String w2=wordList.get(indx2); System.out.println(w2);
                                for(int i32a=0;i32a<negatorsList.size();i32a++){
                                        if(negatorsList.get(i32a).equals(w1)) { System.out.println("1.negator : "+negatorsList.get(i32a)+" index is :"+i32a);negcount =negcount-2;break;}
                                    }
                                for(int i32a=0;i32a<negatorsList.size();i32a++){
                                        if(negatorsList.get(i32a).equals(w2)) { System.out.println("1.negator : "+negatorsList.get(i32a)+" index is :"+i32a);negcount =negcount -2;break;}
                                    }
                                
                                break;
                            }        
                           }
                    }    
                    
                }
            }
           // end of neg keyword scanning
            String filename= "E:\\MAIN PROJECT\\TEXT FILES\\reviews_output\\" + str1;
           PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filename,true)));
           System.out.println("file: "+file.getName());
           System.out.println("+ve: "+poscount+"-ve: "+negcount);
           int polarity = poscount - negcount;
           if(polarity>0) {pw.println("POS method classifies as POSITIVE");totpos++;}
           else if(polarity==0) {pw.println("POS method classifies as NEUTRAL");totneu++;}
           else {pw.println("POS method classifies as NEGATIVE");totneg++;}
           pw.close();
        }    
        
    System.out.println("totpos: "+totpos+"totneg: "+totneg+"totneu: "+totneu);
    }
    
}
