
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        //Edit these variables before you run the code
        int len = 5000;//length of your vector, look for 'len:#' in the input file
        String outputGpuPath = "C:\\Users\\Tristan\\Documents\\GitHub\\DMKD\\InputConverter\\vectors1.txt";//Name and path of output file for GPU Kmeans
        String outputSparkPath = "C:\\Users\\Tristan\\Documents\\GitHub\\DMKD\\InputConverter\\vectors2.txt";//Name and path of output file for Spark Kmeans
        String inputPath = "C:\\Users\\Tristan\\Documents\\GitHub\\DMKD\\InputConverter\\vectors.txt";//Name and path of input file
        //I can explain the rest of program later, but it does require the complexity that it has
        try {
            File file = new File(inputPath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer gpuBuffer = new StringBuffer();
            StringBuffer sparkBuffer = new StringBuffer();
            String line;
            String[] arr;
            String updatedLine;
            String adjustedLine;
            int n = 0;
            while ((line = bufferedReader.readLine()) != null) {
                updatedLine = line.replaceAll("^.*?(?=0:)|;","");
                arr = updatedLine.split("\\s");
                int i = 0;
                int r = 0;
                StringBuffer stringBuffer = new StringBuffer();
                while(i < len) {
                    if (i - r < arr.length) {
                        int index = arr[i-r].indexOf(":");
                        if (Integer.parseInt(arr[i - r].substring(0, index)) == i) {
                            stringBuffer.append(arr[i-r] + " ");

                        } else {
                            stringBuffer.append(i + ":0 ");
                            r++;
                        }
                    } else {
                        stringBuffer.append(i + ":0 ");
                    }
                    i++;
                }

                adjustedLine = stringBuffer.toString();
                adjustedLine = adjustedLine.replaceAll("\\s\\d\\d?\\d?\\d?\\d?\\d?\\d?:"," ");//Comment these lines out if you want vectors to be formatted with dimension numbers
                adjustedLine = adjustedLine.replaceFirst("0:","");

                gpuBuffer.append(n + " ");
                gpuBuffer.append(adjustedLine);
                gpuBuffer.append("\n");
                sparkBuffer.append(adjustedLine);
                sparkBuffer.append("\n");
                n++;
            }
            fileReader.close();
            //System.out.println("Contents of file:");
            //System.out.println(stringBuffer.toString());
            try {
                File outputGpu = new File(outputGpuPath);
                FileWriter fileWriter = new FileWriter(outputGpu);
                fileWriter.write(gpuBuffer.toString());
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                File outputSpark = new File(outputSparkPath);
                FileWriter fileWriter = new FileWriter(outputSpark);
                fileWriter.write(sparkBuffer.toString());
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
