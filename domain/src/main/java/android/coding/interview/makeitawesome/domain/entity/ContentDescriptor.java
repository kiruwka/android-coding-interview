package android.coding.interview.makeitawesome.domain.entity;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Liang on 2016/1/30.
 */
@Root
public class ContentDescriptor {
    @Element(required = false)
    private String Name;
    @Element(required = false)
    private String ImageURL;

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public String getImageURL ()
    {
        return ImageURL;
    }

    public void setImageURL (String ImageURL)
    {
        this.ImageURL = ImageURL;
    }

    @Override
    public String toString()
    {
        return "ContentDescriptor [Name = "+Name+", ImageURL = "+ImageURL+"]";
    }
}
