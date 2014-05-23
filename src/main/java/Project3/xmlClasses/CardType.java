
package Project3.xmlClasses;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cardType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="cardType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Вітання"/>
 *     &lt;enumeration value="Реклама"/>
 *     &lt;enumeration value="Звичайна"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "cardType")
@XmlEnum
public enum CardType {

    @XmlEnumValue("\u0412\u0456\u0442\u0430\u043d\u043d\u044f")
    ВІТАННЯ("\u0412\u0456\u0442\u0430\u043d\u043d\u044f"),
    @XmlEnumValue("\u0420\u0435\u043a\u043b\u0430\u043c\u0430")
    РЕКЛАМА("\u0420\u0435\u043a\u043b\u0430\u043c\u0430"),
    @XmlEnumValue("\u0417\u0432\u0438\u0447\u0430\u0439\u043d\u0430")
    ЗВИЧАЙНА("\u0417\u0432\u0438\u0447\u0430\u0439\u043d\u0430");
    private final String value;

    CardType(String v) {
        value = v;
    }

    public static CardType fromValue(String v) {
		for (CardType c : CardType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}
}
