package Project3;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 5/23/14
 * Time: 12:17 AM
 */

public class CustomDOM {

	public static Node getNode(String tagName, NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++){
			Node node = nodes.item(i);
			if (node.getNodeName().equalsIgnoreCase(tagName))
				return node;
		}
		return null;
	}

	public static String getNodeValue(String tagName, NodeList nodes){
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeName().equalsIgnoreCase(tagName)){
				NodeList childNodes = node.getChildNodes();
				for (int j = 0; j < childNodes.getLength(); j++) {
					Node data = childNodes.item(j);
					if (data.getNodeType() == Node.TEXT_NODE)
						return data.getNodeValue();
				}
			}

		}
		return "";
	}

}
