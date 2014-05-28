package com.ltree.carbonandmore.dao;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * Access The emissions database.
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
		log.info("get emission called with Transport type: " + transportType);
		if (transportType == null)
			throw new IllegalArgumentException(ERROR_MSG_INVALID_TRANSPORT_TYPE);

		String searchTerm = transportType.toString().toUpperCase();
		String value = findValueInFile(loadData(), searchTerm);

		if (value != null) {
			log.info("get Emission returns with value: " + value);
			return Double.parseDouble(value);
		} else {
			log.info("get Emission returns with no data");
			return 0.0;
		}

	}

	/**
	 * Private helper method to get the value from the XML file. This uses an a
	 * document object Model (DOM) which loads all of the xml file into memory
	 * before parsing it. DOMs have are not very fast processing very large
	 * files. The Helper method also implements DEBUG loggin which might be
	 * useful in debugging issues in a live system.
	 * 
	 * @param file
	 * @param searchName
	 * @return
	 */
	private String findValueInFile(final File file, final String searchName) {
		// START exercise 3.3
/*
		try {
			log.debug("findValueInFile called: " + file.getAbsolutePath()
					+ " searchName: " + searchName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			log.debug("findValueInFile: file parsed: searchName: " + searchName);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("transport");
			log.debug("findValueInFile: node list length: " + nList.getLength());
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) nNode;
					String type = element.getAttribute("type");
					String name = element.getElementsByTagName("emission")
							.item(0).getTextContent();
					log.debug("findValueInFile: file parsing: type: " + type
							+ " name: " + name);
					if (searchName.equals(type.toUpperCase())) {
						log.debug("findValueInFile: found a hit: type: " + type
								+ " name: " + name);
						return name.toLowerCase();
					}

				}
			}
		} catch (Exception e) {
			log.error(ERROR_MSG_READING_FILE, e);
		}
		return null;
	}
*/
		// END exercise 3.3

		// START exercise 4.1

		EmissionHandler handler = new EmissionHandler();
		handler.setSearchName(searchName);
		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(loadData().getAbsoluteFile(), handler);

		} catch (Exception e) {
			log.error(ERROR_MSG_READING_FILE, e);
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
			
			if (emission && mot.toUpperCase().equals(searchName)) {
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

	// END exercise 4.1

	private File loadData() {
		if (datasource == null)
			throw new IllegalArgumentException(ERROR_MSG_DATASOURCE_NOT_SET);
		URL url = this.getClass().getResource(datasource);
		return new File(url.getFile());
	}

	@Override
	public void setDataSource(String datasource) {
		log.debug("Datasource initialised with " + datasource);
		this.datasource = datasource;

	}
}
