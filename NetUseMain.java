import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;


public class NetUseMain {

     public static void main (String args[]) {
        
        NetUseInterface netuse = new NetUse();  

        //setIP
        netuse.setIP();

        //setDate
        netuse.setDate();

        //setPath
        netuse.setPath();

        //getPath
        Path fpath = netuse.getPath();

        //Access(for netuse)
        netuse.access();

        //make copy soruce file & destination set
        netuse.mkFile();

        //WatchService Start.
        WatchServiceInterface wst = new NetWatchService();
     
     　　//For realAccess
        wst.watchStart(fpath);

          //For Test
         //wst.watchStart(Paths.get("C:/XXX/001_TestPath"));               
    }

   


}
