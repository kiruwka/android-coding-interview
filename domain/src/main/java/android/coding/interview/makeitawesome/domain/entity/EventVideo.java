package android.coding.interview.makeitawesome.domain.entity;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Liang on 2016/1/30.
 */
@Root
public class EventVideo {

    @Element(required = false)
    private String ThumbnailLocation;

    @Element(required = false)
    private String MediaResourceSubType;

    @Element(required = false)
    private String Location;

    @Element(required = false)
    private String Title;

    @Element(required = false)
    private String MediaResourceFormat;

    public String getThumbnailLocation() {
        return ThumbnailLocation;
    }

    public void setThumbnailLocation(String ThumbnailLocation) {
        this.ThumbnailLocation = ThumbnailLocation;
    }

    public String getMediaResourceSubType() {
        return MediaResourceSubType;
    }

    public void setMediaResourceSubType(String MediaResourceSubType) {
        this.MediaResourceSubType = MediaResourceSubType;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getMediaResourceFormat() {
        return MediaResourceFormat;
    }

    public void setMediaResourceFormat(String MediaResourceFormat) {
        this.MediaResourceFormat = MediaResourceFormat;
    }

    @Override
    public String toString() {
        return "EventVideo [ThumbnailLocation = " + ThumbnailLocation + ", MediaResourceSubType = " + MediaResourceSubType + ", Location = " + Location + ", Title = " + Title + ", MediaResourceFormat = " + MediaResourceFormat + "]";
    }
}

