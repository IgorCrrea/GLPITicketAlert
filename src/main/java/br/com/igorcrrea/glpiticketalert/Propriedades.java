package br.com.igorcrrea.glpiticketalert;

import java.io.FileInputStream;
import java.util.Properties;

public class Propriedades {

	public static Properties getProp() {

		try (FileInputStream file = new FileInputStream("./config.properties")) {
			Properties props = new Properties();
			props.load(file);
			return props;
			
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

}
