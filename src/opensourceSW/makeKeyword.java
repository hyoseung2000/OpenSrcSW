package opensourceSW;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;

public class makeKeyword {

	public static void makeKeyword() {
		
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
		
		File input = new File("C:\\SimpleIR\\src","collection.xml");

		Document document = null;
		try {
			document = Jsoup.parse(input, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Elements bodys = document.getElementsByTag("body");
		Elements ttitle=document.getElementsByTag("title");
		KeywordExtractor ke =new KeywordExtractor();
		
		for(int i=0;i<bodys.size();i++) {
			org.w3c.dom.Element docu = doc.createElement("doc");
			docs.appendChild(docu);
			docu.setAttribute("id", String.valueOf(i));
			// <title>
			org.w3c.dom.Element title = doc.createElement("title");
			title.appendChild(doc.createTextNode(ttitle.get(i).text()));
			docu.appendChild(title);
			// <body>
			org.w3c.dom.Element body = doc.createElement("body");
			
			String str=bodys.get(i).text();
			KeywordList kl=ke.extractKeyword(str, true);
			String neww="";
			for(int j=0;j<kl.size();j++) {
				Keyword kwrd=kl.get(j);
				neww+=kwrd.getString()+":"+kwrd.getCnt()+"#";
			}
			body.appendChild(doc.createTextNode(neww));
			docu.appendChild(body);
			
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
				result = new StreamResult(new FileOutputStream(new File("src/index.xml")));
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