package org.datapath.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
	
	private int threadcount;
	
	private String author;
	
	private String created;
	
	private String message;
	
	private List<Link> links = new ArrayList<Link>();
	
	public Message(){}
	
	public Message(int threadcount, String author, String message){
		this.threadcount = threadcount;
		this.author = author;
		this.message = message;
		this.created = new Date().toString();
		
	}

	public int getThreadcount() {
		return threadcount;
	}

	public void setThreadcount(int threadcount) {
		this.threadcount = threadcount;
	}



	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public void addlinks(String link,String rel){
		Link l = new Link();
		l.setLink(link);
		l.setRel(rel);
		links.add(l);
		
	}
	

}
