
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        int len = 5000;//length of your vector, look for 'len:#' in the input file
        boolean forGpuCode = true;//Whether or not to have line numbers in front of the output text
        String outputPath = "C:\\Users\\Tristan\\Documents\\GitHub\\DMKD\\InputConverter\\vectors1.txt";//Name and path of output file
        String inputPath = "C:\\Users\\Tristan\\Documents\\GitHub\\DMKD\\InputConverter\\vectors.txt";//Name and path of input file
        try {
            File file = new File(inputPath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            String[] arr;
            String updatedLine;
            String adjustedLine;
            int n = 1;
            while ((line = bufferedReader.readLine()) != null) {
                updatedLine = line.replaceAll("^.*?(?=0:)|;","");
                arr = updatedLine.split("\\s");
                int i = 0;
                int r = 0;
                StringBuilder stringBuilder = new StringBuilder();
                while(i < len) {
                    if (i - r < arr.length) {
                        int index = arr[i-r].indexOf(":");
                        if (Integer.parseInt(arr[i - r].substring(0, index)) == i) {
                            stringBuilder.append(arr[i-r] + " ");

                        } else {
                            stringBuilder.append(i + ":0 ");
                            r++;
                        }
                    } else {
                        stringBuilder.append(i + ":0 ");
                    }
                    i++;
                }
                if(forGpuCode){
                    stringBuffer.append(n + " ");
                }
                adjustedLine = stringBuilder.toString();

                adjustedLine = adjustedLine.replaceAll("\\s\\d\\d?\\d?\\d?\\d?\\d?\\d?:"," ");//Comment these lines out if you want vectors to be formatted with dimension numbers
                adjustedLine = adjustedLine.replaceFirst("0:","");

                stringBuffer.append(adjustedLine);
                stringBuffer.append("\n");
                n++;
            }
            fileReader.close();
            //System.out.println("Contents of file:");
            //System.out.println(stringBuffer.toString());
            try {
                File output = new File(outputPath);
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
