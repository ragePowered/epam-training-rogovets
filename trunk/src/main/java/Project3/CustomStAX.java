package Project3;

import Project3.xmlClasses.CardType;
import Project3.xmlClasses.Country;
import Project3.xmlClasses.OldCardType;
import Project3.xmlClasses.ValueType;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 5/23/14
 * Time: 1:50 AM
 */

public class CustomStAX {
	private List<OldCardType> list = null;
	private OldCardType currentCard = null;
	private String tagContent = null;
	private XMLInputFactory factory = XMLInputFactory.newFactory();

	public List<OldCardType> getList() {
		return list;
	}

	public void parse(String xmlPath) {

		try {
			XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(xmlPath));
			while (reader.hasNext()) {
				int event = reader.next();
				switch (event) {
					case (XMLStreamConstants.START_ELEMENT): {
						switch (reader.getLocalName()) {
							case ("oldCardList"): {
								list = new ArrayList<>();
								break;
							}
							case ("oldCard"): {
								currentCard = new OldCardType();
								break;
							}
						}
						break;
					}
					case (XMLStreamConstants.CHARACTERS): {
						tagContent = reader.getText().trim();
						break;
					}
					case (XMLStreamConstants.END_ELEMENT): {
						switch (reader.getLocalName()) {
							case ("oldCard"): {
								list.add(currentCard);
								break;
							}
							case ("theme"): {
								currentCard.setTheme(tagContent);
								break;
							}
							case ("type"): {
								currentCard.setType(CardType.fromValue(tagContent));
								break;
							}
							case ("cuntry"): {
								currentCard.setCuntry(Country.fromValue(tagContent));
								break;
							}
							case ("year"): {
								currentCard.setYear(Integer.parseInt(tagContent));
								break;
							}
							case ("author"): {
								currentCard.setAuthor(Arrays.asList(tagContent.split(" ")));
								break;
							}
							case ("valuable"): {
								currentCard.setValuable(ValueType.fromValue(tagContent));
								break;
							}
						}
						break;
					}
				}
			}
		} catch (XMLStreamException | FileNotFoundException e) {
			e.printStackTrace();  //Exception here
		}
	}
}
