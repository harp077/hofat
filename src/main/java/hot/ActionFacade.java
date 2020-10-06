package hot;

import hot.more.TextTransfer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
@Lazy(false)
@DependsOn(value = {"textTransfer"})
public class ActionFacade {

    @Value("${top}")
    private String top;
    //@Value("${tema}")
    //private String currentTheme;    
    @Value("${skin}")
    private String currentLAF;
    
    @Inject
    @Qualifier("loggerBean")
    private Logger logBean;    

    @Inject
    private TextTransfer textTransfer;

    public static List<String> lookAndFeelsDisplay = new ArrayList<>();
    public static List<String> lookAndFeelsRealNames = new ArrayList<>();

    @PostConstruct
    public void afterBirn() {
        InstallLF();
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
    }
    
    public void showClipboard(JFrame frame) {
        //TextTransfer textTransfer = new TextTransfer();
        String buf = textTransfer.getClipboardContents();
        JTextArea taClip = new JTextArea(10,30);
        taClip.setText(buf);
        //taClip.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JScrollPane jsc=new JScrollPane(taClip);
        jsc.setViewportView(taClip);        
        Object[] ob = {jsc};
        ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/img/24x24/clipboard_search.png"));
        JOptionPane.showMessageDialog(frame, ob, "ClipBoard", JOptionPane.INFORMATION_MESSAGE, icon);
    }

    public void about(JFrame frame) {
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/thehash_245x143.png"));
        JOptionPane.showMessageDialog(frame,
                  "HOFAT - free portable cross-platform\n"
                + "hash calculator, 100%-pure Java. Support\n"
                + "md2,md5,sha1,sha256,sha384,sha512 hashes.\n"
                + "Developed with Java Spring Framework.\n"
                + "Tested in Windows/Linux. Need JRE-1.8\n"
                + "Roman Koldaev, Saratov city, Russia.\n"
                + "Home = http://hofat.sf.net/  or \n"
                + "https://github.com/harp077/hofat/ \n"
                + "Mail=harp07@mail.ru",
                top, JOptionPane.INFORMATION_MESSAGE, icon);
    }

    public void MyInstLF(String lf) {
        lookAndFeelsDisplay.add(lf);
        lookAndFeelsRealNames.add(lf);
    }

    public void InstallLF() {
        MyInstLF("de.muntjak.tinylookandfeel.TinyLookAndFeel");
        MyInstLF("javax.swing.plaf.metal.MetalLookAndFeel");
    }

    public void setLF(JFrame frame) {
        try {
            UIManager.setLookAndFeel(currentLAF);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            logBean.log(Level.SEVERE, null, ex);
        }
        SwingUtilities.updateComponentTreeUI(frame);
    }

    /*public void changeLF(JFrame frame) {
        String changeLook = (String) JOptionPane.showInputDialog(frame, "Choose Look and Feel Here:", "Select Look and Feel", JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource("/img/color_swatch.png")), lookAndFeelsDisplay.toArray(), null);
        if (changeLook != null) {
            for (int a = 0; a < lookAndFeelsDisplay.size(); a++) {
                if (changeLook.equals(lookAndFeelsDisplay.get(a))) {
                    currentLAF = lookAndFeelsRealNames.get(a);
                    setLF(frame);
                    break;
                }
            }
        }
    }*/
    

}
