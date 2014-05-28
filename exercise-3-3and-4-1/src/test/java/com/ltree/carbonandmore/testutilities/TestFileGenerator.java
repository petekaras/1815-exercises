package com.ltree.carbonandmore.testutilities;

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
/**
 * Utility to generate an XML file thats used as part of this exercise.
 * @author peter
 *
 */
public class TestFileGenerator {

	private static final int NUMBER_OF_RECORDS = 100000;
	private static final String OUTPUT_XML_FILE_NAME = "C:/Users/peter/file.xml";

	public static void main(String argv[]) {

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("emissions");
			doc.appendChild(rootElement);
			for (int i = 0; i < NUMBER_OF_RECORDS; i++) {

				Element transport = doc.createElement("transport");
				rootElement.appendChild(transport);

				Attr attr = doc.createAttribute("type");
				attr.setValue("TYPE"+i);
				transport.setAttributeNode(attr);


				Element emission = doc.createElement("emission");
				emission.appendChild(doc.createTextNode(getRandomEmission()));
				transport.appendChild(emission);
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(
					OUTPUT_XML_FILE_NAME));

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
