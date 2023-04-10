import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
//declaring properties of text editor
    JFrame frame;

    JMenuBar menuBar;

    JMenu file,edit;

    //file menuitem
    JMenuItem newfile,openfile,savefile;
    //edit menuitem
    JMenuItem copy,cut,paste,selectAll,close;

    JTextArea textarea;

    TextEditor(){
        //Intializing JFrame
        frame = new JFrame();

        //intiallizing textarea
        textarea = new JTextArea();

        //Intiallizing JMenuBar
        menuBar = new JMenuBar();

        //Intiallizing JMenu
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Intiallizing JMenuitem to the file
        newfile  = new JMenuItem("New File");
        openfile = new JMenuItem("Open File");
        savefile = new JMenuItem("Save File");

        //add action listener to file menu item
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);

        //add JMenuitem to the file
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);

        // Intiallizing JMenuitem to the  edit
        copy = new JMenuItem("Copy");
        cut  = new JMenuItem("Cut");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("SelectAll");
        close = new JMenuItem("Close");

        //add actionlistener to the edit menu item
        copy.addActionListener(this);
        cut.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //add JMenuItem to the edit
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // add menu to the menubar
        menuBar.add(file);
        menuBar.add(edit);

        //set menubar in frame
        frame.setJMenuBar(menuBar);

       //Create content panel
        JPanel jPanel = new JPanel();
        jPanel.setBorder(new EmptyBorder(5,5,5,5));
        jPanel.setLayout(new BorderLayout(0,0));
        //Add text Area to panel
        jPanel.add(textarea, BorderLayout.CENTER);
        //create scroll panel
        JScrollPane jScrollPane = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add jpanel to the jScrollPane
        jPanel.add(jScrollPane);
        //add jPanel to the frame
        frame.add(jPanel);

        //set dimension of JFrame
        frame.setBounds(100,100,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);  //for visibility of Jframe
        frame.setLayout(null);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
     if(actionEvent.getSource()==copy){
         //performe copy operation
         textarea.copy();
     }
     if(actionEvent.getSource()==cut){
         //performe cut operation
         textarea.cut();
     }
        if(actionEvent.getSource()==paste){
            //performe paste operation
            textarea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            //performe selectAll opration
            textarea.selectAll();
        }
        if(actionEvent.getSource()==close){
            // perfome close operation
            System.exit(0);
        }
        if(actionEvent.getSource()==openfile){
            //open file chooser
            JFileChooser fileChooser =new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);

            //If we clicked open button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //Getting selectfile
                File file = fileChooser.getSelectedFile();
                // Get path of slected file
                String filePath = file.getPath();
                try{
                    //Intiallize the file reder
                    FileReader fileReader = new FileReader(filePath);
                    //Intiallizing BufferReader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate ="", output = "";
                    //Read the content of file line by line
                    while((intermediate = bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //set the output string to the textArea
                    textarea.setText(output);
                } catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource()==savefile){
            //Intiallize file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            //get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //check if we clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //Create a new file with choosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //Intiallize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //Intiallize Buffer Writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write a content of text area to file
                    textarea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }

            }
        }

        if(actionEvent.getSource()==newfile){
            TextEditor newTextEditor = new TextEditor();
        }

        }
    public static void main(String[] args) {
      TextEditor textEditor = new TextEditor();
    }
}
