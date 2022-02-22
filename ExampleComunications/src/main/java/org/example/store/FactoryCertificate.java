package org.example.store;

import java.io.*;

public class FactoryCertificate {

    public static void genBasicCertificate(String path){
        ProcessBuilder processBuilder=new ProcessBuilder("cmd","/c", "dir");

        try {

            Process process = processBuilder.start();


            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
