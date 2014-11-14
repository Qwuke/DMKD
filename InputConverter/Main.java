
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        //Edit these variables before you run the code
        int len = 12;//length of your vector, look for 'len:#' in the input file
        String outputGpuPath = "C:\\Users\\Tristan\\Documents\\DMKD\\GPU_Kmeans\\GPU_Kmeans\\vectors.txt";//Name and path of output file for GPU Kmeans
        String outputSparkPath = "C:\\Users\\Tristan\\Documents\\DMKD\\Spark_Kmeans\\Spark_Kmeans\\vectors.txt";//Name and path of output file for Spark Kmeans
        String outputMultiPath = "C:\\Users\\Tristan\\Documents\\DMKD\\Spark_Kmeans\\Spark_Kmeans\\vectors1.txt";
        String inputPath = "C:\\Users\\Tristan\\Documents\\DMKD\\wine.txt";//Name and path of input file
        //I can explain the rest of program later, but it does require the complexity that it has
        try {
            File file = new File(inputPath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer gpuBuffer = new StringBuffer();
            StringBuffer sparkBuffer = new StringBuffer();
            StringBuffer multiBuffer = new StringBuffer();
            String line;
            String[] arr;
            String updatedLine;
            String adjustedLine;
            String multiCoreMapRLine;
            int n = 0;
            while ((line = bufferedReader.readLine()) != null) {
                //updatedLine = line.replaceAll("^.*?(?=len:\\s)|len:\\s\\d\\d?\\d?\\d?\\d?\\d?\\d?;\\s|;",""); this was for converting newsgroup data
                updatedLine = line.replaceFirst("\\d\\d?\\d?\\d?\\d?\\d?\\d?\\d?\\d?\\s","");
                updatedLine = updatedLine.replaceAll("\\d\\d?\\d?\\d?\\d?\\d?\\d?\\d?\\d?:","");
                System.out.println(updatedLine);
                arr = updatedLine.split("\\s");
                int i = 0;
                int r = 0;
                StringBuffer stringBuffer = new StringBuffer();
                while(i < len) {
                    stringBuffer.append(i+":"+arr[i]+"; ");
                    /**
                    if (i - r < arr.length) {
                        int index = arr[i-r].indexOf(":");
                        if (Integer.parseInt(arr[i - r].substring(0, index)) == i) {
                            stringBuffer.append(arr[i-r] + "; ");

                        } else {
                            stringBuffer.append(i + ":0; ");
                            r++;
                        }
                    } else {
                        stringBuffer.append(i + ":0; ");
                    }*/
                    System.out.println(i);
                    i++;
                }

                multiCoreMapRLine = stringBuffer.toString();
                adjustedLine = multiCoreMapRLine.replaceAll("\\d\\d?\\d?\\d?\\d?\\d?\\d?:|;","");//Comment these lines out if you want vectors to be formatted with dimension numbers

                multiBuffer.append("key: a" + n + "; value: " + n + "; len: " + len + "; ");
                multiBuffer.append(multiCoreMapRLine);
                multiBuffer.append("\n");
                gpuBuffer.append(n + " ");
                gpuBuffer.append(adjustedLine);
                gpuBuffer.append("\n");
                sparkBuffer.append(adjustedLine);
                sparkBuffer.append("\n");
                System.out.println(n);
                n++;
            }
            fileReader.close();
            //System.out.println("Contents of file:");
            //System.out.println(stringBuffer.toString());
            try {
                File outputMulti = new File(outputMultiPath);
                FileWriter fileWriter = new FileWriter(outputMulti);
                fileWriter.write(multiBuffer.toString());
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
