
package hot.more;

import hot.HashTextGui;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

//@Service
@Component
@Scope("singleton")
//@Scope("prototype")
//@Lazy(true)
public class HashOfFile {
    
    @Inject
    private HashTextGui hashTextGui;
    
    //@Inject
    //private TaskExecutor executor;
    
    private String fileHash="";
    
    @PostConstruct
    public void afterBirn(){
        System.out.println("Bean HashOfFile potok = " + Thread.currentThread().getName());  
    }    
    
    @Async
    public synchronized void hash_of_File(String path, String tip) {
        //String fileHash="";
        File buffile = new File(path);
        if (!buffile.isFile()) {
            hashTextGui.outHashTF.setText("not a file !");
            return;
        }
        hashTextGui.outHashTF.setText("PLEASE WAIT !!!");
        try ( FileInputStream fis = new FileInputStream(buffile);
              BufferedInputStream bis = new BufferedInputStream(fis); )  {
            switch (tip) {
                case "md2":    fileHash = DigestUtils.md2Hex(bis);    break;
                case "md5":    fileHash = DigestUtils.md5Hex(bis);    break;
                case "sha1":   fileHash = DigestUtils.sha1Hex(bis);   break;
                case "sha256": fileHash = DigestUtils.sha256Hex(bis); break;
                case "sha384": fileHash = DigestUtils.sha384Hex(bis); break;
                case "sha512": fileHash = DigestUtils.sha512Hex(bis); break;
            }
            hashTextGui.outHashTF.setText(fileHash);
            System.out.println("hash of file potok = " + Thread.currentThread().getName());
            //Thread.currentThread().stop();
            //Thread.sleep(1000L);
            //return;
            //executor.
        } catch (FileNotFoundException fex) {
            //Logger.getLogger(HashOfFile.class.getName()).log(Level.SEVERE, null, fex);
        } catch (IOException ex) {
            //Logger.getLogger(HashOfFile.class.getName()).log(Level.SEVERE, null, ex);
        } //catch (InterruptedException ex) {
          //  Logger.getLogger(HashOfFile.class.getName()).log(Level.SEVERE, null, ex);
        //} 
    }    
    
}
