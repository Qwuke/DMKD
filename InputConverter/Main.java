package net.qwuke;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\Qwuke\\IdeaProjects\\InputConverter1\\src\\net\\qwuke\\vectors.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            int len = 5000;//length of your vector, look for len
            String line;
            String[] arr;
            String updatedLine;
            int n = 1;
            while ((line = bufferedReader.readLine()) != null) {
                updatedLine = line.replaceAll("^.*?(?=0:)|;","");
                arr = updatedLine.split(" ");
                int i = 0;
                while(i < len - 1){
                    int index = arr[i-1].indexOf(":");
                    if(Integer.parseInt(arr[i-1].substring(0,index)) == i){

                    }
                }
                //updatedLine = updatedLine.replaceAll("\\s\\d\\d?\\d?\\d?\\d?\\d?\\d?:"," ");
                //stringBuffer.append(n + " ");
                stringBuffer.append(updatedLine);

                stringBuffer.append("\n");
                n++;
            }
            fileReader.close();
            System.out.println("Contents of file:");
            System.out.println(stringBuffer.toString());
            try {
                File output = new File("C:\\Users\\Qwuke\\IdeaProjects\\InputConverter1\\src\\net\\qwuke\\vectors1.txt");
                FileWriter fileWriter = new FileWriter(output);
                fileWriter.write(stringBuffer.toString());
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
