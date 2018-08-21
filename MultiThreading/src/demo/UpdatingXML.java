package demo;


import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class UpdatingXML{

    static String strXml = "<INFO><BeginDate>2013-12-02</BeginDate><EndDate>2014-01-31</EndDate></INFO>";

    public static void main(String[] args) throws Exception {

        System.out.println(strXml);
        Document doc = StringToDocument(strXml);
        updateNodeValue(doc);
        String newxml = DocumentToString(doc);
        System.out.println(newxml);

    }

    public static void updateNodeValue(Document doc) {

        Node rootNode = doc.getFirstChild();
        System.out.println("Root node="+rootNode);
        NodeList list = rootNode.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {

            Element element = (Element) list.item(i);
            Node node = list.item(i);
            System.out.println(node.getNodeName());
            if ("EndDate".equals(node.getNodeName())) {
                element.setTextContent("2013-12-12");
                return;
            }
        }
    }

    public static String DocumentToString(Document doc) throws Exception {

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        String output = writer.getBuffer().toString();
        return output;
    }

    public static Document StringToDocument(String strXml) throws Exception {

        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            StringReader strReader = new StringReader(strXml);
            InputSource is = new InputSource(strReader);
            doc = (Document) builder.parse(is);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return doc;
    }
}