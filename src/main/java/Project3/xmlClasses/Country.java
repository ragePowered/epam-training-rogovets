
package Project3.xmlClasses;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for country.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="country">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="US"/>
 *     &lt;enumeration value="UA"/>
 *     &lt;enumeration value="EN"/>
 *     &lt;enumeration value="FR"/>
 *     &lt;enumeration value="TU"/>
 *     &lt;enumeration value="IT"/>
 *     &lt;enumeration value="BR"/>
 *     &lt;enumeration value="BL"/>
 *     &lt;enumeration value="CH"/>
 *     &lt;enumeration value="JP"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "country")
@XmlEnum
public enum Country {

    US,
    UA,
    EN,
    FR,
    TU,
    IT,
    BR,
    BL,
    CH,
    JP;

    public static Country fromValue(String v) {
        return valueOf(v);
    }

}
