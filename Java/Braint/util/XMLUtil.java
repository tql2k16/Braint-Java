package Braint.util;

import java.io.File;
import java.io.IOException;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class XMLUtil {

	

	public static <T> void serializeObject(T objectToSerialize, String fileName) {

		Serializer serializer = new Persister();

		String filename = fileName;

		if (!filename.contains(".xml"))
			filename += ".xml";

		File result = new File("./settings/" + filename);

		if (!result.exists()) {
			try {
				result.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		try {
			serializer.write(objectToSerialize, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static <T> T deserialzeObject(T type, String fileName){
		
		Serializer serializer = new Persister();
		
		String filename = fileName;

		if (!filename.contains(".xml"))
			filename += ".xml";

		File source = new File("./settings/" + filename);
		
		T obj = null;

		try {
			obj = serializer.read(type, source);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj;
		
	}

}
