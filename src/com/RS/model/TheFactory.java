package com.RS.model;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wkBule.docFiller.utils.LoadResource;
import wkBule.docFiller.xml.contentDescriptor.Content;
import wkBule.docFiller.xml.contentDescriptor.ContentList;
import wkBule.docFiller.xml.contentDescriptor.ObjectFactory;

class TheFactory {
	private static Logger log = LoggerFactory.getLogger(TheFactory.class);
	private static Map<String, Content> contentInfo;
	private static List<Content> contentList;

	public TheFactory() {
		if (contentInfo == null) {
			try {
				JAXBElement<?> e = (JAXBElement<?>) JAXBContext
				.newInstance(ObjectFactory.class).createUnmarshaller()
				.unmarshal(LoadResource.load("ContentInfo.xml"));
				
				contentList = ((ContentList)e.getValue()).getContent();
				contentInfo = new Hashtable<String, Content>();

				for (Content c : contentList) {
					contentInfo.put(c.getType(), c);
				}
			} catch (IOException | JAXBException e) {
				log.error("Exception occurred during parsing ContentInfo.xml",
				e);
			}
		}
	}

	protected ProjectFactory getProjectFactory(String projectType) {
		String projectFactoryClass = contentInfo.get(projectType).getProjectClass()+"$Factory";
		try {
			Class<?> factoryClass = (Class<?>)Class.forName(projectFactoryClass);
			return (ProjectFactory)factoryClass.newInstance();
		} catch (ClassNotFoundException e) {
			log.error("Cannot find the factory of specified projectType: "+projectType,e);
			return null;
		} catch (Exception e) {
			log.error("Exception occurred during get the Factory of specified projectType: "+projectType,e);
			return null;
		}
		
	}

	public List<Content> getProjectsList() {
		
		return contentList;
	}
	
	public static Map<String,Content> getProjectsMap(){
		return contentInfo;
	}
}
