package android.coding.interview.makeitawesome.domain.entity;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Liang on 2016/1/30.
 */
@Root
public class Images {
    @Element(required = false)
    private String EventSmallImagePortrait;
    @Element(required = false)
    private String EventLargeImageLandscape;
    @Element(required = false)
    private String EventMediumImagePortrait;
    @Element(required = false)
    private String EventSmallImageLandscape;
    @Element(required = false)
    private String EventMicroImagePortrait;
    @Element(required = false)
    private String EventLargeImagePortrait;

    public String getEventSmallImagePortrait ()
    {
        return EventSmallImagePortrait;
    }

    public void setEventSmallImagePortrait (String EventSmallImagePortrait)
    {
        this.EventSmallImagePortrait = EventSmallImagePortrait;
    }

    public String getEventLargeImageLandscape ()
    {
        return EventLargeImageLandscape;
    }

    public void setEventLargeImageLandscape (String EventLargeImageLandscape)
    {
        this.EventLargeImageLandscape = EventLargeImageLandscape;
    }

    public String getEventMediumImagePortrait ()
    {
        return EventMediumImagePortrait;
    }

    public void setEventMediumImagePortrait (String EventMediumImagePortrait)
    {
        this.EventMediumImagePortrait = EventMediumImagePortrait;
    }

    public String getEventSmallImageLandscape ()
    {
        return EventSmallImageLandscape;
    }

    public void setEventSmallImageLandscape (String EventSmallImageLandscape)
    {
        this.EventSmallImageLandscape = EventSmallImageLandscape;
    }

    public String getEventMicroImagePortrait ()
    {
        return EventMicroImagePortrait;
    }

    public void setEventMicroImagePortrait (String EventMicroImagePortrait)
    {
        this.EventMicroImagePortrait = EventMicroImagePortrait;
    }

    public String getEventLargeImagePortrait ()
    {
        return EventLargeImagePortrait;
    }

    public void setEventLargeImagePortrait (String EventLargeImagePortrait)
    {
        this.EventLargeImagePortrait = EventLargeImagePortrait;
    }

    @Override
    public String toString()
    {
        return "Images [EventSmallImagePortrait = "+EventSmallImagePortrait+", EventLargeImageLandscape = "+EventLargeImageLandscape+", EventMediumImagePortrait = "+EventMediumImagePortrait+", EventSmallImageLandscape = "+EventSmallImageLandscape+", EventMicroImagePortrait = "+EventMicroImagePortrait+", EventLargeImagePortrait = "+EventLargeImagePortrait+"]";
    }
}
