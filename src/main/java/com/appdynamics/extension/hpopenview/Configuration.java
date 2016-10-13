package com.appdynamics.extension.hpopenview;

import java.util.List;
import java.util.Map;

public class Configuration {
	
	private String pathToExecutable;
    private String msgGroup;
    private String controllerUrl;
    private Map <String, List <Map <String, String> > > server;
    private List <Map <String, String> > applicationConnectors;
    
    public String getMsgGroup() {
        return msgGroup;
    }

    public void setMsgGroup(String msgGroup) {
        this.msgGroup = msgGroup;
    }

    public String getPathToExecutable() {
        return pathToExecutable;
    }

    public void setPathToExecutable(String pathToExecutable) {
        this.pathToExecutable = pathToExecutable;
    }

    public String getControllerUrl() {
        return controllerUrl;
    }

    public void setControllerUrl(String controllerUrl) {
        this.controllerUrl = controllerUrl;
    }
    public Map <String, List <Map <String, String> > > getServer() {
        return server;
    }

    public void setServer(Map <String, List <Map <String, String> > > server) {
        this.server = server;
    }
    public List <Map <String, String> > getApplicationConnectors() {
        return applicationConnectors;
    }

    public void setApplicationConnectors(List <Map <String, String> > applicationConnectors) {
        this.applicationConnectors = applicationConnectors;
    }
}
