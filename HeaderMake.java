import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class HeaderMake implements HeaderMakeInterface {
    iniFileReading iniReading = new iniFileReading();
    //HeaderPath
    String headerList[] = {"01_A_Value_Name.txt","02_B_Value_Name.txt","03_C_Value_Name.txt","04_D_Value_Name.txt","05_E_Value_Name.txt","06_F_Value_Name.txt"};
    
    Path headerCommonPath = Paths.get("C:/999_JavaTools/001_Getter/000_設定/HeaderFiles");
  
    public void makeHeader(Path targetPath,String outFileStr,Path commonPath) {
    try {
  
       Charset charset = Charset.forName("SHIFT_JIS");
       String lines = Files.readString(targetPath,charset);
       
              
       String linput [] = lines.split("\r\n");
       StringBuilder targetPathSubstring = new StringBuilder();
       
       

       
       targetPathSubstring.append(outFileStr);
       
       String strJudge = targetPathSubstring.substring(0,2);
       

      BufferedWriter bw = Files.newBufferedWriter(targetPath,charset);
      
      

      ArrayList<String> arrayList = new ArrayList<>();

      String flag = "";
      String individualFlag="";
      String headerLines = "";

      flag = iniReading.iniFileFlagRead();
      
     
        if (strJudge.equals("A")) {
        headerLines = headerCommonPath + "/" + headerList[0];
            if (flag.equals("1")){
                individualFlag = iniReading.individualFlag(1);
                }

        } else if (strJudge.equals("B")) {
        headerLines = headerCommonPath + "/" + headerList[1];
        if (flag.equals("1")){
               individualFlag = iniReading.individualFlag(2);
            }
        
    } else if (strJudge.equals("C")) {
        headerLines = headerCommonPath + "/" + headerList[2];
          if (flag.equals("1")){
                individualFlag = iniReading.individualFlag(3);
            }
        
    } else if (strJudge.equals("D")) {
        headerLines = headerCommonPath + "/" + headerList[3];
          if (flag.equals("1")){
                individualFlag = iniReading.individualFlag(4);
                
            }


    }else if (strJudge.equals("E")) {
        headerLines = headerCommonPath + "/" + headerList[4];
          if (flag.equals("1")){
                individualFlag = iniReading.individualFlag(5);
            }

    }else if (strJudge.equals("F")) {
        headerLines = headerCommonPath + "/" + headerList[5];
          if (flag.equals("1")){
                individualFlag = iniReading.individualFlag(6);
            }
    }

        headerLines = Files.readString(Paths.get(headerLines),charset);
        
       String headerinput [] = headerLines.split(",");
        int countNo =1;
     
        for (int i = 0; i < linput.length; i++) {
            
            //Values > Header (for avoiding error)
            if ( (i == linput.length-1) || (i==0) )  {
                arrayList.add(linput[i]);
                bw.write(arrayList.get(i));
                bw.newLine();
            }else {


                if (linput[i].contains(">")){
                    arrayList.add(headerinput[i]+linput[i]+"---");
                    bw.write(arrayList.get(i));
                    bw.newLine();
                    }else{
                    arrayList.add("No." + countNo +":" +  headerinput[i]+"："+linput[i]);
                    bw.write(arrayList.get(i));
                    bw.newLine();
                    countNo++;
                    }
            }
        
        } bw.flush();
          bw.close();

       if (individualFlag.equals("1")){
            iniReading.iniFileRead(strJudge,targetPath,commonPath);
               }
     
       } catch(IOException e ) {
        System.out.println(e.getMessage());
    }
}
    
    
    
    
}

       
       
       
 


       



