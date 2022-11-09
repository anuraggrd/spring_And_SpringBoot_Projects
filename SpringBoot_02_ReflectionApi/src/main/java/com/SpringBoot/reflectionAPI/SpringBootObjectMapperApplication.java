package com.SpringBoot.reflectionAPI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.SpringBoot.reflectionAPI.bean.Employee;
import com.SpringBoot.reflectionAPI.bean.Item;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class SpringBootObjectMapperApplication {

	public static final ObjectMapper om = new ObjectMapper();

	public static void main(String[] args)
			throws StreamReadException, DatabindException, JsonProcessingException, IOException, ClassNotFoundException {
		SpringApplication.run(SpringBootObjectMapperApplication.class, args);

		System.out.println("Hello world");

		/*
		 * writeJsonString(); readJsonString(); readDataFromJsonWithoutBeanClass();
		 * 
		 * System.out.println(om.writeValueAsString(getListFromJson()));
		 * 
		 * System.out.println(om.writeValueAsString(getItemFromJson()));
		 * 
		 * getItemFromJson2();
         * 	getItemFromJson3();
		 * System.out.println(om.writeValueAsString(getListFromJson2()));
		 */
		
		//System.out.println(om.writeValueAsString(getObjectFromJson()));
		reflectionMethod2();
		 
		 
	}
	/*String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
    Map<String, Object> map = objectMapper.readValue(json, new TypeReference<Map<String,Object>>(){});
  
  */

	public static void writeJsonString() {
		try {

			Employee emp = new Employee("Anurag", "Giridih", "HOD", "CSE");

			om.writeValue(new File("target\\emp2.json"), emp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void readJsonString() {
		String str = "{\"name\":\"Anurag\",\"city\":\"Giridih\",\"designation\":\"HOD\",\"department\":\"CSE\"}";

		// ObjectMapper om = new ObjectMapper();

		try {
			Employee emp = om.readValue(str, Employee.class);
			System.out.println(emp);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void readDataFromJsonWithoutBeanClass() {
		String str = "{\"name\":\"Anurag\",\"city\":\"Giridih\",\"designation\":\"HOD\",\"department\":\"CSE\"}";

		try {

			// ObjectMapper om = new ObjectMapper();
			JsonNode readTree = om.readTree(str);

			String name = readTree.get("name").asText();
			String city = readTree.get("city").asText();
			String designation = readTree.get("designation").asText();
			String department = readTree.get("department").asText();

			om.writeValue(new File("target\\empTreeNode2.txt"),
					name + "," + city + "," + designation + "," + department);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@SuppressWarnings("resource")
	public static <T> List<T> getListFromJson() throws StreamReadException, DatabindException, IOException {
		// ObjectMapper om = new ObjectMapper();
		TypeReference<List<T>> typeReference = null;
		String path = "src/main/resources/ItemList.json";
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(path));
			typeReference = new TypeReference<List<T>>() {
			};

		} catch (Exception e) {
			e.printStackTrace();
		}

		return om.readValue(inputStream, typeReference);
	}

	public static List<Item> getItemFromJson() throws StreamReadException, DatabindException, IOException {
		// ObjectMapper om = new ObjectMapper();
		TypeReference<List<Item>> typeReference = null;
		String path = "src/main/resources/ItemList.json";
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(path));
			typeReference = new TypeReference<List<Item>>() {};

		} catch (Exception e) {
			e.printStackTrace();
		}

		return om.readValue(inputStream, typeReference);
	}

	public static void getItemFromJson2() throws StreamReadException, DatabindException, IOException {
		// ObjectMapper om = new ObjectMapper();
		String path = "src/main/resources/ItemList2.json";
		
		try {
			String result = new String(Files.readAllBytes(Paths.get(path)));
			// System.out.println(result);
			Item readValue = om.readValue(result, Item.class);

			System.out.println(readValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	
	
	public static void getItemFromJson3() throws StreamReadException, DatabindException, IOException {
		String path = "src/main/resources/ItemList.json";
		TypeReference<List<Item>> typeReference = null;
		try {
			String result = new String(Files.readAllBytes(Paths.get(path)));
			System.out.println(result);
			typeReference = new TypeReference<List<Item>>() {};
			List<Item> readValue = om.readValue(result, typeReference);

			for (Item item : readValue) {
				System.out.println(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	
	
	public static <T> List<T> getListFromJson2() throws StreamReadException, DatabindException, IOException {
		// ObjectMapper om = new ObjectMapper();
		TypeReference<List<T>> typeReference = null;
		String path = "src/main/resources/ItemList.json";
		FileInputStream inputStream = null;
		String result = new String(Files.readAllBytes(Paths.get(path)));
		try {
			//inputStream = new FileInputStream(new File(path));
			typeReference = new TypeReference<List<T>>() {
			};

		} catch (Exception e) {
			e.printStackTrace();
		}
		List<T> readValue = om.readValue(result, typeReference);
		for (T t : readValue) {
			System.out.println(t);
			
		}
		return om.readValue(result, typeReference);
	}
	
	public static <T>  T getObjectFromJson() throws IOException {
		// ObjectMapper om = new ObjectMapper();
		TypeReference<T> typeReference = null;
		String path = "src/main/resources/ItemList2.json";
		FileInputStream inputStream = null;
		String result = new String(Files.readAllBytes(Paths.get(path)));
		try {
			//inputStream = new FileInputStream(new File(path));
			typeReference = new TypeReference<T>() {
			};

		} catch (Exception e) {
			e.printStackTrace();
		}
		T randomClass = om.readValue(result, typeReference);
		
		
		
			System.out.println(randomClass);
			
		
		return om.readValue(result, typeReference);
	}
	public static <T>  T reflectionMethod2() throws IOException, ClassNotFoundException {
		// ObjectMapper om = new ObjectMapper();
		TypeReference<T> typeReference = null;
		String path = "src/main/resources/ItemList2.json";
		FileInputStream inputStream = null;
		String result = new String(Files.readAllBytes(Paths.get(path)));
		try {
			//inputStream = new FileInputStream(new File(path));
			typeReference = new TypeReference<T>() {
			};

		} catch (Exception e) {
			e.printStackTrace();
		}
		T randomClass = om.readValue(result, typeReference);
		
		Class<?> forName = Class.forName(randomClass.getClass().getName());
		Field[] fields = forName.getFields();
		System.out.println("@@@----------@@@");
		for (Field field : fields) {
			System.out.println(field.getType());
			System.out.println(field.getGenericType());
			
		}
		System.out.println("@@@----------@@@");
		
		String name = randomClass.getClass().getName();
		System.out.println(name+"@@@@@@@@");
		Class<? extends Object> class1 = randomClass.getClass();
		Method[] methods = class1.getMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
		}
		
		System.out.println("-------------------------------------");
		Field[] declaredFields = class1.getDeclaredFields();
		for (Field field : declaredFields) {
			System.out.println(field.getName());
		}
		System.out.println("-------------------------------------");
			//System.out.println(randomClass);
			
		
		return om.readValue(result, typeReference);
	}
	
	
	public static <T>  T reflectionMethod5() throws IOException {
		// ObjectMapper om = new ObjectMapper();
		TypeReference<T> typeReference = null;
		String path = "src/main/resources/ItemList2.json";
		FileInputStream inputStream = null;
		String result = new String(Files.readAllBytes(Paths.get(path)));
		try {
			//inputStream = new FileInputStream(new File(path));
			typeReference = new TypeReference<T>() {
			};

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    Map<String, Object> map = om.readValue(result, new TypeReference<Map<String,Object>>(){});
	    
	    	    
	    
	    System.out.println(map.getClass().getSimpleName());
	    
		
		  for (Map.Entry<String, Object> m : map.entrySet()) { 
			  //Object object =		  m.getValue(); 
			 // System.out.println(object.getClass().getSimpleName());
			  System.out.println(m.getKey() +" "+ m.getValue());
		  
		  }
		 
	    /*
		//T randomClass = om.readValue(result, typeReference);
		
		String name = randomClass.getClass().getName();
		System.out.println(name+"@@@@@@@@");
		Class<? extends Object> class1 = randomClass.getClass();
		Method[] methods = class1.getMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
		}
		
		System.out.println("-------------------------------------");
		Field[] declaredFields = class1.getDeclaredFields();
		for (Field field : declaredFields) {
			System.out.println(field.getName());
		}
		System.out.println("-------------------------------------");
			//System.out.println(randomClass);
		
		*/
			
		
		return om.readValue(result, typeReference);
	}

	
	public static void reflectionMethod1()
	{
		Employee emp = new Employee("Anurag", "Giridih", "HOD", "CSE");
		
		Class<? extends Employee> empClass = emp.getClass();
		
		Method[] methods = empClass.getMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
		}
		System.out.println("-------------------------------------");
		Field[] declaredFields = empClass.getDeclaredFields();
		for (Field field : declaredFields) {
			
			System.out.println(field.getName());
		}
		System.out.println("-------------------------------------");
		
	}
	
	public static void reflectionMethod3() throws ClassNotFoundException
	{
		Class<?> classObject = Class.forName("/SpringBoot_ReflectionApi/src/main/java/com/SpringBoot/reflectionAPI/bean/Employee.java");
		System.out.println("1 ->"+ classObject.getSimpleName());
		System.out.println("2 ->" + classObject.getName());
	}
	
	public static void reflectionMethod4() throws ClassNotFoundException
	{
		Class <Employee> empobj = Employee.class;
		
		System.out.println("1 ->"+ empobj.getSimpleName());
		System.out.println("2 ->" + empobj.getName());
	}
	
	

}
