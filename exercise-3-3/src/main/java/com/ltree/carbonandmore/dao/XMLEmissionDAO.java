package com.ltree.carbonandmore.dao;

import java.io.File;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
		String searchTerm = transportType.toString().toLowerCase();
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
			log.error(ERROR_MSG_READING_FILE,e);
		}
		return null;
	}

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
