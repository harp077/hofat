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
    private TextTransfer textTransfer;

    public static List<String> lookAndFeelsDisplay = new ArrayList<>();
    public static List<String> lookAndFeelsRealNames = new ArrayList<>();
    //public static List<String> pgsTemes = new ArrayList<>();
    //public static List<String> tinyTemes = new ArrayList<>();
    //public static List<String> lipstikTemes = new ArrayList<>();    

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
                + "Home=http://hofat.sf.net/ ,\n"
                + "Mail=harp07@mail.ru",
                top, JOptionPane.INFORMATION_MESSAGE, icon);
    }

    public void MyInstLF(String lf) {
        lookAndFeelsDisplay.add(lf);
        lookAndFeelsRealNames.add(lf);
    }

    public void InstallLF() {
        /*tinyTemes.add("lib/themes/Default.theme");
        tinyTemes.add("lib/themes/Forest.theme");
        tinyTemes.add("lib/themes/Golden.theme");
        tinyTemes.add("lib/themes/Plastic.theme");
        tinyTemes.add("lib/themes/Silver.theme");
        tinyTemes.add("lib/themes/Nightly.theme");
        tinyTemes.add("lib/themes/My_Cyan.theme");
        tinyTemes.add("lib/themes/My_Yellow.theme");
        tinyTemes.add("lib/themes/My_AquaMarine.theme");
        tinyTemes.add("lib/themes/My_Magenta.theme");
        tinyTemes.add("lib/themes/My_Green.theme");
        lipstikTemes.add("Default");
        lipstikTemes.add("Klearlooks");
        lipstikTemes.add("LightGray");
        pgsTemes.add("vista");
        pgsTemes.add("silver");
        pgsTemes.add("native"); */       
        /*MyInstLF("de.muntjak.tinylookandfeel.TinyLookAndFeel");
        MyInstLF("javax.swing.plaf.metal.MetalLookAndFeel");
        MyInstLF("com.pagosoft.plaf.PgsLookAndFeel");
        MyInstLF("com.birosoft.liquid.LiquidLookAndFeel");
        MyInstLF("net.beeger.squareness.SquarenessLookAndFeel");
        MyInstLF("com.lipstikLF.LipstikLookAndFeel");
        MyInstLF("com.jgoodies.looks.plastic.PlasticLookAndFeel");
        PlasticLookAndFeel.setPlasticTheme(new SkyGreen());
        MyInstLF("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
        MyInstLF("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");*/
        ///////////////////
        MyInstLF("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        MyInstLF("com.jtattoo.plaf.aero.AeroLookAndFeel");
        MyInstLF("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        MyInstLF("com.jtattoo.plaf.fast.FastLookAndFeel");
        MyInstLF("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        MyInstLF("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        MyInstLF("com.jtattoo.plaf.mint.MintLookAndFeel");
        MyInstLF("com.jtattoo.plaf.noire.NoireLookAndFeel");
        MyInstLF("com.jtattoo.plaf.smart.SmartLookAndFeel");
        MyInstLF("com.jtattoo.plaf.luna.LunaLookAndFeel");
        MyInstLF("com.jtattoo.plaf.texture.TextureLookAndFeel");
        MyInstLF("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
        MyInstLF("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
        ///////////////////////
        /*MyInstLF("org.pushingpixels.substance.api.skin.SubstanceSaharaLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceAutumnLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceCremeCoffeeLookAndFeel");                  
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceModerateLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceMagellanLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceMistAquaLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceMistSilverLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceOfficeBlack2007LookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceOfficeSilver2007LookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceNebulaLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceNebulaBrickWallLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceGeminiLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceDustCoffeeLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceRavenLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceGraphiteAquaLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceGraphiteGlassLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlueSteelLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceMarinerLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceTwilightLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceCeruleanLookAndFeel"); 
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceEmeraldDuskLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceChallengerDeepLookAndFeel");
        */        
    }

    public void setLF(JFrame frame) {
        /*if (currentLAF.contains("tinylookandfeel")) {
            de.muntjak.tinylookandfeel.Theme.loadTheme(new File(currentTheme));
        }
        if (currentLAF.contains("lipstik")) {
            switch (currentTheme) {
                case "Default": 
                    LipstikLookAndFeel.setMyCurrentTheme(new DefaultTheme()); break;
                case "Klearlooks":
                    LipstikLookAndFeel.setMyCurrentTheme(new KlearlooksTheme()); break;
                case "LightGray":
                    LipstikLookAndFeel.setMyCurrentTheme(new LightGrayTheme()); break;
                default:
                    LipstikLookAndFeel.setMyCurrentTheme(new DefaultTheme());
            }
        } 
        if (currentLAF.contains("pagosoft")) {
            switch (currentTheme) {
                case "vista": 
                    PlafOptions.setCurrentTheme(new VistaTheme()); break;
                case "silver":
                    PlafOptions.setCurrentTheme(new SilverTheme()); break;
                case "native":
                    PlafOptions.setCurrentTheme(new NativeColorTheme()); break;
                default:
                    PlafOptions.setCurrentTheme(new VistaTheme());
            }
        }*/        
        try {
            UIManager.setLookAndFeel(currentLAF);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ActionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        SwingUtilities.updateComponentTreeUI(frame);
    }

    public void changeLF(JFrame frame) {
        String changeLook = (String) JOptionPane.showInputDialog(frame, "Choose Look and Feel Here:", "Select Look and Feel", JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource("/img/color_swatch.png")), lookAndFeelsDisplay.toArray(), null);
        //String changeLook = "de.muntjak.tinylookandfeel.TinyLookAndFeel";
        /*if (changeLook.contains("tinylookandfeel")) {
            currentTheme = (String) JOptionPane.showInputDialog(frame, "Set TinyLF Theme:", "Select TinyLF Theme", JOptionPane.QUESTION_MESSAGE, new javax.swing.ImageIcon(getClass().getResource("/img/color_swatch.png")), tinyTemes.toArray(), null);
        }
        if (changeLook.contains("lipstik")) {
            currentTheme = (String) JOptionPane.showInputDialog(frame, "Set LipstikLF Theme:", "Select LipstikLF Theme", JOptionPane.QUESTION_MESSAGE, new javax.swing.ImageIcon(getClass().getResource("/img/color_swatch.png")), lipstikTemes.toArray(), null);
        }
        if (changeLook.contains("pagosoft")) {
            currentTheme = (String) JOptionPane.showInputDialog(frame, "Set PgsLF Theme:", "Select PgsLF Theme", JOptionPane.QUESTION_MESSAGE, new javax.swing.ImageIcon(getClass().getResource("/img/color_swatch.png")), pgsTemes.toArray(), null);
        }  */      
        if (changeLook != null) {
            for (int a = 0; a < lookAndFeelsDisplay.size(); a++) {
                if (changeLook.equals(lookAndFeelsDisplay.get(a))) {
                    currentLAF = lookAndFeelsRealNames.get(a);
                    setLF(frame);
                    break;
                }
            }
        }
    }
    

}
