import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Calendar;
import java.text.SimpleDateFormat;


public class NetUse implements NetUseInterface {
    private static Path filePath;
    private static String ip;
    private static String pathIp;
    private static String date;
    private static Path destination;
    
    CsvShapingInterface csvShape = new CsvShaping();


    // inputPath
    @Override
      
    public void setIP() {
        Console console = System.console();
        this.ip =console.readLine("Input IP\t: ");
        String commonpath = "C$\\XXXX";
        

        this.pathIp  ="\\\\" + ip +  "\\" + commonpath;
    }

        //Current Date or Specified Date Set.
    @Override
    public void setDate() {
        
        
        Calendar cl = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(cl.getTime());

        System.out.println("Current Date\t: " + date);
        
        Console console = System.console();
        
        String yesno = console.readLine("Date Change ? Yes : Y / No : N (CurrentDate) "); 
        
        
        if (yesno.equals("Y")) {
            String date2 = console.readLine("Input Date\t: ");
            this.date = date2;
        } else {
            this.date = date;
        }
        
       

    }

    @Override
    public void setPath(){
        this.filePath = Paths.get(this.pathIp + "/" + this.date);
    }

    @Override
    public Path getPath(){
        return this.filePath;
    }


    
    //Net Use 
    
    @Override
    public void access() {
        try{
        String cmds [] = new String [] {"net","use",this.filePath.toString(),"/user:XXX","XXX"};
        ProcessBuilder pb = new ProcessBuilder (cmds);
        Process process = pb.start();
        System.out.println("---Access---");
    } catch (IOException e) {
        System.out.println(e.getMessage());
        System.exit(0);
        }
    }
        
    




    //Destination mkdir & field setted.
    @Override
    public void mkFile() {       
            


            Path mkdirpath = Paths.get("C:\\999_JavaTools\\001_Getter\\" + this.ip + "\\" + this.date);
            this.destination = mkdirpath;
            System.out.println("---mkdir---\t: " + this.destination);

            if (Files.exists(mkdirpath)) 
            {;}
            else {
                try{

            Files.createDirectories(mkdirpath);
            
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } 
        }
        }


      //copy  
      @Override
      public void copyFile(Object context){
      StringBuilder sb = new StringBuilder();
      //Context which WatchService gets; ex:Tran..dat
      String cont = context.toString();
      sb.append(cont);
      String numberName = sb.substring(cont.indexOf(".")-4,cont.indexOf("."));
      
      //testpath should be changed access(after tested).
      //String testPath = ("C:/XXX/001_TestPath");
      
      //For netuse
      String realPath = this.filePath.toString();
      String sourcePath = realPath  +"/" +  cont;
      
      //String sourcePath = testPath  +"/" +  cont;
      
      //Copy source
      Path source = Paths.get(sourcePath);
      String xxxFileDir = this.destination.toString() + "\\" + numberName + "\\";
      
       try {
        if (Files.exists(Paths.get(xxxFileDir))){
            ;
        }else {
            Files.createDirectory(Paths.get(xxxFileDir));
        }

      } catch (IOException e) {
        System.out.println(e.getMessage());
      }

      Path target = Paths.get(xxxFileDir + "/" +cont);
      String xxxTarget = "";
      StringBuilder xxxSb = new StringBuilder();

      try {

      Files.copy(source,target);
      callAAA(target);


      xxxSb.append(target.toString());
      xxxTarget = xxxSb.substring(xxxSb.indexOf("-"),xxxSb.lastIndexOf("."));
      xxxTarget = "TTT" + xxxTarget + ".dat";
      
      target = Paths.get(xxxFileDir + "/" + xxxTarget);
      
      
      //must.


      } catch (IOException e ) {
          e.printStackTrace();
          System.exit(0);
      }
      
      
      csvShape.csvShaping(Paths.get(xxxFileDir),target);
      
    }



    //give WatchService dstpath.
    @Override
    public Path getDest() {
        return this.destination;
    }

    @Override //inflate exe must.
    
    public void callAAA(Path target) {

        String xxxAbsolutePath = ("C:\\999_JavaTools\\001_TranGetter\\000_設定\XXX\\XXX.exe");
        

        String cmds [] = new String [] {xxxAbsolutePath,target.toString()};
        
        try {
        ProcessBuilder pb = new ProcessBuilder (cmds);
        Process process = pb.start();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Thread thread = new Thread();
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie ) {
            System.out.println(ie.getMessage());
        }
        

    }
    
}



    



    




