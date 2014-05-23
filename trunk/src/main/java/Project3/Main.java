package Project3;

import Project3.xmlClasses.*;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 5/18/14
 * Time: 4:54 PM
 */

/**
 *
 */

public class Main {
	final static private String xsdPath = "src/main/java/Project3/OldCard.xsd";
	final static private String xmlPath = "src/main/java/Project3/instance.xml";
	final static private String xslPath = "src/main/java/Project3/OldCard.xsl";
	final static private String outPath = "src/main/java/Project3/OldCard.html";

	private static OldCardList oldCardList = new OldCardList(new ArrayList<OldCardType>() {{
		add(new OldCardType(Utils.pickTheme(), CardType.fromValue(Utils.pickCardType()), Country.fromValue(Utils.pickCountry()), Utils.pickYear(), Utils.generateAuthors(), ValueType.fromValue(Utils.pickValue())));
		add(new OldCardType(Utils.pickTheme(), CardType.fromValue(Utils.pickCardType()), Country.fromValue(Utils.pickCountry()), Utils.pickYear(), Utils.generateAuthors(), ValueType.fromValue(Utils.pickValue())));
		add(new OldCardType(Utils.pickTheme(), CardType.fromValue(Utils.pickCardType()), Country.fromValue(Utils.pickCountry()), Utils.pickYear(), Utils.generateAuthors(), ValueType.fromValue(Utils.pickValue())));
		add(new OldCardType(Utils.pickTheme(), CardType.fromValue(Utils.pickCardType()), Country.fromValue(Utils.pickCountry()), Utils.pickYear(), Utils.generateAuthors(), ValueType.fromValue(Utils.pickValue())));
		add(new OldCardType(Utils.pickTheme(), CardType.fromValue(Utils.pickCardType()), Country.fromValue(Utils.pickCountry()), Utils.pickYear(), Utils.generateAuthors(), ValueType.fromValue(Utils.pickValue())));
		add(new OldCardType(Utils.pickTheme(), CardType.fromValue(Utils.pickCardType()), Country.fromValue(Utils.pickCountry()), Utils.pickYear(), Utils.generateAuthors(), ValueType.fromValue(Utils.pickValue())));
		add(new OldCardType(Utils.pickTheme(), CardType.fromValue(Utils.pickCardType()), Country.fromValue(Utils.pickCountry()), Utils.pickYear(), Utils.generateAuthors(), ValueType.fromValue(Utils.pickValue())));
		add(new OldCardType(Utils.pickTheme(), CardType.fromValue(Utils.pickCardType()), Country.fromValue(Utils.pickCountry()), Utils.pickYear(), Utils.generateAuthors(), ValueType.fromValue(Utils.pickValue())));
		add(new OldCardType(Utils.pickTheme(), CardType.fromValue(Utils.pickCardType()), Country.fromValue(Utils.pickCountry()), Utils.pickYear(), Utils.generateAuthors(), ValueType.fromValue(Utils.pickValue())));
		add(new OldCardType(Utils.pickTheme(), CardType.fromValue(Utils.pickCardType()), Country.fromValue(Utils.pickCountry()), Utils.pickYear(), Utils.generateAuthors(), ValueType.fromValue(Utils.pickValue())));
	}});

	public static void main(String[] args) {
		saveToXml(oldCardList);
		loadFromXmlAndShow();

		parseXmlWithCustomSAX();
		parseXmlWithCustomDOM();
		parseXmlWithCustomStAX();

		validate();

		convertToHTML();

	}

	private static void saveToXml(OldCardList oldCardList) {
		try {
			JAXBContext context = JAXBContext.newInstance(OldCardList.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "OldCard.xsd");
			File output = new File(xmlPath);
			marshaller.marshal(oldCardList, output);
		} catch (JAXBException e) {
			e.printStackTrace();  //Exception here
		}
	}

	private static void loadFromXmlAndShow() {
		OldCardList unmarshalledCards = null;
		try {
			JAXBContext context = JAXBContext.newInstance(OldCardList.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshalledCards = (OldCardList) unmarshaller.unmarshal(new File(xmlPath));
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		if (unmarshalledCards != null) {
			unmarshalledCards.getList().forEach(System.out::println);
		}
	}

	private static void parseXmlWithCustomSAX() {
		CustomSAX customSAX = new CustomSAX();
		List<OldCardType> cardsList;

		try {
			SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
			saxParser.parse(new File(xmlPath), customSAX);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		cardsList = customSAX.getOldCardList();
		cardsList.forEach(System.out::println);
	}

	private static void parseXmlWithCustomDOM() {
		List<OldCardType> cardsList = new ArrayList<>();
		DOMParser parser = new DOMParser();

		try {
			parser.parse(xmlPath);
		} catch (SAXException | IOException e) {
			e.printStackTrace();  //Exception here
		}

		Document document = parser.getDocument();
		NodeList root = document.getChildNodes();

		Node listTag = CustomDOM.getNode("oldCardList", root);

		NodeList oldCards = listTag.getChildNodes();
		for (int i = 0; i < oldCards.getLength(); i++) {
			if (oldCards.item(i).getNodeType() == Node.ELEMENT_NODE) {
				NodeList nodes = oldCards.item(i).getChildNodes();
				String theme = CustomDOM.getNodeValue("theme", nodes);
				String type = CustomDOM.getNodeValue("type", nodes);
				String cuntry = CustomDOM.getNodeValue("cuntry", nodes);
				String year = CustomDOM.getNodeValue("year", nodes);
				String author = CustomDOM.getNodeValue("author", nodes);
				String valuable = CustomDOM.getNodeValue("valuable", nodes);
				cardsList.add(new OldCardType(theme, CardType.fromValue(type), Country.fromValue(cuntry),
						Integer.parseInt(year), Arrays.asList(author.split(" ")), ValueType.fromValue(valuable)));
			}
		}
		cardsList.forEach(System.out::println);
	}

	private static void parseXmlWithCustomStAX() {
		CustomStAX parser = new CustomStAX();
		parser.parse(xmlPath);
		parser.getList().forEach(System.out::println);
	}

	private static void validate() {
		File schemaFile = new File(xsdPath);

		Source xmlFile = new StreamSource(new File(xmlPath));
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = null;
		try {
			schema = schemaFactory.newSchema(schemaFile);
		} catch (SAXException e) {
			e.printStackTrace();  //Exception here
		}
		Validator validator = schema != null ? schema.newValidator() : null;

		try {
			if (validator != null) {
				validator.validate(xmlFile);
			}
			System.out.println(xmlFile.getSystemId() + " is valid");
		} catch (SAXException e) {
			System.out.println(xmlFile.getSystemId() + " is NOT valid!\nReason: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();  //Exception here
		}
	}

	private static void convertToHTML() {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer(new StreamSource(new FileInputStream(xslPath)));
			transformer.transform(new StreamSource(new FileInputStream(xmlPath)), new StreamResult(new FileOutputStream(outPath)));
			System.out.println("HTML was successfully generated!");
		} catch (FileNotFoundException | TransformerException e) {
			e.printStackTrace();  //Exception here
		}
	}

}
