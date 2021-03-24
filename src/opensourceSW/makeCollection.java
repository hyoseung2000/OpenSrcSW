package opensourceSW;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

//import org.w3c.dom.Document;
//import org.w3c.dom.Element;

public class xml {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] fname = { "떡.html", "라면.html", "아이스크림.html", "초밥.html", "파스타.html" };

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		org.w3c.dom.Document doc = docBuilder.newDocument();
		// <docs>
		org.w3c.dom.Element docs = doc.createElement("docs");
		doc.appendChild(docs);

		for (int i = 0; i < 5; i++) {
			File input = new File(fname[i]);

			Document document = null;
			try {
				document = Jsoup.parse(input, "UTF-8");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Elements ttitle = document.getElementsByTag("title");
			Elements pp = document.getElementsByTag("p");

			// <doc>
			org.w3c.dom.Element docu = doc.createElement("doc");
			docs.appendChild(docu);
			docu.setAttribute("id", String.valueOf(i));
			// <title>
			org.w3c.dom.Element title = doc.createElement("title");
			title.appendChild(doc.createTextNode(ttitle.text()));
			docu.appendChild(title);
			// <body>
			org.w3c.dom.Element body = doc.createElement("body");
			String content = "";
			if (pp.size() > 0) {
				for (int j = 0; j < pp.size(); j++) {
					String src = pp.get(j).text();
					content += src;
				}
				body.appendChild(doc.createTextNode(content));
				docu.appendChild(body);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();

			Transformer transformer = null;
			try {
				transformer = transformerFactory.newTransformer();
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

			DOMSource source = new DOMSource(doc);
			StreamResult result = null;
			try {
				result = new StreamResult(new FileOutputStream(new File("src/collection.xml")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				transformer.transform(source, result);
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
