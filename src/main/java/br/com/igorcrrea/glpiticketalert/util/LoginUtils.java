package br.com.igorcrrea.glpiticketalert.util;

import br.com.igorcrrea.glpiticketalert.service.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class LoginUtils {

    private static final File file = new File("./loginInfo.iac");

    public static void writeFile(LoginInfosDTO infos) {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print(JsonParser.creteConfigJson(infos));
        }catch(Exception e){
            createFile();
            writeFile(infos);
        }
    }

    public static LoginInfosDTO readFile() {
        try(FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)){
            return JsonParser.readConfig(br.readLine());
        }catch (Exception e){
            return new LoginInfosDTO();
        }
    }


    private static void createFile() {
        try {
            file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
