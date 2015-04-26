package sentimentanalysis;


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

public class FinalWin7Neg {
    public static void main(String args[]) throws FileNotFoundException, IOException{
        
        int totpos = 0;
        int totneg = 0;
        int totneu = 0;
        int negtflag;
	int negtspflag;
        int negtspflag_1;
	int negtflag_1;
        // STORE ALL NEGATORS IN A STRING ARRAY
        
        String negators = "E:\\MAIN PROJECT\\TEXT FILES\\negators.txt"; 
        BufferedReader negatorsinn  = new BufferedReader(new InputStreamReader(new FileInputStream(negators),"UTF-8"));
        negatorsinn.readLine();
        ArrayList<String> negatorsList = new ArrayList<String>();
        String negtkey;
        while((negtkey = negatorsinn.readLine()) != null){
            negatorsList.add(negtkey);
        }
        int nlist_size = negatorsList.size();
        File folder = new File("E:\\MAIN PROJECT\\TEXT FILES\\Separate Files\\Separate Files\\Negative Separate Files");
        File[] listOfFiles = folder.listFiles();
        for(int i=0;i< listOfFiles.length;i++){
            int poscount = 0;
            int negcount = 0;
	    negtspflag=0;
            negtspflag_1=0;
            negtflag=0;
	    negtflag_1=0;
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
                            if(wordList.get(i3).equals(subposkey[0]) && wordList.get(i3+1).equals(subposkey[1]) ) { 
                                System.out.println(poskey);poscount++;
                                int index1=0;
                                index1 = i3-7;
                                if(index1<0) index1=0;
                                int index2=0;
                                index2=i3+7;
                                if(index2>=list_size) index2=list_size-1;
                                int i6=0;
                                int spflagl=0;
                                int spflagr=0;
                                for(int i5=0;i5<nlist_size;i5++){
                                        String[] subnegtlist=negatorsList.get(i5).split("[ ]");
                                        for(i6=0;i6<subnegtlist.length;i6++){
                                        int index;
					
                                        for(index=index1;index<=index2;index++){
                                            if(index==i3) continue;
						if(subnegtlist[i6].equals("ಆದರೆ") || subnegtlist[i6].equals("ಹೊರತು") || subnegtlist[i6].equals("ಆದರೂ")||subnegtlist[i6].equals("ಗಿದ್ದರೂ")) {
							
                                                        if(wordList.get(index).contains(subnegtlist[i6])){ 
                                                            if(index<=i3)spflagl=1;
                                                            else if(index>=i3) spflagr=1;
							break;}}
                                                else if(wordList.get(index).contains(subnegtlist[i6])){System.out.println(subnegtlist[i6]); break;}   
                                        }
                                        if(index>index2) break;
                                        }
                                        if(i6==subnegtlist.length && spflagl==1) ;
                                        else if(i6==subnegtlist.length && spflagr==1) negtspflag=negtspflag-2;
                                       else if(i6==subnegtlist.length) negtflag++;  
                                        spflagl=0;
                                        spflagr=0;
                                }
                            } 
                        }   
                    }
                    else if(wordList.get(i3).equals(poskey)) { 
                                System.out.println(poskey);poscount++;
                                int index1=0;
                                index1 = i3-7;
                                if(index1<0) index1=0;
                                int index2=0;
                                index2=i3+7;
                                if(index2>=list_size) index2=list_size-1;
                                int i6=0;
                                int spflagl=0;
                                int spflagr=0;
                                for(int i5=0;i5<nlist_size;i5++){
                                    String[] subnegtlist=negatorsList.get(i5).split("[ ]");
                                        for(i6=0;i6<subnegtlist.length;i6++){
                                        int index;
					
                                        for(index=index1;index<=index2;index++){
                                            if(index==i3) continue;
						if(subnegtlist[i6].equals("ಆದರೆ")|| subnegtlist[i6].equals("ಹೊರತು")|| subnegtlist[i6].equals("ಆದರೂ")||subnegtlist[i6].equals("ಗಿದ್ದರೂ")) {
                                                    
                                                    if(wordList.get(index).contains(subnegtlist[i6])){ 
                                                        if(index<=i3)spflagl=1;
                                                            else if(index>=i3) spflagr=1;
                                                        break;}}
                                                else if(wordList.get(index).contains(subnegtlist[i6])) {System.out.println(subnegtlist[i6]);break;}   
                                        }
                                        if(index>index2) break;
                                        }
					if(i6==subnegtlist.length && spflagl==1) ;
                                        else if(i6==subnegtlist.length && spflagr==1) negtspflag=negtspflag-2;
                                       else if(i6==subnegtlist.length) negtflag++; 
                                        spflagl=0;spflagr=0;
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
                String[] subnegkey=negkey.split("[ ]");
                int lensnk = subnegkey.length;
                for(int i4=0;i4<list_size;i4++){
                    if(lensnk>1){
                        if(i4<list_size-1){
                            if(wordList.get(i4).equals(subnegkey[0]) && wordList.get(i4+1).equals(subnegkey[1]) ) { System.out.println(negkey);negcount++;
							int index11=0;
                                index11 = i4-7;
                                if(index11<0) index11=0;
                                int index22=0;
                                index22=i4+7;
                                if(index22>=list_size) index22=list_size-1;
                                int i66=0;
                                int spflagl=0;
                                int spflagr=0;
                                for(int i55=0;i55<nlist_size;i55++){
                                        String[] subnegtlist=negatorsList.get(i55).split("[ ]");
                                        for(i66=0;i66<subnegtlist.length;i66++){
                                        int index;
							
                                        for(index=index11;index<=index22;index++){
                                            if(index==i4) continue;
					  if(subnegtlist[i66].equals("ಆದರೆ") || subnegtlist[i66].equals("ಹೊರತು") || subnegtlist[i66].equals("ಆದರೂ")||subnegtlist[i66].equals("ಗಿದ್ದರೂ")) {
							
                                              if(wordList.get(index).contains(subnegtlist[i66])){ 
                                                  if(index<=i4)spflagl=1;
                                                            else if(index>=i4) spflagr=1;
							break;}}
                                         else if(wordList.get(index).contains(subnegtlist[i66])){System.out.println(subnegtlist[i66]); break;}   
                                        }
                                        if(index>index22) break;
                                        }
					if(i66==subnegtlist.length && spflagl==1) ;
                                        else if(i66==subnegtlist.length && spflagr==1) negtspflag_1=negtspflag_1+2;
                                       else if(i66==subnegtlist.length) negtflag_1++;  
                                        spflagl=0;
                                        spflagr=0;
                                }		
							}
                        }
                    }
               else if(wordList.get(i4).equals(negkey)) { System.out.println(negkey);negcount++;
								int index11=0;
                                index11 = i4-7;
                                if(index11<0) index11=0;
                                int index22=0;
                                index22=i4+7;
                                if(index22>=list_size) index22=list_size-1;
                                int i66=0;
                                int spflagl=0;
                                int spflagr=0;
                                for(int i55=0;i55<nlist_size;i55++){
                                        String[] subnegtlist=negatorsList.get(i55).split("[ ]");
                                        for(i66=0;i66<subnegtlist.length;i66++){
                                        int index;
										
                                        for(index=index11;index<=index22;index++){
                                            if(index==i4) continue;
				         if(subnegtlist[i66].equals("ಆದರೆ") || subnegtlist[i66].equals("ಹೊರತು") || subnegtlist[i66].equals("ಆದರೂ")||subnegtlist[i66].equals("ಗಿದ್ದರೂ")) {
							
                                             if(wordList.get(index).contains(subnegtlist[i66])) {
                                                 if(index<=i4)spflagl=1;
                                                            else if(index>=i4) spflagr=1;
							break;}}
                                         else if(wordList.get(index).contains(subnegtlist[i66])){System.out.println(subnegtlist[i66]); break;}   
                                        }
                                        if(index>index22) break;
                                        }
					if(i66==subnegtlist.length && spflagl==1);
                                         else if(i66==subnegtlist.length && spflagr==1) negtspflag_1=negtspflag_1+2;
                                        
                                       else if(i66==subnegtlist.length) negtflag_1++;
                                        spflagl=0;
                                        spflagr=0;
				}
			   } 
              }
            }
           // end of neg keyword scanning
           String filename= "E:\\MAIN PROJECT\\TEXT FILES\\reviews_output\\" + str1;
           PrintWriter pw;
            pw = new PrintWriter(new BufferedWriter(new FileWriter(filename,true)));
           poscount = poscount - 2*negtflag;
           poscount =poscount +2*negtflag_1;
           poscount = poscount + negtspflag;
           negcount =negcount - negtspflag_1;
           System.out.println("FILENAME        : "+file.getName());
           System.out.println("POSITIVE COUNT  : "+poscount);
           System.out.println("NEGATIVE COUNT  : "+negcount);
           System.out.println("NEGATORS COUNT  : "+negtflag+" AND: "+negtflag_1);
           System.out.println("SP.NEGATOR COUNT: "+negtspflag+" AND: "+negtspflag_1);
           int polarity = poscount - negcount;
           if(polarity>0) { pw.println("Window7 method classifies as POSITIVE");totpos++;}
           else if(polarity==0) {pw.println("Window7 method classifies as NEUTRAL");totneu++;}
           else {pw.println("Window7 method classifies as NEGATIVE");totneg++;}
           pw.close();
        }
        //display results
        System.out.println("FOLDER NAME           : "+folder.getName());
        System.out.println("TOTAL POSITIVE REVIEWS: "+totpos);
        System.out.println("TOTAL NEGATIVE REVIEWS: "+totneg);
        System.out.println("TOTAL NEUTRAL REVIEWS : "+totneu);
    }
    
}
