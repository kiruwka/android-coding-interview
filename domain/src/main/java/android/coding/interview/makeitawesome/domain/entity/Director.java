package android.coding.interview.makeitawesome.domain.entity;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Liang on 2016/1/30.
 */
@Root
public class Director {
    @Element(required = false)
    private String FirstName;
    @Element(required = false)
    private String LastName;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    @Override
    public String toString() {
        return "Director [FirstName = " + FirstName + ", LastName = " + LastName + "]";
    }
}
