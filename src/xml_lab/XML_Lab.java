package xml_lab;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XML_Lab {

    public static String numericID;

    public static void main(String[] args) {
        System.out.println("Type in ID Number");
        Scanner scanner = new Scanner(System.in);
        numericID = scanner.nextLine();

        try {
            new XML_Lab().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void start() throws Exception {
        URL url = new URL("http://129.32.92.49/xml/grade.php?id=" + numericID);
        URLConnection connection = url.openConnection();

        Document document = parseXML(connection.getInputStream());
        NodeList descNodes = document.getElementsByTagName("name");
        NodeList desGrade = document.getElementsByTagName("grade");

        for (int i = 0; i < descNodes.getLength(); i++) {
            System.out.println(descNodes.item(i).getTextContent());
        }

        for (int i = 0; i < desGrade.getLength(); i++) {
            System.out.println(desGrade.item(i).getTextContent());
        }
    }

    private Document parseXML(InputStream stream)
            throws Exception {
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;

        try {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = objDocumentBuilder.parse(stream);
        } catch (Exception ex) {
            throw ex;
        }

        return doc;
    }
}
