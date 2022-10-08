package br.com.igorcrrea.glpiticketalert.util;

import br.com.igorcrrea.glpiticketalert.model.Configurations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class LoginUtils {

    private static final File file = new File("./loginInfo.iac");
    private static final JsonParser jsonParser = new JsonParser();

    public static void writeFile(Configurations infos) {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print(jsonParser.creteConfigJson(infos));
        }catch(Exception e){
            createFile();
            writeFile(infos);
        }
    }

    public static Configurations readFile() {
        try(FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)){
            return jsonParser.readConfig(br.readLine());
        }catch (Exception e){
            return new Configurations();
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
