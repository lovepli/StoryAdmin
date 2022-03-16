package com.story.storyadmin.framework.http.demo2;


import java.util.ArrayList;
import java.util.List;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class WebHandler extends DefaultHandler {

    private List<Entity> entityList;


    private List<Mapping> mappingList;

    private Entity entity;
    private Mapping mapping;

    private String tag;
    private boolean isMap;

    public WebHandler() {

    }
    public List<Entity> getEntityList() {
        return entityList;
    }

    public List<Mapping> getMappingList() {
        return mappingList;
    }
    @Override
    public void startDocument() throws SAXException {
        entityList=new ArrayList<Entity>();
        mappingList=new ArrayList<Mapping>();
    }

    @Override
    public void endDocument() throws SAXException {
//		for (Mapping mapping : mappingList) {
//			if(mapping==null)
//				continue;
//			String name;
//			if(mapping.getName()!=null)
//				name=mapping.getName();
//			else
//				name="null";
//			List<String> urlList=mapping.getUrl();
//			for (String url:urlList) {
//				System.out.println(name+"---->"+url);
//			}
//		}
//		for (Entity entity:entityList) {
//			String servletname=entity.getName();
//			String clz=entity.getClz();
//			System.out.println(servletname+"---->"+clz);
//		}
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //System.out.println("开始处理"+"--->"+qName);
        if(null!=qName) {
            if(qName.equals("servlet")) {
                isMap=false;
                entity=new Entity();
            }else if(qName.equals("servlet-mapping")){
                isMap=true;
                mapping=new Mapping();
            }
        }
        tag=qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //System.out.println("结束处理"+"--->"+qName);
        if(null!=qName) {
            if(qName.equals("servlet")) {
                entityList.add(entity);
            }else if(qName.equals("servlet-mapping")){
                mappingList.add(mapping);
            }
        }
        tag=null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str=new String(ch, start, length);
        //System.out.println("处理中"+"--->"+str);
        if(tag!=null&&str!=null&&!str.trim().equals("")) {
            if(!isMap) {
                if(tag.equals("servlet-name"))
                    entity.setName(str);
                else if(tag.equals("servlet-class"))
                    entity.setClz(str);
            }else {
                if(tag.equals("servlet-name"))
                    mapping.setName(str);
                else if(tag.equals("url"))
                    mapping.getUrl().add(str);
            }
        }
    }

}
