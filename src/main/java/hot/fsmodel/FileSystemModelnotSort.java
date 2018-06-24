package hot.fsmodel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component
//@Scope("singleton")
//@Lazy(false)
public class FileSystemModelnotSort implements TreeModel {

    private String root="/"; // The root identifier
    private Vector listeners; // Declare the listeners vector
    private File[] allroots;
    private List<String> allrootsString = new ArrayList<>();

    public FileSystemModelnotSort() {    }

    //@PostConstruct
    public void afterBirn() {
        listeners = new Vector();
        allroots = File.listRoots();
        for (File file : allroots) {
            allrootsString.add(file.toString());
        }
    }

    @Override
    public Object getRoot() {
        return new File(root);
    }

    @Override
    public Object getChild(Object parent, int index) {
        File directory = (File) parent;
        String[] directoryMembers = directory.list();
        return (new File(directory, directoryMembers[index]));
    }

    @Override
    public int getChildCount(Object parent) {
        File fileSystemMember = (File) parent;
        if (fileSystemMember.isDirectory()) {
            String[] directoryMembers = fileSystemMember.list();
            return directoryMembers.length;
        } 
        else {  return 0;  }
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        File directory = (File) parent;
        File directoryMember = (File) child;
        String[] directoryMemberNames = directory.list();
        int result = -1;
        int buf=directoryMemberNames.length;
        for (int i = 0; i < buf; ++i) {
            if (directoryMember.getName().equals(directoryMemberNames[i])) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean isLeaf(Object node) {
        return ((File) node).isFile();
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        if (l != null && !listeners.contains(l)) {
            listeners.addElement(l);
        }
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        if (l != null) {
            listeners.removeElement(l);
        }
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {       }
    
    /////////////////////////////////////////////////////////
    
    public String[] getAllrootsString() {
        return (String[]) allrootsString.toString().split(",");
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public File[] getAllroots() {
        return allroots;
    }

    public void setAllroots(File[] allroots) {
        this.allroots = allroots;
    }

}
