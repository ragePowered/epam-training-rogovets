package Project3;

import Project3.xmlClasses.CardType;
import Project3.xmlClasses.Country;
import Project3.xmlClasses.OldCardType;
import Project3.xmlClasses.ValueType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 5/22/14
 * Time: 12:04 PM
 */

public class CustomSAX extends DefaultHandler implements Interpretations{
	private List<OldCardType> oldCardList;
	private OldCardType currentCard;
	private int currentTag = -1;

	public List<OldCardType> getOldCardList() {
		return oldCardList;
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("Parsing started");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("Parsing ended");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (qName){
			case ("oldCardList"):{
				this.oldCardList = new ArrayList<>();
				break;
			}
			case ("oldCard"):{
				this.currentCard = new OldCardType();
				break;
			}
			case ("theme"):{
				this.currentTag = THEME;
				break;
			}
			case ("type"):{
				this.currentTag = TYPE;
				break;
			}
			case ("cuntry"):{
				this.currentTag = COUNTRY;
				break;
			}
			case ("year"):{
				this.currentTag = YEAR;
				break;
			}
			case ("author"):{
				this.currentTag = AUTHOR;
				break;
			}
			case ("valuable"):{
				this.currentTag = VALUABLE;
				break;
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("oldCard")) this.oldCardList.add(currentCard);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String s = new String(ch, start, length).replace('\n', ' ').replace('\t',' ').trim();
		switch (currentTag){
			case (THEME):{
				currentCard.setTheme(s);
				currentTag = -1;
				break;
			}
			case (TYPE):{
				currentCard.setType(CardType.fromValue(s));
				currentTag = -1;
				break;
			}
			case (COUNTRY):{
				currentCard.setCuntry(Country.fromValue(s));
				currentTag = -1;
				break;
			}

			case (AUTHOR):{
				currentCard.setAuthor(Arrays.asList(s.split(" ")));
				currentTag = -1;
				break;
			}
			case (VALUABLE):{
				currentCard.setValuable(ValueType.fromValue(s));
				currentTag = -1;
				break;
			}
		}
	}
}
