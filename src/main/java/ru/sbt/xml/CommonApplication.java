package ru.sbt.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Application")
public class CommonApplication {

    private String applicationID;

    @XmlElement(name = "applicationID")
    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommonApplication that = (CommonApplication) o;

        return applicationID.equals(that.applicationID);
    }

    @Override
    public int hashCode() {
        return applicationID.hashCode();
    }

    @Override
    public String toString() {
        return "CommonApplication{" +
                "applicationID='" + applicationID + '\'' +
                '}';
    }
}
