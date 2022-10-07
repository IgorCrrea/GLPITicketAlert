package br.com.igorcrrea.glpiticketalert.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class LoginUtils {

    private static File file = new File("./loginInfo.txt");

    public static void writeFile(LoginInfosDTO infos) {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println(infos.getUrl());
            writer.println(infos.getAppToken());
            writer.print(infos.getUserToken());
        }catch(Exception e){
            createFile();
            writeFile(infos);
        }
    }


    public static LoginInfosDTO readFile() {
        try(FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)){
            String line;
            List<String> list = new ArrayList<String>();
            while((line = br.readLine()) != null){
                list.add(line);
            }
            return new LoginInfosDTO(list.get(0),list.get(1),list.get(2));
        }catch (Exception e){
            return new LoginInfosDTO();
        }
    }


    private static void createFile() {
        try {
            file.createNewFile();
        } catch (IOException ex) {

        }
    }

}
