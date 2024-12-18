package org.example;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLParser {

    public List<Employee> parseXML(String[] columnMapping, String fileName) {

        List<Employee> employeeList = new ArrayList<>();

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(new File(fileName));
            Node root = doc.getDocumentElement();

            NodeList nodeList = root.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;

                    Map<String, String> valuesMap = new HashMap<>();
                    for (String column : columnMapping) {
                        valuesMap.put(column, element.getElementsByTagName(column).item(0).getTextContent());
                    }

                    // Создаем объект Employee
                    Employee employee = new Employee(
                            Long.parseLong(valuesMap.get("id")),
                            valuesMap.get("firstName"),
                            valuesMap.get("lastName"),
                            valuesMap.get("country"),
                            Integer.parseInt(valuesMap.get("age"))
                    );

                    employeeList.add(employee);
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return employeeList;
    }
}