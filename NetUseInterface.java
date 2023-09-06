import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public interface NetUseInterface {
 
    //Set IP
    public void setIP();
    //Set Date
    public void setDate();
    //Set Path
    public void setPath();
    //get Path
    public Path getPath();

    //Access Path
    public void access();

    //GetFile
    public void mkFile();
    //CopyFile
    
    //getDest →　WatchService
    public Path getDest();

    //WatchService calls.
    public void copyFile(Object context);


    //Call AAA.exe
    
    public void callAAA (Path target);
    

}