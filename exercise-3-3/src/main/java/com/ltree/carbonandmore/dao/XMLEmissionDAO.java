package com.ltree.carbonandmore.dao;

import java.io.File;
import java.io.StringReader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderAdapter;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Access The user database
 * 
 * @author peter
 * 
 */
public class XMLEmissionDAO implements EmissionDAO {
	private String datasource = null;
	private static final String ERROR_MSG_INVALID_TRANSPORT_TYPE = "Invalid transport type";
	private static final String ERROR_MSG_DATASOURCE_NOT_SET = "Datasource not set";
	private static final String ERROR_MSG_READING_FILE = "Error reading file";

	static Logger log = Logger.getLogger(XMLEmissionDAO.class.getName());

	@Override
	public double getEmission(final TransportType transportType) {
		log.trace("get Emission called with Transport type: " + transportType);
		if (transportType == null)
			throw new IllegalArgumentException(ERROR_MSG_INVALID_TRANSPORT_TYPE);

		String searchTerm = transportType.toString().toUpperCase();
		String value = findValueInFile(loadData(), searchTerm);

		if (value != null) {
			log.trace("get Emission returns with value: " + value);
			return Double.parseDouble(value);
		} else {
			log.trace("get Emission returns with no data");
			return 0;
		}

	}

	private String findValueInFile(final File file, final String searchName) {
//START exercise 3.3
		
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("transport");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) nNode;
					String type = element.getAttribute("type");
					String name = element.getElementsByTagName("emission")
							.item(0).getTextContent();
					if (searchName.equals(type.toLowerCase())) {
						return name.toLowerCase();
					}

				}
			}
		} catch (Exception e) {
			log.error(ERROR_MSG_READING_FILE, e);
		}
		return null;
	}
//END exercise 3.3
		
// START exercise 4.1
		/*
		EmissionHandler handler = new EmissionHandler();
		handler.setSearchName(searchName);
		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(loadData().getAbsoluteFile(), handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return handler.getHit();

	}

	public class EmissionHandler extends DefaultHandler {
		boolean emission = false;
		boolean transport = false;
		String searchName = "";
		String mot = "";
		String hit = "0.0";

		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {

			if (qName.equalsIgnoreCase("EMISSION")) {
				emission = true;
			}
			if (qName.equalsIgnoreCase("TRANSPORT")) {
				transport = true;
				mot = attributes.getValue("type");
			}

		}

		public void characters(char ch[], int start, int length)
				throws SAXException {

			if (emission && mot.equals(searchName)) {
				hit = new String(ch, start, length);
				emission = false;

			}
			if (transport) {
				transport = false;
			}

		}

		public void setSearchName(final String searchName) {
			this.searchName = searchName;
		}

		public String getHit() {
			return hit;
		}
	}
*/
	// END exercise 4.1

	private File loadData() {
		if (datasource == null)
			throw new IllegalArgumentException(ERROR_MSG_DATASOURCE_NOT_SET);
		URL url = this.getClass().getResource(datasource);
		return new File(url.getFile());
	}

	@Override
	public void setDataSource(String datasource) {
		log.trace("Datasource initialised with " + datasource);
		this.datasource = datasource;

	}
}
