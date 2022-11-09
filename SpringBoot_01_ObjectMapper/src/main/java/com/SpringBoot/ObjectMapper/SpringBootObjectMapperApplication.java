package com.SpringBoot.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.SpringBoot.ObjectMapper.bean.Employee;
import com.SpringBoot.ObjectMapper.bean.Item;
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
			throws StreamReadException, DatabindException, JsonProcessingException, IOException {
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
		 */
		
		 System.out.println(om.writeValueAsString(getListFromJson2()));
	}

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

}
