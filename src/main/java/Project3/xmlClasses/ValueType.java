
package Project3.xmlClasses;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for valueType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="valueType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Історична ціність"/>
 *     &lt;enumeration value="Колеційна цінність"/>
 *     &lt;enumeration value="Тематична цінність"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "valueType")
@XmlEnum
public enum ValueType {

    @XmlEnumValue("\u0406\u0441\u0442\u043e\u0440\u0438\u0447\u043d\u0430 \u0446\u0456\u043d\u0456\u0441\u0442\u044c")
    ІСТОРИЧНА_ЦІНІСТЬ("\u0406\u0441\u0442\u043e\u0440\u0438\u0447\u043d\u0430 \u0446\u0456\u043d\u0456\u0441\u0442\u044c"),
    @XmlEnumValue("\u041a\u043e\u043b\u0435\u0446\u0456\u0439\u043d\u0430 \u0446\u0456\u043d\u043d\u0456\u0441\u0442\u044c")
    КОЛЕЦІЙНА_ЦІННІСТЬ("\u041a\u043e\u043b\u0435\u0446\u0456\u0439\u043d\u0430 \u0446\u0456\u043d\u043d\u0456\u0441\u0442\u044c"),
    @XmlEnumValue("\u0422\u0435\u043c\u0430\u0442\u0438\u0447\u043d\u0430 \u0446\u0456\u043d\u043d\u0456\u0441\u0442\u044c")
    ТЕМАТИЧНА_ЦІННІСТЬ("\u0422\u0435\u043c\u0430\u0442\u0438\u0447\u043d\u0430 \u0446\u0456\u043d\u043d\u0456\u0441\u0442\u044c");
    private final String value;

    ValueType(String v) {
        value = v;
    }

    public static ValueType fromValue(String v) {
        for (ValueType c: ValueType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
