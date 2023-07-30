import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
public class text_editor implements ActionListener{
    private JFrame  frame;
    private JMenuBar MenuBar;
    private  JMenu jmenufile,menuEdit, menuAbout;
    private JMenuItem  jMenuitemopen, jMenuitemnew,jmenusave,
    jmenusaveas,jMenutExit,jMenuCut,jMenucopy,jMenuPaste;
    private JTextArea jTextAreaMain;
    private String text;

    public text_editor(){
        frame = new JFrame("Text Editior");
        jTextAreaMain = new JTextArea();
        MenuBar = new JMenuBar();
    
 //menu
        jmenufile = new JMenu("File");
        menuEdit = new JMenu("Edit");
        menuAbout = new JMenu("About");
 // sub menu for file menu
    
        jMenuitemnew = new JMenuItem("New");
        jMenuitemopen = new JMenuItem("open");
        jmenusave = new JMenuItem("save");
        jmenusaveas = new JMenuItem("Saveas");
        jMenutExit = new JMenuItem("Exit");
 // sub menu item for edit
        jMenuCut = new JMenuItem("Cut");
        jMenucopy = new JMenuItem("copy");
        jMenuPaste = new JMenuItem("Paste");

        setAllMenuItems();
        setComponentsProperties();
        setMenuIteamListeners();
        setFrameProperties();
    }

    private void setAllMenuItems(){
        jmenufile.add(jMenuitemnew);
        jmenufile.add(jMenuitemopen);
        jmenufile.add(jmenusave);
        jmenufile.add(jmenusaveas);
        jmenufile.add(jMenutExit);

        menuEdit.add(jMenuCut);
        menuEdit.add(jMenucopy);
        menuEdit.add(jMenuPaste);

        MenuBar.add(jmenufile);
        MenuBar.add(menuEdit);
        MenuBar.add(menuAbout);

        jMenuitemnew.setBorder(new EmptyBorder(new Insets(5, 20, 5, 20)));
        jMenuitemopen.setBorder(new EmptyBorder(new Insets(5, 20, 5, 20)));
        jmenusave.setBorder(new EmptyBorder(new Insets(5, 20, 5, 20)));
        jmenusaveas.setBorder(new EmptyBorder(new Insets(5, 20, 5, 20)));
        jMenuCut.setBorder(new EmptyBorder(new Insets(5, 20, 5, 20)));
        jMenucopy.setBorder(new EmptyBorder(new Insets(5, 20, 5, 20)));
        jMenuPaste.setBorder(new EmptyBorder(new Insets(5, 20, 5, 20)));
        
        frame.setJMenuBar(MenuBar);
    }
    private void setMenuIteamListeners(){
        jMenutExit.addActionListener(this);
        jMenuCut.addActionListener(this);
        jMenucopy.addActionListener(this);
        jMenuPaste.addActionListener(this);
        jMenuitemopen.addActionListener(this);

        
    }

    private void setComponentsProperties(){
        jTextAreaMain = new JTextArea();
        JScrollPane scroll = new JScrollPane(jTextAreaMain,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(BorderLayout.CENTER,scroll);
    }

    private void setFrameProperties(){
    frame.setSize(500,500);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setUndecorated(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("New")){
            jTextAreaMain.setText("");
        }
        else if(e.getActionCommand().equals("open")){
            JFileChooser chooser = new JFileChooser("c:/");
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("only.txt files","txt");
            chooser.addChoosableFileFilter(restrict);
            int result = chooser.showOpenDialog(chooser);
            if(result == JFileChooser.APPROVE_OPTION){
            }
        }
        else if (e.getActionCommand().equals("save")){
            final JFileChooser saveAs = new JFileChooser();
            saveAs.setApproveButtonText("save");
            int actionDialog = saveAs.showOpenDialog(saveAs);
            if(actionDialog!=JFileChooser.APPROVE_OPTION){

                return;
            }
            File fileName = new File(saveAs.getSelectedFile() +".txt");
            BufferedWriter outFile = null;
            try{
                outFile = new BufferedWriter(new FileWriter(fileName));
                jTextAreaMain.write(outFile);
            }catch(IOException ae){
                ae.printStackTrace();
            }
        }
        else if(e.getActionCommand().equals("copy")){
            text = jTextAreaMain.getSelectedText();
            }
        else if(e.getActionCommand().equals("Paste")){
            jTextAreaMain.insert(text, jTextAreaMain.getCaretPosition());
        }
        else if(e.getActionCommand().equals("Cut")){
            text = jTextAreaMain.getSelectedText();
            jTextAreaMain.replaceRange("",jTextAreaMain.getSelectionStart(),jTextAreaMain.getSelectionEnd());
        }
        else if(e.getActionCommand().equals("Exit")){
            frame.dispose();
        }   
     }

    public static void main(String args[]){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e){

        }
        new text_editor();

    }
}
