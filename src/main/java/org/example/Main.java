package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};

        String fileName = "data.csv";
        String jsonFileName = "data.json";
        CSVparser csv = new CSVparser();
        List<Employee> staff = csv.parseCSV(columnMapping, fileName);
        String json = listToJson(staff);
        writeString(json, jsonFileName);

        String xmlFileName = "data2.xml";

        String json2FileName = "data2.json";
        XMLParser xml = new XMLParser();
        List<Employee> staff2 = xml.parseXML(columnMapping, xmlFileName);
        String json2 = listToJson(staff2);
        writeString(json2, json2FileName);

    }

    protected static String listToJson(List<Employee> staff) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();

        return gson.toJson(staff, listType);
    }

    protected static void writeString(String json, String jsonFileName) {
        try (FileWriter writer = new FileWriter(jsonFileName, false)) {
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}