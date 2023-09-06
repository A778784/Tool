import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.Watchable;

import static java.nio.file.StandardWatchEventKinds.*;
import static java.nio.file.WatchEvent.*;

public class NetWatchService implements WatchServiceInterface {
    private Object context; 
    private Path destination;
    
    NetUseInterface netuse = new NetUse();
    CsvShapingInterface csvShape = new CsvShaping(); 
    
    @Override
    public void watchStart(Path fpath) {

        Path dest = netuse.getDest();  
        this.destination = dest;
    
        WatchService watcher;
        watcher = null;
 

    try {
        watcher = FileSystems.getDefault().newWatchService();
        //objectPath
        Watchable path = fpath;
        path.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
    } catch (IOException e) {
        e.printStackTrace();
    } 
 
    while (true) {
        WatchKey watchKey;
        watchKey = null;

        try {
            watchKey = watcher.take();
        } catch (InterruptedException e ) {
            System.err.println(e.getMessage());
        }

        for (WatchEvent<?> event : watchKey.pollEvents() ) {
            Kind<?> kind = event.kind();
            Object context = event.context();
            
            this.context = context;
            StringBuilder sb = new StringBuilder();
            sb.append(context.toString());
            String fileExtension = sb.substring(sb.indexOf(".")+1,sb.length());
            
            if ( (kind == ENTRY_CREATE) && (! (fileExtension.equals("gz.tmp")) )){
                
                String strObj = context.toString();
                System.out.println("---Copy---\t: " +  strObj);
                Thread thread = new Thread();
                try {
                    Thread.sleep(500);
                }catch (InterruptedException ie ) {
                    System.out.println(ie.getMessage());
                }
                netuse.copyFile(context);
                } else {;}
        }

        if(!watchKey.reset()) {
            System.out.println("WatchKey is Invalid");
            System.exit(0);

        }
        }
    }

}
