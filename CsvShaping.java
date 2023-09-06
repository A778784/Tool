import java.io.IOException;
import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;



public class CsvShaping implements CsvShapingInterface {
    HeaderMakeInterface headerMake = new HeaderMake();
    
    @Override
    public void csvShaping(Path targetPath,Path targetObjectPath){        
        Charset charset = Charset.forName("SHIFT_JIS");
        try {
        //array
        String lines = Files.readString(targetObjectPath,charset);
        //reading for prepartion
        lines = lines.replace("\r\n",",");
        String str[] = lines.split(",");
        //initialize 1.commonOutFilePath 2.for changePath 3.for substring
        String outFileStr = "";
        Path newOutPutPath = null;
        StringBuilder sb = new StringBuilder();
        String firstTBL = "A_TBL.txt";
        String firstXXXPath =  (targetPath.toString() + "\\" + "01_" + firstTBL);
        //counter
        int count = 1;
        int recCount = 1; //count appended for RRR #2
        //initialSet
         Path XXXTBL = Paths.get(firstXXXPath);
        BufferedWriter bw = Files.newBufferedWriter(XXXTBL,charset);
        Path pathForRec = null; //Folder For RRR#2
       
        for (int i = 0 ; i < str.length; i++){

                //commonCase
            if (str[i].contains("<") == false) {
                bw.write(str[i]);
                bw.newLine();

            //case : contains:TTT
            }
            if (str[i].contains("TTT")) {
                sb.delete(0,sb.length());
                sb.append(str[i]);

                //case 0 YYY</XXXTTT>close
                       if (sb.indexOf("<")!=0){

                       bw.write(sb.substring(0,str[i].indexOf("<")));
                       bw.newLine();
                       bw.write(sb.substring(str[i].indexOf("<")));

    
                         bw.flush();
                         bw.close();
                         count++; 
                        
                     headerMake.makeHeader(newOutPutPath,outFileStr,targetPath);

                           //case 1 : </XXX_TTT>
                   }else if (sb.indexOf("/") == 1 ) {
                      count++;
                      recCount=1; //count for rec is initialized.
         
                           //case2 : <XXX_TTT> newFileOpen
                     }else if ((str[i].indexOf(">")+1)==str[i].length()) {
                            outFileStr = sb.substring(1,str[i].indexOf(">"));
                            pathForRec = Paths.get( targetPath + "\\" +  "0" + count + "_" + outFileStr);
                            Files.createDirectory(pathForRec);
 
                           //case3 : <XXX_TTT>YYY
                       }else if  ((str[i].indexOf(">")+1) != str[i].length()) {
                    outFileStr = sb.substring(1,str[i].indexOf(">")); 
                    newOutPutPath = Paths.get( targetPath + "\\"  + "0" + count + "_" + outFileStr + ".txt");
                    bw = Files.newBufferedWriter(newOutPutPath,charset);
                    bw.write(sb.substring(str[i].indexOf("<"),str[i].indexOf(">")+1));
                    bw.newLine();
                    bw.write(sb.substring(str[i].indexOf(">")+1));
                    bw.newLine();
                   }
                  
               //case4　:　<XXX_RRR>XXX newFileOpen #2append
        }else if (str[i].contains("RRR")) {
                sb.delete(0,sb.length());
                sb.append(str[i]);
                if (!(str[i].contains("/"))) {
               outFileStr = sb.substring(1,str[i].indexOf(">"));
               newOutPutPath = Paths.get( pathForRec.toString() + "\\" +  "0" + recCount + "_" + outFileStr + ".txt");
               bw = Files.newBufferedWriter(newOutPutPath,charset);
               bw.write(sb.substring(str[i].indexOf("<"),str[i].indexOf(">")+1));
               bw.newLine();
               bw.write(sb.substring(str[i].indexOf(">")+1));
               bw.newLine();                                

                    //case4 : YYY</XXX_RRR>
             }else if (str[i].contains("/")){
               bw.write(sb.substring(0,str[i].indexOf("<")));
                bw.newLine();
                bw.write(sb.substring(str[i].indexOf("<")));
                
                bw.flush();
                bw.close();
                recCount++;

                headerMake.makeHeader(newOutPutPath,outFileStr,targetPath);
             }
            }
            }
        }catch(IOException e ) {
            System.err.println(e.getMessage());
            System.out.println("---error---");
            System.exit(0);
        }
     }
}
