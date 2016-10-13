package com.appdynamics.extension.hpopenview.api;

public class Alert {
	
	private String application;
	
	private String object;

    private String severity;

	private String msgText;
	
    private String msgGroup;

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {

        this.object = object;
	}

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public String getMsgGroup() {
        return msgGroup;
    }

    public void setMsgGroup(String msgGroup) {
        this.msgGroup = msgGroup;
    }
}
