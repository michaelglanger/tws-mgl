package com.mgl.old;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import com.mgl.Data;

public class Serializer {
	
	private static String FOLDER = "serialized/";
	private static Serializer instance;
	
	private Serializer() {
		
	}
	
	public static Serializer getInstance() {
		if (instance == null) {
			instance = new Serializer();
		}
		return instance;
	}

	public Serializable get(String name) {
		Data recoveredData = null;
		try(
			InputStream file = new FileInputStream(FOLDER + name + ".ser");
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream (buffer);
		){
			recoveredData = (Data)input.readObject();
		} catch (FileNotFoundException ex) {
		
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();		
		} catch (IOException ex){
			ex.printStackTrace();
	    }
		return recoveredData;
	}

	public void put(String name, Data data) {
	    try (
	      OutputStream file = new FileOutputStream(FOLDER + name + ".ser");
	      OutputStream buffer = new BufferedOutputStream(file);
	      ObjectOutput output = new ObjectOutputStream(buffer);
	    ) {
	      output.writeObject(data);
	    }  catch(IOException ex){
	    	ex.printStackTrace();
	    }
		
	}

}
