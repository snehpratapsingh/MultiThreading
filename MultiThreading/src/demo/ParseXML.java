package demo;

import java.io.File;
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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParseXML {

	public static void main(String[] args) {

		try {

			File file = new File("D:\\Knova\\Data\\DocExport\\ExportData_two_docs.xml");

			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			Document doc = dBuilder.parse(file);

			// System.out.println("Root element :" +
			// doc.getDocumentElement().getNodeName());
			Node rootNode = doc.getFirstChild();
			System.out.println("Root node=" + rootNode);
			if (doc.hasChildNodes()) {
				printAll(doc.getChildNodes());
			}
			// print the final xml
			 System.out.println(DocumentToString(doc));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void printAll(NodeList rootNode) {

		// System.out.println("Size of list:"+rootNode.getLength());
		for (int i = 0; i < rootNode.getLength(); i++) {

			Node node = rootNode.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) rootNode.item(i);
				if ("Title".equals(node.getNodeName())) {
					element.setTextContent("Changed the title");
				}
				if (node.hasChildNodes()) {
					printAll(node.getChildNodes());
				}
				//print all nodes
				//System.out.println(node.getNodeName());
			}

			;
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

	private static void printNote(NodeList nodeList) {

		for (int count = 0; count < nodeList.getLength(); count++) {

			Node tempNode = nodeList.item(count);

			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				// get node name and value
				System.out.println("<" + tempNode.getNodeName() + ">");
				System.out.println(tempNode.getTextContent());

				if (tempNode.hasAttributes()) {

					// get attributes names and values
					NamedNodeMap nodeMap = tempNode.getAttributes();

					for (int i = 0; i < nodeMap.getLength(); i++) {

						Node node = nodeMap.item(i);
						System.out.println("attr name : " + node.getNodeName());
						System.out.println("attr value : " + node.getNodeValue());

					}

				}

				if (tempNode.hasChildNodes()) {

					// loop again if has child nodes
					printNote(tempNode.getChildNodes());

				}

				System.out.println("<" + tempNode.getNodeName() + ">");

			}

		}

	}

}
