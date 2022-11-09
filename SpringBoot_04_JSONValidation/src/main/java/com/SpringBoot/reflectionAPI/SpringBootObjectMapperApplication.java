package com.SpringBoot.reflectionAPI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.SourceType;
import org.jsonschema2pojo.rules.RuleFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.codemodel.JCodeModel;

@SpringBootApplication
public class SpringBootObjectMapperApplication {

	public static final ObjectMapper om = new ObjectMapper();
//
	public static void main(String[] args)
			throws StreamReadException, DatabindException, JsonProcessingException, IOException, ClassNotFoundException {
		SpringApplication.run(SpringBootObjectMapperApplication.class, args);

		System.out.println("Hello world");
		String path = "src/main/resources/ItemList.json";
		String result = new String(Files.readAllBytes(Paths.get(path)));
		System.out.println(isValid(result));
		
		 
		 
	}
	
	 public static boolean isValid(String json) {
	        try {
	        	om.readTree(json);
	        } catch (JacksonException e) {
	            return false;
	        }
	        return true;
	    }
	

}
