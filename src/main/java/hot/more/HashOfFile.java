
package hot.more;

//import com.google.common.hash.Hashing;
//import com.google.common.io.Files;
import hot.HashTextGui;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

//@Service
@Component
@Scope("singleton")
//@Scope("prototype")
//@Lazy(true)
public class HashOfFile implements BeanNameAware {
    
    private String myName;
    
    @Inject
    private HashTextGui hashTextGui;
    
    @Inject
    @Qualifier("loggerBean")
    private Logger logBean;   
    
    private String fileHash="";
    
    @Override
    public void setBeanName (String myName) {
        this.myName=myName;
    }
    
    @PostConstruct
    public void afterBirn(){
        System.out.println("Bean HashOfFile potok = " + Thread.currentThread().getName()); 
        System.out.println("HashOfFile: my name is = " + myName); 
    }    
    
    @Async
    public synchronized void hash_of_File(String path, String tip) {
        //String fileHash="";
        File buffile = new File(path);
        if (!buffile.isFile()) {
            hashTextGui.outHashTA.setText("not a file !");
            return;
        }
        hashTextGui.outHashTA.setText("PLEASE WAIT !!!");
        /* FileInputStream and BufferedInputStream
           implements AutoClosable     */
        try ( FileInputStream fis = new FileInputStream(buffile);
              BufferedInputStream bis = new BufferedInputStream(fis); )  {
            switch (tip) {
                case "md2":    fileHash = DigestUtils.md2Hex(bis);    break;
                case "md5":    fileHash = DigestUtils.md5Hex(bis);    break;
                //case "md5":    fileHash = com.google.common.io.Files.hash(buffile, Hashing.md5()).toString(); break;
                case "sha1":   fileHash = DigestUtils.sha1Hex(bis);   break;
              //case "crc32":  fileHash = com.google.common.io.Files.hash(buffile, Hashing.crc32()).toString(); break;
                //case "crc32":  fileHash = com.google.common.io.Files.asByteSource(buffile).hash(Hashing.crc32()).toString(); break;
                //case "crc32c": fileHash = com.google.common.io.Files.hash(buffile, Hashing.crc32c()).toString(); break;                
                case "sha256": fileHash = DigestUtils.sha256Hex(bis); break;
                case "sha384": fileHash = DigestUtils.sha384Hex(bis); break;
                case "sha512": fileHash = DigestUtils.sha512Hex(bis); break;
            }
            hashTextGui.outHashTA.setText(fileHash);
            System.out.println("hash of file potok = " + Thread.currentThread().getName());
            //Thread.currentThread().stop();
            //Thread.sleep(1000L);
            //return;
            //executor.
        } catch (FileNotFoundException fex) {
            logBean.log(Level.SEVERE, null, fex);
        } catch (IOException ex) {
            logBean.log(Level.SEVERE, null, ex);
        } //catch (InterruptedException ex) {
          //  Logger.getLogger(HashOfFile.class.getName()).log(Level.SEVERE, null, ex);
        //} 
    }    
    
}
