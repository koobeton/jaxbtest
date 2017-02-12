package ru.sbt.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GetApplicationDetailsRs")
public class GetApplicationDetailsRs {

    private Application application;

    @XmlElement(name = "Application")
    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GetApplicationDetailsRs that = (GetApplicationDetailsRs) o;

        return application.equals(that.application);
    }

    @Override
    public int hashCode() {
        return application.hashCode();
    }

    @Override
    public String toString() {
        return "GetApplicationDetailsRs{" +
                "application=" + application +
                '}';
    }

    public static class Application extends CommonApplication {
        @Override
        public String toString() {
            return "Application{" +
                    "applicationID='" + super.getApplicationID() + '\'' +
                    '}';
        }
    }
}
