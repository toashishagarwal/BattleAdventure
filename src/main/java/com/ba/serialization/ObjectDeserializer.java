package com.ba.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by ashishagarwal on 6/3/17.
 */
public class ObjectDeserializer {

	private final String filename = "config.dat";
	
	public Object deserialize()  {
		Object object = null;
		try {
			object = readFromFile();
		} catch (IOException | ClassNotFoundException e) {
			try {
				object = readFromClasspathResource();
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
		}
		return object;
	}

	private Object readFromFile() throws IOException, ClassNotFoundException {
		try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filename))) {
			return stream.readObject();
		}
	}

	private Object readFromClasspathResource() throws IOException, ClassNotFoundException {
		try (ObjectInputStream stream = new ObjectInputStream(ObjectDeserializer.class.getClassLoader().getResourceAsStream(filename))) {
			return stream.readObject();
		}
	}
}
