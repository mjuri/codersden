package uk.codersden.hr.profiles.documents;

import java.util.List;
import java.util.Map;

public class DocumentPayload {
	private Document document;
	private List<Map<String,String>> sharedWith;
	private Boolean isOnboarding;
	private String todoIdentifier;
	
	public Boolean getIsOnboarding() {
		return isOnboarding;
	}
	public void setIsOnboarding(Boolean isOnboarding) {
		this.isOnboarding = isOnboarding;
	}
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

	public void setSharedWith(List<Map<String, String>> sharedWith) {
		this.sharedWith = sharedWith;
	}
	public String getTodoIdentifier() {
		return todoIdentifier;
	}
	public void setTodoIdentifier(String todoIdentifier) {
		this.todoIdentifier = todoIdentifier;
	}
	
}
