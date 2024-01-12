import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit;
    JMenuItem newFile,openFile,saveFile;
    JMenuItem cut,copy,paste,selectAll,close;
    JTextArea textArea;
    TextEditor(){
        frame = new JFrame();

        menuBar = new JMenuBar();

        textArea=new JTextArea();

        file=new JMenu("File");
        edit=new JMenu("Edit");

        newFile=new JMenuItem("New Window");
        openFile=new JMenuItem("Open FIle");
        saveFile=new JMenuItem("Save File");

        //adding actionlistener to menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("close");

        //adding action listener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        menuBar.add(file);
        menuBar.add(edit);

        frame.setJMenuBar(menuBar);
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //add text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        //create scroll pane
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //adding scroll pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);


        frame.setBounds(100,100,800,400);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setTitle("Text Editor");


    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close) {
            System.exit(0);
        }

        if(actionEvent.getSource()==openFile){
            JFileChooser fileChooser= new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //if we have clicked open button

            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //Getting selected file
                File file = fileChooser.getSelectedFile();
                //get the path of selected file

                String filePath=file.getPath();
                try {
                    //initialising file reader
                    FileReader fileReader=new FileReader(filePath);

                    BufferedReader bufferedReader=new BufferedReader(fileReader);
                    String intermediate="",output="";

                    //read contents of file line by line
                    while((intermediate= bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //set the output string to text area
                    textArea.setText(output);
                } catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }

            }
        }
        if(actionEvent.getSource()==saveFile){
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption=fileChooser.showSaveDialog(null);
            //check if we clicked on save button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //create a new file with choosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //initialise file writer
                    FileWriter fileWriter=  new FileWriter(file);
                    //initialising buffered writer
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                    //write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==newFile){
            TextEditor newTextEditor=new TextEditor();
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor=new TextEditor();

    }
}