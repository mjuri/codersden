package uk.codersden.hr.profiles.documents;

import java.util.List;
import java.util.Map;

public class DocumentPayload {
	private Document document;
	private List<Map<String,String>> sharedWith;
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public List<Map<String,String>> getSharedWith() {
		return sharedWith;
	}
	public void setVisibilty(List<Map<String,String>> sharedWith) {
		this.sharedWith = sharedWith;
	}
	
}
