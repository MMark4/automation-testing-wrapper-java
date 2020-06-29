package saetabis.automation.testingWrapper.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Component;
import saetabis.automation.testingWrapper.models.IDataTestModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@Component
public class DataReader<T extends IDataTestModel> {

	public T read(Class<T> theClass, String dataFile) {
		ObjectMapper mapper = new ObjectMapper();

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(dataFile).getFile());

		try {
			return mapper.readValue(file, theClass);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> readStringArray(String dataFile)
	{
		ObjectMapper mapper = new ObjectMapper();

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(dataFile).getFile());

		try {
			String[] array  = mapper.readValue(file, String[].class);
			return Arrays.asList(array);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public File getFile(String pathName) {
		ClassLoader classLoader = getClass().getClassLoader();
		return new File(classLoader.getResource(pathName).getFile());
	}

	public String readAsString(String dataFile) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(dataFile).getFile());

		try {
			return new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String readObjToJsonString(Object theClass) {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			return ow.writeValueAsString(theClass);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
