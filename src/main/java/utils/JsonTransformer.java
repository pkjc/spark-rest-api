package utils;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import spark.ResponseTransformer;
/*
 * Implements ResponseTransformer from Spark framework and converts POJOs to JSON and vice versa
 */
public class JsonTransformer implements ResponseTransformer {
	@Override
	public String render(Object model) throws Exception {
		try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, model);
            return sw.toString();
        } catch (IOException e) {
            throw new RuntimeException("IOEXception while mapping object (" + model + ") to JSON");
        }
	}

}