package com.SpringBoot.reflectionAPI;

import java.io.File;
import java.io.IOException;
import java.net.URL;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.codemodel.JCodeModel;

@SpringBootApplication
public class SpringBootObjectMapperApplication {

	public static final ObjectMapper om = new ObjectMapper();
//String path = "src/main/resources/ItemList2.json";
	public static void main(String[] args)
			throws StreamReadException, DatabindException, JsonProcessingException, IOException, ClassNotFoundException {
		SpringApplication.run(SpringBootObjectMapperApplication.class, args);

		System.out.println("Hello world");

		String packageName = "com.baeldung.jsontojavaclass.pojo";
        String basePath = "src/main/resources";
        File inputJson = new File(basePath + File.separator + "ItemList2.json");
        File outputPojoDirectory = new File(basePath + File.separator + "convertedPojo");
        outputPojoDirectory.mkdirs();
        try {
            new SpringBootObjectMapperApplication().convertJsonToJavaClass(inputJson.toURI().toURL(), outputPojoDirectory, packageName, inputJson.getName().replace(".json", ""));
        } catch (IOException e) {
            System.out.println("Encountered issue while converting to pojo: " + e.getMessage());
            e.printStackTrace();
        }
		 
		 
	}
	
	 public void convertJsonToJavaClass(URL inputJsonUrl, File outputJavaClassDirectory, String packageName, String javaClassName) throws IOException {
	        JCodeModel jcodeModel = new JCodeModel();

	        GenerationConfig config = new DefaultGenerationConfig() {
	            @Override
	            public boolean isGenerateBuilders() {
	                return true;
	            }

	            @Override
	            public SourceType getSourceType() {
	                return SourceType.JSON;
	            }
	        };

	        SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
	        mapper.generate(jcodeModel, javaClassName, packageName, inputJsonUrl);

	        jcodeModel.build(outputJavaClassDirectory);
	    }

}
