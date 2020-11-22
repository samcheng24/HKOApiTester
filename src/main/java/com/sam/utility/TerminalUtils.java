package com.sam.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TerminalUtils {
    private static List<String> commands;
    private static ThreadedStreamHandler iSH;
    private static BufferedReader stdError;
    private static String DEFAULT_DIRECTORY = "/src/main/java/resources/";

    /**
     * This method will execute terminal commands on the Binary File Name Passed
     *
     * @param binaryFileName file being processed
     * @return String whether or not executed command succeeds
     * @throws InterruptedException
     * @throws IOException
     */
    public static String executeCommand(String binaryFileName) throws InterruptedException, IOException {
        // Execute terminal command to encrypt csv file
        commands = new ArrayList<String>();
        commands.add("java");
        commands.add("-jar");
        commands.add(binaryFileName +".jar");

        executeCommand();
        String strErr = getStandardErrorFromCommand();
        StringBuilder strOutput = getStandardOutputFromCommand();

        if(strErr!=null) return strErr;
        else return strOutput.toString();
    }

    private static int executeCommand() throws IOException, InterruptedException {
        int exitValue = -99;

        try {
            ProcessBuilder pb = new ProcessBuilder(commands);
            pb.directory(new File(DEFAULT_DIRECTORY));
            Process process = pb.start();
            exitValue = process.waitFor();

            stdError = new BufferedReader(new
                    InputStreamReader(process.getErrorStream()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return exitValue;
        }
    }

    /**
     * Get the standard output (stdout) from the command you just exec'd.
     */
    private static StringBuilder getStandardOutputFromCommand() {
        return iSH.getOutputBuffer();
    }

    /**
     * Get the standard error (stderr) from the command you just exec'd.
     */
    private static String getStandardErrorFromCommand() {
        StringBuffer output = new StringBuffer();

        String line = "";
        try {
            while ((line = stdError.readLine()) != null) {
                output.append(line + "\n");
            }
        }catch(IOException ex){}

        System.out.println(output.toString());
        return output.toString();
    }
}






