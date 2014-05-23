
package Project3.xmlClasses;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for oldCardType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="oldCardType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="theme" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{}cardType"/>
 *         &lt;element name="cuntry" type="{}country"/>
 *         &lt;element name="year" type="{}year"/>
 *         &lt;element name="author" type="{}authorList"/>
 *         &lt;element name="valuable" type="{}valueType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "oldCardType", propOrder = {
    "theme",
    "type",
    "cuntry",
    "year",
    "author",
    "valuable"
})
public class OldCardType implements Comparable<OldCardType>{

	public OldCardType() {
	}

	public OldCardType(String theme, CardType type, Country cuntry, int year, List<String> author, ValueType valuable) {
		this.theme = theme;
		this.type = type;
		this.cuntry = cuntry;
		this.year = year;
		this.author = author;
		this.valuable = valuable;
	}

	@XmlElement(required = true)
    protected String theme;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected CardType type;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected Country cuntry;
    @XmlSchemaType(name = "positiveInteger")
    protected int year;
    @XmlList
    @XmlElement(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected List<String> author;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected ValueType valuable;

    /**
     * Gets the value of the theme property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTheme() {
        return theme;
    }

    /**
     * Sets the value of the theme property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTheme(String value) {
        this.theme = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link CardType }
     *     
     */
    public CardType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardType }
     *     
     */
    public void setType(CardType value) {
        this.type = value;
    }

    /**
     * Gets the value of the cuntry property.
     * 
     * @return
     *     possible object is
     *     {@link Country }
     *     
     */
    public Country getCuntry() {
        return cuntry;
    }

    /**
     * Sets the value of the cuntry property.
     * 
     * @param value
     *     allowed object is
     *     {@link Country }
     *     
     */
    public void setCuntry(Country value) {
        this.cuntry = value;
    }

    /**
     * Gets the value of the year property.
     * 
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     * 
     */
    public void setYear(int value) {
        this.year = value;
    }

    /**
     * Gets the value of the author property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the author property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAuthor() {
        if (author == null) {
            author = new ArrayList<>();
        }
        return this.author;
    }

	public void setAuthor(List<String> author) {
		this.author = author;
	}

	/**
     * Gets the value of the valuable property.
     * 
     * @return
     *     possible object is
     *     {@link ValueType }
     *     
     */
    public ValueType getValuable() {
        return valuable;
    }

    /**
     * Sets the value of the valuable property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueType }
     *     
     */
    public void setValuable(ValueType value) {
        this.valuable = value;
    }

	@Override
	public String toString() {
		return 	'\t' + theme +
				"\n\t" + type +
				"\n\t" + cuntry +
				"\n\t" + year +
				"\n\t" + author +
				"\n\t" + valuable +
				'\n';
	}

	@Override
	public int compareTo(OldCardType o) {
		return this.year - o.getYear();
	}
}
