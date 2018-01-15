package ch.juventus.se.kiosk.controller;

import java.io.*;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class FileHandler {

    private File inputFile, outputFile;

    public FileHandler(File inputFile, File outputFile){
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public String read(){
        String contentString = "";
        FileInputStream in = null;
        try {
            in = new FileInputStream(inputFile);

            System.out.println("Total file size to read (in bytes) : "
                    + in.available());

            int content;
            while ((content = in.read()) != -1) {
                // convert to char and display it
                contentString += (char) content;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return contentString;
    }

    public void write(String text){
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outputFile);
            byte[] texttInBytes = text.getBytes();
            byte[] defaultText = "Dies ist mein Output:\n".getBytes();
            out.write(defaultText);
            out.write(texttInBytes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
