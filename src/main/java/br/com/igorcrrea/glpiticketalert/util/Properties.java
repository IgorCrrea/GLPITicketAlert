package br.com.igorcrrea.glpiticketalert.util;

import java.io.FileInputStream;

public class Properties {

	public static java.util.Properties getProp() {

		try (FileInputStream file = new FileInputStream("./config.properties")) {
			java.util.Properties props = new java.util.Properties();
			props.load(file);
			return props;
			
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

}
