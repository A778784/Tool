import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.io.BufferedWriter;
import java.io.IOException;



public class iniFileReading {
    //SwingValue swingValue = new SwingValue();

    //For Local2
    Path iniFilePath = Paths.get("C:/999_JavaTools/001_Getter/000_設定/targetValue.ini");

    public void iniFileRead(String tBLName,Path targetPath,Path commonPath) 
    {

        
        try {
        Charset charset = Charset.forName("SHIFT_JIS");
        
        // Must be changed when directories are changed.
                
        
        String lines = Files.readString(targetPath,charset);
        String iniLines = Files.readString(iniFilePath,charset);

        String arrayLines[] = lines.split("\r\n");
        String arrayIniLines[] = iniLines.split("\r\n");

        String valueSpecifiedFolder = commonPath.toString() + "/SpecifiedValues";
        if (Files.exists(Paths.get(valueSpecifiedFolder))){
            ;
        }else {
        Files.createDirectory(Paths.get(valueSpecifiedFolder));
        }

        StringBuilder specifiedValues = new StringBuilder();

        Path specifiedFile = Paths.get(valueSpecifiedFolder + "/" + tBLName +"_SpecifiedValues.txt");  
        
        BufferedWriter bw = Files.newBufferedWriter(specifiedFile,charset);
        
        String arrayObject = "";
        String arrayObjectIniLines [];
        String targetObject [] = Files.readString(targetPath,charset).split("\r\n");

        // ini value Check



        
        if (tBLName.equals("A")) {

            specifiedValues.append(arrayIniLines[7]);
            if(specifiedValues.substring(arrayIniLines[7].indexOf("=")+1).equals("")) {
            ;
            } else {
 
            specifiedValues.append(arrayIniLines[7]);
             
            arrayObject = (specifiedValues.substring(arrayIniLines[7].indexOf("=")+1,arrayIniLines[7].length()));
            arrayObjectIniLines = arrayObject.split(",");
            System.out.println("---XXX---");
            
            for (int i = 0; i < arrayObjectIniLines.length; i++) {
                
                System.out.println(targetObject[(Integer.parseInt(arrayObjectIniLines[i]))]);
                bw.write(targetObject[(Integer.parseInt(arrayObjectIniLines[i]))]);
                bw.newLine();

                
                
            }bw.flush();
            bw.close();

          

             }
         }

        if (tBLName.equals("B")) {
            specifiedValues.append(arrayIniLines[8]);
            if(specifiedValues.substring(arrayIniLines[8].indexOf("=")+1).equals("")) {
            ;
            } else{
            specifiedValues.append(arrayIniLines[8]);
            System.out.println("---YYY---");
             
            arrayObject = (specifiedValues.substring(arrayIniLines[8].indexOf("=")+1,arrayIniLines[8].length()));
            arrayObjectIniLines = arrayObject.split(",");

            

            for (int i = 0; i < arrayObjectIniLines.length; i++) {
                System.out.println(targetObject[(Integer.parseInt(arrayObjectIniLines[i]))]);
                bw.write(targetObject[(Integer.parseInt(arrayObjectIniLines[i]))]);
                bw.newLine();

                
                
            }bw.flush();
            bw.close();


            } 
        }
        
        if (tBLName.equals("C")) {
            specifiedValues.append(arrayIniLines[9]);
            if(specifiedValues.substring(arrayIniLines[9].indexOf("=")+1).equals("")) {
            ;
        }else{

            arrayObject = (specifiedValues.substring(arrayIniLines[9].indexOf("=")+1,arrayIniLines[9].length()));
            arrayObjectIniLines = arrayObject.split(",");
            System.out.println("---ZZZ---");


            

            for (int i = 0; i < arrayObjectIniLines.length; i++) {
                System.out.println(targetObject[(Integer.parseInt(arrayObjectIniLines[i]))]);
                bw.write(targetObject[(Integer.parseInt(arrayObjectIniLines[i]))]);
                bw.newLine();

                
                
            }bw.flush();
            bw.close();
            

        }
    }

        if (tBLName.equals("D")) {

            specifiedValues.append(arrayIniLines[10]);
            if(specifiedValues.substring(arrayIniLines[10].indexOf("=")+1).equals("")) {
            ;}else{

            arrayObject = (specifiedValues.substring(arrayIniLines[10].indexOf("=")+1,arrayIniLines[10].length()));
            arrayObjectIniLines = arrayObject.split(",");
            System.out.println("---AAA---");

            

            for (int i = 0; i < arrayObjectIniLines.length; i++) {
                System.out.println(targetObject[(Integer.parseInt(arrayObjectIniLines[i]))]);
                bw.write(targetObject[(Integer.parseInt(arrayObjectIniLines[i]))]);
                bw.newLine();

                
                
            }bw.flush();
            bw.close();     
            

    
        }
    }    
        if (tBLName.equals("E")) {
            specifiedValues.append(arrayIniLines[11]);
            if(specifiedValues.substring(arrayIniLines[11].indexOf("=")+1).equals("")) {
            ;}else{

            arrayObject = (specifiedValues.substring(arrayIniLines[11].indexOf("=")+1,arrayIniLines[11].length()));
            arrayObjectIniLines = arrayObject.split(",");
            System.out.println("---BBB---");

         
            for (int i = 0; i < arrayObjectIniLines.length; i++) {
                System.out.println(targetObject[(Integer.parseInt(arrayObjectIniLines[i]))]);
                bw.write(targetObject[(Integer.parseInt(arrayObjectIniLines[i]))]);
                bw.newLine();

                
                
            }bw.flush();
            bw.close();     
           

    
        }
    }       
            if (tBLName.equals("F")) {
         specifiedValues.append(arrayIniLines[12]);
            if(specifiedValues.substring(arrayIniLines[12].indexOf("=")+1).equals("")) {
            ;} else{
            arrayObject = (specifiedValues.substring(arrayIniLines[12].indexOf("=")+1,arrayIniLines[12].length()));
            arrayObjectIniLines = arrayObject.split(",");
            System.out.println("---CCC---");

            

            for (int i = 0; i < arrayObjectIniLines.length; i++) {
                System.out.println(targetObject[(Integer.parseInt(arrayObjectIniLines[i]))]);
                bw.write(targetObject[(Integer.parseInt(arrayObjectIniLines[i]))]);
                bw.newLine();

                
                
            }bw.flush();
            bw.close();     
           

        }
    }

    } catch (IOException e ) {
        System.out.println(e.getMessage());
    }

    
}

   public String iniFileFlagRead() {
      StringBuilder flagJudge = new StringBuilder();
      String commonFlag = "";
      try {
      String flagStr = Files.readString(iniFilePath);
      flagJudge.append(flagStr);
      commonFlag = flagJudge.substring(flagStr.indexOf("=")+1,flagStr.indexOf("=")+2);
      
      
       
    } catch (IOException e ) {
        System.out.println(e.getMessage());
    }
    return commonFlag;
}

   public String individualFlag (int rowNumber) {
    StringBuilder flagJudge = new StringBuilder();
    String individualFlag ="";
    try 
        {
        
        String flagStr = Files.readString( iniFilePath);
        
        String [] flagArray = flagStr.split("\r\n");
        
        flagJudge.append(flagArray[rowNumber]);
        individualFlag = flagJudge.substring(flagJudge.indexOf("=")+1,flagJudge.indexOf("=")+2);
        
        
        }catch (IOException e ) {
            System.out.println(e.getMessage());
        }
        return individualFlag;
    




   }
}