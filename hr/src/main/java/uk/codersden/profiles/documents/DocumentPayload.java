package uk.codersden.profiles.documents;

import java.util.List;

public class DocumentPayload {
	private Document document;
	private List<String> sharedWith;
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public List<String> getSharedWith() {
		return sharedWith;
	}
	public void setVisibilty(List<String> sharedWith) {
		this.sharedWith = sharedWith;
	}
	
}
