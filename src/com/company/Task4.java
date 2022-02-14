package com.company;


import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class Task4 extends Task{

    File file;
    private final String curDirectory = "D://Maxim//ЯП//OS//lab1//";

    public Task4(){
        menus = new String[]{"Записать в файл", "Вывести информацию из файла", "Удалить файл"};
        func = Arrays.asList((num)-> {
            try {
                return WriteToFile();
            } catch (ParserConfigurationException | IOException e) {
                e.printStackTrace();
            }
            return null;
        },(num)->ReadFromFile(),(num)->DeleteFile());
    }

    @Override
    public void Menu() {
        for (int i = 0; i < menus.length;i++){
            System.out.printf("%d) %s\n", i+1, menus[i]);
        }
    }

    public void CreateFile(String newfile) {
        try
        {
            file = new File(curDirectory+newfile);
            boolean created = file.createNewFile();
            if(created)
                System.out.printf("Файл %s был успешно создан\n",newfile);
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Boolean WriteToFile() throws ParserConfigurationException, IOException {
        PrintAllFiles();
        System.out.print("Введите название файла для записи в него: ");
        String temp = in.nextLine();
        CreateFile(temp+".xml");

        Person person = CreatePerson();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        Element root = doc.createElement("person");
        Element name = doc.createElement("name");
        Element surname = doc.createElement("surname");
        Element age = doc.createElement("age");

        name.setTextContent(person.name);
        surname.setTextContent(person.surname);
        age.setTextContent(person.age+"");

        root.appendChild(name);
        root.appendChild(surname);
        root.appendChild(age);
        doc.appendChild(root);

        writeDocument(doc, curDirectory+temp+".xml");
        return true;
    }

    private String getValue(NodeList fields, int index)
    {
        NodeList list = fields.item(index).getChildNodes();
        if (list.getLength() > 0) {
            return list.item(0).getNodeValue();
        } else {
            return "";
        }
    }

    public Boolean ReadFromFile(){
        PrintAllFiles();
        System.out.print("Введите название файла для вывода: ");
        String temp = in.nextLine();
        if (!temp.contains(".xml")){
            temp += ".xml";
        }
        Person person;

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder        db = dbf.newDocumentBuilder();
            Document               doc = null;

            try {
                doc = db.parse(new File(curDirectory+temp));
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("person");
            for (int s = 0; s < nodeList.getLength(); s++) {
                Node node = nodeList.item(s);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) node;
                    String name = el.getElementsByTagName("name").item(0).getTextContent();
                    String surname = el.getElementsByTagName("surname").item(0).getTextContent();
                    String age = el.getElementsByTagName("age").item(0).getTextContent();
                    person = new Person(name, surname, parseInt(age));
                    System.out.printf("%s\n",person.ToString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public Boolean DeleteFile(){
        PrintAllFiles();
        System.out.print("Введите название файла для удаления: ");
        String temp = in.nextLine();
        if (!temp.contains(".xml")){
            temp += ".xml";
        }
        file = new File(curDirectory+temp);
        if(file.delete()){
            System.out.printf("%s файл удален\n",curDirectory+temp);
        }
        else System.out.printf("Файла %s не обнаружено",curDirectory+temp);
        return true;
    }

    private void PrintAllFiles(){
        File dir = new File("D://Maxim//ЯП//OS//lab1");

        if(dir.isDirectory())
        {
            for(File item : Objects.requireNonNull(dir.listFiles())){

                if(!item.isDirectory() && item.getName().contains(".xml")){
                    System.out.println(item.getName());
                }

            }
        }
    }

    void writeDocument(Document document, String path) throws TransformerFactoryConfigurationError, IOException {
        Transformer trf = null;
        DOMSource src = null;
        FileOutputStream fos = null;
        try {
            trf = TransformerFactory.newInstance()
                    .newTransformer();
            src = new DOMSource(document);
            fos = new FileOutputStream(path);
            StreamResult result = new StreamResult(fos);
            trf.transform(src, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        } finally {
            assert fos != null;
            fos.close();
        }
    }

    private Person CreatePerson(){
        System.out.print("Введите имя: ");
        String name = in.nextLine();
        System.out.print("Введите фамилию: ");
        String surname = in.nextLine();
        System.out.print("Введите возраст: ");
        int age = in.nextInt();

        return new Person(name, surname, age);
    }
}
