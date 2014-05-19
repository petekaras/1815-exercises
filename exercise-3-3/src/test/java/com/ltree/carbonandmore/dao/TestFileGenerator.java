package com.ltree.carbonandmore.dao;

import java.io.File;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TestFileGenerator {

	public static void main(String argv[]) {

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("emissions");
			doc.appendChild(rootElement);
			for (int i = 0; i < 100000; i++) {
				// staff elements
				Element staff = doc.createElement("transport");
				rootElement.appendChild(staff);

				// set attribute to staff element
				Attr attr = doc.createAttribute("type");
				attr.setValue("TYPE"+i);
				staff.setAttributeNode(attr);

				// shorten way
				// staff.setAttribute("id", "1");

				// firstname elements
				Element firstname = doc.createElement("emission");
				firstname.appendChild(doc.createTextNode(getRandomEmission()));
				staff.appendChild(firstname);
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(
					"C:/Users/peter/file.xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	private static String getRandomEmission() {
		Random r = new Random();
		double number = 1 + (100 - 1) * r.nextDouble();
		number = Math.round(number * 100);
		number = number / 100;
		return "" + number;
	}
}
