package hot;

//import hot.clipboard.ShowClipboard;
//import com.google.common.base.Charsets;
//import com.google.common.hash.Hashing;
import hot.more.TextTransfer;
import hot.more.HashOfFile;
import hot.fsmodel.FileSystemModel;
import java.awt.Dimension;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
@DependsOn(value = {"textTransfer", "fileSystemModel", "actionFacade"})
public class HashTextGui extends javax.swing.JFrame {

    @Inject
    private HashOfFile hashOfFile;
    @Inject
    private TextTransfer textTransfer;
    //@Inject
    //private ShowClipboard showClipboard;
    @Inject
    private FileSystemModel fsModel;
    @Inject
    private ActionFacade actionFacade;
    @Inject
    @Qualifier("loggerBean")
    private Logger logBean;

    @Value("${hash}")
    private String HashTip;
    @Value("${skin}")
    private String currentLAF;
    @Value("${top}")
    private String top;

    public static HashTextGui frame;
    private static final Dimension frameDimension = new Dimension(720, 512);
    public static String[] typeHashArray = {"md2", "md5", "sha1", "sha256", "sha384", "sha512"};
    public ImageIcon FrameIcon = new ImageIcon(getClass().getResource("/img/SubFrameIcon.png"));
    //private String buferHashOfFile = "";
    //private int k = 0;

    public HashTextGui() {
        initComponents();
        this.setIconImage(FrameIcon.getImage());
        this.outHashTF.setComponentPopupMenu(mpMenu);
        this.bcomboHashTip.setModel(new DefaultComboBoxModel<>(typeHashArray));
        jTabbedPane1.addTab("Input Text for hash:", new ImageIcon(getClass().getResource("/img/16x16/text1.png")), textScrollPane);
        jTabbedPane1.addTab("Click File for Hash:", new ImageIcon(getClass().getResource("/img/16x16/tree.png")), fileScrollPane);
    }

    @PostConstruct
    public void afterBirn() {
        System.out.println(HashTip);
        System.out.println(currentLAF);
        this.bcomboHashTip.setSelectedItem(HashTip);
        //this.bcomboHashTip.setVisible(false);
        //jRadioButtonMd5.setSelected(true);
        this.bcomboDevice.setModel(new DefaultComboBoxModel<>(fsModel.getAllrootsString()));
        //this.bcomboDevice.setSelectedIndex(1);
        this.jTree1.setModel(fsModel);
        this.jTree1.setRootVisible(true);
        this.jTree1.setShowsRootHandles(true);
        this.jTabbedPane1.setSelectedIndex(1);
        textTransfer.setClipboardContents("");
        this.setTitle(top);
        /*btnGroup.add(jRadioButtonMd2);
        btnGroup.add(jRadioButtonMd5);
        btnGroup.add(jRadioButtonSha1);
        btnGroup.add(jRadioButtonSha256);
        btnGroup.add(jRadioButtonSha384);
        btnGroup.add(jRadioButtonSha512);*/
    }

    //@Scheduled(cron = "*/1 * * * * *")
    //@Scheduled(fixedDelay = 1000)
    /*@Scheduled(fixedRate = 1000)
    public void goCheck() {
    }*/
    //@Async //- НЕЛЬЗЯ ВЫЗЫВАТЬ В ТОМ ЖЕ КЛАССЕ !!!
    /*  First – let’s go over the rules – @Async has two limitations:
    - it must be applied to public methods only
    - self-invocation – calling the async method from within the same class – won’t work
    The reasons are simple – the method needs to be public so that it can be proxied. 
    And self-invocation doesn’t work because it bypasses the proxy and calls the underlying method directly.  */
    public void GetTextHash(String buf) {
        if (!buf.isEmpty()) {
            switch (HashTip) {
                case "md2":
                    frame.outHashTF.setText(DigestUtils.md2Hex(buf));
                    break;
                case "md5":
                    frame.outHashTF.setText(DigestUtils.md5Hex(buf));
                    break;
                case "sha1":
                    frame.outHashTF.setText(DigestUtils.sha1Hex(buf));
                    break;
                case "sha256":
                    frame.outHashTF.setText(DigestUtils.sha256Hex(buf));
                    break;
                case "sha384":
                    frame.outHashTF.setText(DigestUtils.sha384Hex(buf));
                    break;
                case "sha512":
                    frame.outHashTF.setText(DigestUtils.sha512Hex(buf));
                    break;
            }
            this.jTree1.setSelectionRow(-1);
        } else {
            JOptionPane.showMessageDialog(frame, "'Input Text' is empty !", "Error !", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clearAll() {
        frame.outHashTF.setText("");
        frame.inTF.setText("");
    }

    public void setLF(JFrame frame) {
        try {
            UIManager.setLookAndFeel(currentLAF);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            logBean.log(Level.SEVERE, null, ex);
        }
        SwingUtilities.updateComponentTreeUI(frame);
    }

    public void changeHashTipCombo(String tip) {
        String buf;
        HashTip = tip;
        if (textScrollPane.isShowing()) {
            if (inTF.getText().length() != 0) {
                GetTextHash(inTF.getText());
            }
            return;
        }
        if (fileScrollPane.isShowing()) {
            try {
                buf = jTree1.getSelectionPath().getLastPathComponent().toString();
                hashOfFile.hash_of_File(buf, HashTip);
            } catch (NullPointerException ne) {
                logBean.log(Level.WARNING, "NullPointerException - object for hash not selected !");
            }
        }
    }    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mpMenu = new javax.swing.JPopupMenu();
        mpCopyClip = new javax.swing.JMenuItem();
        mpShowClip = new javax.swing.JMenuItem();
        btnGroup = new javax.swing.ButtonGroup();
        jToolBar1 = new javax.swing.JToolBar();
        bGetHash = new javax.swing.JButton();
        bCopy = new javax.swing.JButton();
        bShowClip = new javax.swing.JButton();
        bClear = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        bcomboHashTip = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jLabel2 = new javax.swing.JLabel();
        bcomboDevice = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jToolBar2 = new javax.swing.JToolBar();
        outHashTF = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        textScrollPane = new javax.swing.JScrollPane();
        inTF = new javax.swing.JTextArea();
        fileScrollPane = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jMenuBar1 = new javax.swing.JMenuBar();
        mFile = new javax.swing.JMenu();
        mGetHash = new javax.swing.JMenuItem();
        mClipCopy = new javax.swing.JMenuItem();
        mClearAll = new javax.swing.JMenuItem();
        mExit = new javax.swing.JMenuItem();
        mHelp = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        mAbout = new javax.swing.JMenuItem();

        mpCopyClip.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/16x16/clipboard_plus.png"))); // NOI18N
        mpCopyClip.setText("Copy to ClipBoard");
        mpCopyClip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mpCopyClipActionPerformed(evt);
            }
        });
        mpMenu.add(mpCopyClip);

        mpShowClip.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/16x16/clipboard_lupa-16.png"))); // NOI18N
        mpShowClip.setText("Show Clipboard");
        mpShowClip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mpShowClipActionPerformed(evt);
            }
        });
        mpMenu.add(mpShowClip);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HashText");
        setUndecorated(true);

        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tools"));
        jToolBar1.setFloatable(false);

        bGetHash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/16x16/play_green-1.png"))); // NOI18N
        bGetHash.setToolTipText("Get Hash of Text");
        bGetHash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGetHashActionPerformed(evt);
            }
        });
        jToolBar1.add(bGetHash);

        bCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/16x16/clipboard_plus.png"))); // NOI18N
        bCopy.setToolTipText("Copy Hash to ClipBoard");
        bCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCopyActionPerformed(evt);
            }
        });
        jToolBar1.add(bCopy);

        bShowClip.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/16x16/clipboard_lupa-16.png"))); // NOI18N
        bShowClip.setToolTipText("Show ClipBoard");
        bShowClip.setFocusable(false);
        bShowClip.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bShowClip.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bShowClip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bShowClipActionPerformed(evt);
            }
        });
        jToolBar1.add(bShowClip);

        bClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/16x16/edit_clear-16.png"))); // NOI18N
        bClear.setToolTipText("Clear All");
        bClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearActionPerformed(evt);
            }
        });
        jToolBar1.add(bClear);
        jToolBar1.add(jSeparator3);

        jLabel1.setText("Hash Type = ");
        jToolBar1.add(jLabel1);
        jToolBar1.add(jSeparator4);

        bcomboHashTip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        bcomboHashTip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcomboHashTipActionPerformed(evt);
            }
        });
        jToolBar1.add(bcomboHashTip);
        jToolBar1.add(jSeparator2);

        jLabel2.setText(" Device = ");
        jToolBar1.add(jLabel2);

        bcomboDevice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        bcomboDevice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bcomboDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcomboDeviceActionPerformed(evt);
            }
        });
        jToolBar1.add(bcomboDevice);
        jToolBar1.add(jSeparator1);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.NORTH);

        jToolBar2.setFloatable(false);
        jToolBar2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        outHashTF.setEditable(false);
        outHashTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        outHashTF.setBorder(javax.swing.BorderFactory.createTitledBorder("Output Hash"));
        jToolBar2.add(outHashTF);

        getContentPane().add(jToolBar2, java.awt.BorderLayout.SOUTH);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Text or File Path"));
        jTabbedPane1.setToolTipText("");

        inTF.setColumns(20);
        inTF.setRows(5);
        inTF.setToolTipText("");
        textScrollPane.setViewportView(inTF);

        jTabbedPane1.addTab("Input Text for hash:", new javax.swing.ImageIcon(getClass().getResource("/img/16x16/text1.png")), textScrollPane); // NOI18N

        jTree1.setModel(fsModel);
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        fileScrollPane.setViewportView(jTree1);

        jTabbedPane1.addTab("Click on File for Hash:", fileScrollPane);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);
        jTabbedPane1.getAccessibleContext().setAccessibleName("Input Text");

        mFile.setText("File");

        mGetHash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/16x16/play_green-1.png"))); // NOI18N
        mGetHash.setText("Get Hash of Text");
        mGetHash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mGetHashActionPerformed(evt);
            }
        });
        mFile.add(mGetHash);

        mClipCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/16x16/clipboard_plus.png"))); // NOI18N
        mClipCopy.setText("Copy Hash to ClipBoard");
        mClipCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mClipCopyActionPerformed(evt);
            }
        });
        mFile.add(mClipCopy);

        mClearAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/16x16/edit_clear-16.png"))); // NOI18N
        mClearAll.setText("Clear All");
        mClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mClearAllActionPerformed(evt);
            }
        });
        mFile.add(mClearAll);

        mExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/16x16/quit.png"))); // NOI18N
        mExit.setText("Exit");
        mExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mExitActionPerformed(evt);
            }
        });
        mFile.add(mExit);

        jMenuBar1.add(mFile);

        mHelp.setText("Info");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/16x16/clipboard_lupa-16.png"))); // NOI18N
        jMenuItem1.setText("Show Clipboard");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mHelp.add(jMenuItem1);

        mAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/16x16/help-green-16.png"))); // NOI18N
        mAbout.setText("About");
        mAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mAboutActionPerformed(evt);
            }
        });
        mHelp.add(mAbout);

        jMenuBar1.add(mHelp);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bGetHashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGetHashActionPerformed
        GetTextHash(inTF.getText());
    }//GEN-LAST:event_bGetHashActionPerformed

    private void bCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCopyActionPerformed
        textTransfer.setClipboardContents(frame.outHashTF.getText());
    }//GEN-LAST:event_bCopyActionPerformed

    private void mAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mAboutActionPerformed
        actionFacade.about(frame);
    }//GEN-LAST:event_mAboutActionPerformed

    private void mGetHashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mGetHashActionPerformed
        GetTextHash(inTF.getText());
    }//GEN-LAST:event_mGetHashActionPerformed

    private void mClipCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mClipCopyActionPerformed
        textTransfer.setClipboardContents(frame.outHashTF.getText());
    }//GEN-LAST:event_mClipCopyActionPerformed

    private void mExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mExitActionPerformed
        int r = JOptionPane.showConfirmDialog(frame, "Exit HOFAT ?", "Quit HOFAT", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_mExitActionPerformed

    private void bClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearActionPerformed
        clearAll();
    }//GEN-LAST:event_bClearActionPerformed

    private void mClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mClearAllActionPerformed
        clearAll();
    }//GEN-LAST:event_mClearAllActionPerformed

    private void bShowClipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bShowClipActionPerformed
        actionFacade.showClipboard(frame);
    }//GEN-LAST:event_bShowClipActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        actionFacade.showClipboard(frame);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void mpCopyClipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mpCopyClipActionPerformed
        textTransfer.setClipboardContents(frame.outHashTF.getText());
    }//GEN-LAST:event_mpCopyClipActionPerformed

    private void mpShowClipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mpShowClipActionPerformed
        actionFacade.showClipboard(frame);
    }//GEN-LAST:event_mpShowClipActionPerformed

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
        inTF.setText("");
        try {
            hashOfFile.hash_of_File(jTree1.getSelectionPath().getLastPathComponent().toString(), HashTip);
        } catch (NullPointerException ne) {
        }
    }//GEN-LAST:event_jTree1MouseClicked

    private void bcomboDeviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcomboDeviceActionPerformed
        fsModel.setRoot(fsModel.getAllroots()[bcomboDevice.getSelectedIndex()].getPath());
        jTree1.setModel(fsModel);
        clearAll();
        SwingUtilities.updateComponentTreeUI(jTree1);
    }//GEN-LAST:event_bcomboDeviceActionPerformed

    private void bcomboHashTipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcomboHashTipActionPerformed
        changeHashTipCombo(bcomboHashTip.getSelectedItem().toString());
    }//GEN-LAST:event_bcomboHashTipActionPerformed

    public synchronized static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HashTextGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }        
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            //java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //ApplicationContext 
                // Shutdown Spring container gracefully in non-web applications !!
                AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
                //ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
                ctx.registerShutdownHook();
                // app runs here...
                // main method exits, hook is called prior to the app shutting down...
                // define @PreDestroy methods for your beans !!! - it is called before close App !!!
                System.out.println("main potok = " + Thread.currentThread().getName());
                System.out.println(" CPU cores = 0 - " + ForkJoinPool.getCommonPoolParallelism());
                frame = ctx.getBean(HashTextGui.class);
                frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
                frame.setLF(frame);
                frame.setSize(frameDimension);
                frame.setMinimumSize(frameDimension);
                frame.setVisible(true);
            }
        });
    }

    // DO NOT USE STATIC FIELDS WITH SPRING !!!!!!!!!!!!!!!!!!!!!!!!!!!

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClear;
    private javax.swing.JButton bCopy;
    private javax.swing.JButton bGetHash;
    private javax.swing.JButton bShowClip;
    public javax.swing.JComboBox<String> bcomboDevice;
    private volatile javax.swing.JComboBox<String> bcomboHashTip;
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.JScrollPane fileScrollPane;
    public javax.swing.JTextArea inTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    public javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JTree jTree1;
    private javax.swing.JMenuItem mAbout;
    private javax.swing.JMenuItem mClearAll;
    private javax.swing.JMenuItem mClipCopy;
    private javax.swing.JMenuItem mExit;
    private javax.swing.JMenu mFile;
    private javax.swing.JMenuItem mGetHash;
    private javax.swing.JMenu mHelp;
    private javax.swing.JMenuItem mpCopyClip;
    private javax.swing.JPopupMenu mpMenu;
    private javax.swing.JMenuItem mpShowClip;
    public volatile javax.swing.JTextField outHashTF;
    private javax.swing.JScrollPane textScrollPane;
    // End of variables declaration//GEN-END:variables
}
