import org.w3c.dom.Document;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.File;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XMLTV
{
	private Document document;
	private DocumentBuilderFactory docFactory;
	private DocumentBuilder builder;
	private TransformerFactory transFactory;
	private Transformer transformer;
	private String stylesheet;

	private Vector programs;

	public XMLTV(Vector programs, String stylesheet)
	{
		this.programs = programs;
		this.stylesheet = stylesheet;
		try
		{
			initBuildersAndWriters();
			buildDom();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void writeXML(OutputStream out) throws IOException, TransformerException
	{
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(out);
		StreamSource stylesource = new StreamSource(stylesheet);
		transformer.transform(source, result);
	}

	private void initBuildersAndWriters() throws Exception
	{
		docFactory = DocumentBuilderFactory.newInstance();
		builder = docFactory.newDocumentBuilder();
		document = builder.newDocument();

		transFactory = TransformerFactory.newInstance();
		if (stylesheet == null)
		{
			transformer = transFactory.newTransformer();
		}
		else
		{
			StreamSource ssSource = new StreamSource(new File(stylesheet));
			transformer = transFactory.newTransformer(ssSource);
		}
	}

	private void addLocaleHashtableElements(Element el, Hashtable hash, String name, String localeDescription)
	{
		Enumeration enum = hash.keys();
		while(enum.hasMoreElements())
		{
			String localeText = (String)enum.nextElement();
			Element locale = (Element)document.createElement(name);
			locale.setAttribute(localeDescription, localeText);
			locale.appendChild(document.createTextNode((String)hash.get(localeText)));
			el.appendChild(locale);
		}
	}

	private Element createProgramElement(Program program)
	{
		Element el = (Element)document.createElement("programme");
		// set attributes;
		el.setAttribute("start", program.getStartTime());
		el.setAttribute("channel", program.getChannel());

		addLocaleHashtableElements(el, program.getTitles(), "title", "lang");
		addLocaleHashtableElements(el, program.getDescriptions(), "desc", "lang");
		addLocaleHashtableElements(el, program.getCategories(), "category", "lang");

		Duration duration = program.getDuration();
		if (duration != null)
		{
			Element durEl = (Element)document.createElement("length");
			durEl.setAttribute("units", duration.getUnits());
			durEl.appendChild(document.createTextNode(duration.getLength()));
			el.appendChild(durEl);
		}

		Credit[] credits = program.getCreditsAsArray();
		if (credits != null)
		{
			Element creditsEl = (Element)document.createElement("credits");
			for (int i = 0; i < credits.length; i++)
			{
				Element credit = (Element)document.createElement(credits[i].getType());
				credit.appendChild(document.createTextNode(credits[i].getPrincipal()));
				creditsEl.appendChild(credit);
			}
			el.appendChild(creditsEl);
		}

		return el;
	}

	private void buildDom()
	{
		Element root = (Element) document.createElement("tv");
		document.appendChild(root);
		for (int i = 0; i < programs.size(); i++)
		{
			Element program = createProgramElement((Program)programs.get(i));
			root.appendChild(program);
		}
	}
}