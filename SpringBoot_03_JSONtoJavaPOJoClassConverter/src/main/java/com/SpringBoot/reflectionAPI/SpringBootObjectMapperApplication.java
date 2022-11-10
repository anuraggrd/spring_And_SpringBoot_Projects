package com.SpringBoot.reflectionAPI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

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

import com.SpringBoot.reflectionAPI.bean.Car;
import com.SpringBoot.reflectionAPI.bean.Demo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
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

		//generateJavaClass();
		//readValueFromJsonFile();
		//reflectionAPI();
		traverseJsonObject();
		 
		 
	}
	
	public static void traverseJsonObject() throws IOException, ClassNotFoundException
	{
		String path = "src/main/resources/demo.json";
		String result = new String(Files.readAllBytes(Paths.get(path)));
		Map<String, Object> map = om.readValue(result, new TypeReference<Map<String,Object>>(){});
		
		 for (Map.Entry<String, Object> m : map.entrySet()) {
			 System.out.println(m.getKey());
			 
			 String upperCase = m.getKey().substring(0,1).toUpperCase();
			  String substring = m.getKey().substring(1);
			  String name = upperCase + substring;
			  System.out.println(name);
			  String source = "com.SpringBoot.reflectionAPI.bean.";
			 String className =  source + name ;
			Class<?> classObject = Class.forName(className);
			/*
			 * System.out.println("1 ->"+ classObject.getSimpleName());
			 * System.out.println("2 ->" + classObject.getName());
			 */
			Field[] declaredFields = classObject.getDeclaredFields();
			for (Field field : declaredFields) {
				System.out.println(field.getName() + " -> "+field.getType());
				
				
			}
			
				 
		 }
	}
	
	
	public static void reflectionAPI() throws ClassNotFoundException
	{
		
		
		
		Class<?> demo = Demo.class;
		Field[] declaredFields = demo.getDeclaredFields();
		for (Field field : declaredFields) {
			System.out.println(field.getGenericType());
			//System.out.println(field.getName());
		}
	}
	
	public static void readValueFromJsonFile() throws StreamReadException, DatabindException, IOException
	{
		TypeReference<Demo> ref = new TypeReference<Demo>(){};
		InputStream in = new FileInputStream("src/main/resources/demo.json");
    	Demo demo = om.readValue(in, ref);
    	System.out.println( "Person name:"+demo.getPerson().getName() );
    	List<Car> cars = demo.getPerson().getCars().getCar();
    	for(Car c : cars) {
    		System.out.println("Car brand is "+c.getBrand());
    	}
		
	}
	public static void generateJavaClass()
	{
		String packageName = "com.baeldung.jsontojavaclass.pojo";
        String basePath = "src/main/resources";
        File inputJson = new File(basePath + File.separator + "demo.json");
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
