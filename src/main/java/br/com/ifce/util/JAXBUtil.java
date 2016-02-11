package br.com.ifce.util;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;

import br.com.ifce.domain.Car;
import br.com.ifce.domain.ListCars;

public class JAXBUtil {
	private static JAXBUtil instance;
	private static JAXBContext context;
	
	public static JAXBUtil getInstance() {
		return instance;
	}
	
	static{
		try{
			context = JAXBContext.newInstance(ListCars.class,Car.class);
		}catch(JAXBException e){
			throw new RuntimeException(e);
		}
	}
	
	public static String toXML(Object object) throws IOException{
		try {
			StringWriter writer = new StringWriter(); 
			Marshaller marshaller= context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(object, writer);
			String xml = writer.toString();
			return xml;
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String toJson(Object object) throws IOException{
		
		try {
			StringWriter writer = new StringWriter(); 
			Marshaller marshaller= context.createMarshaller();
			MappedNamespaceConvention con  = new MappedNamespaceConvention();
			XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con, writer);
			marshaller.marshal(object, xmlStreamWriter);
			String json = writer.toString();
			return json;
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
}
