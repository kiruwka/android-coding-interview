package android.coding.interview.makeitawesome.domain.entity;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Liang on 2016/1/30.
 */
@Root(name = "Event")
public class Event {
    @Element(required = false)
    private String RatingLabel;
    @Element(required = false)
    private String EventURL;
    @Element(required = false)
    private String RatingImageUrl;
    @Element(required = false)
    private Images Images;
    @Element(required = false)
    private String EventType;
    @Element(required = false)
    private String Title;
    @Element(required = false)
    private String dtLocalRelease;
    @ElementList(required = false)
    private List<ContentDescriptor> ContentDescriptors;
    @Element(required = false)
    private String LengthInMinutes;
    @Element(required = false)
    private String GlobalDistributorName;
    @ElementList(required = false)
    private List<Director> Directors;
    @Element(required = false)
    private String Rating;
    @ElementList(required = false)
    private List<EventVideo> Videos;
    @Element(required = false)
    private String OriginalTitle;
    @ElementList(required = false)
    private List<Actor> Cast;
    @Element(required = false)
    private String ID;
    @Element(required = false)
    private String Genres;
    @Element(required = false)
    private String ShortSynopsis;
    @Element(required = false)
    private String Synopsis;
    @Element(required = false)
    private String ProductionCompanies;
    @Element(required = false)
    private String LocalDistributorName;
    @Element(required = false)
    private String ProductionYear;

    public String getRatingLabel() {
        return RatingLabel;
    }

    public void setRatingLabel(String RatingLabel) {
        this.RatingLabel = RatingLabel;
    }

    public String getEventURL() {
        return EventURL;
    }

    public void setEventURL(String EventURL) {
        this.EventURL = EventURL;
    }

    public String getRatingImageUrl() {
        return RatingImageUrl;
    }

    public void setRatingImageUrl(String RatingImageUrl) {
        this.RatingImageUrl = RatingImageUrl;
    }

    public Images getImages() {
        return Images;
    }

    public void setImages(Images Images) {
        this.Images = Images;
    }

    public String getEventType() {
        return EventType;
    }

    public void setEventType(String EventType) {
        this.EventType = EventType;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDtLocalRelease() {
        return dtLocalRelease;
    }

    public void setDtLocalRelease(String dtLocalRelease) {
        this.dtLocalRelease = dtLocalRelease;
    }

    public List<ContentDescriptor> getContentDescriptors() {
        return ContentDescriptors;
    }

    public void setContentDescriptors(List<ContentDescriptor> ContentDescriptors) {
        this.ContentDescriptors = ContentDescriptors;
    }

    public String getLengthInMinutes() {
        return LengthInMinutes;
    }

    public void setLengthInMinutes(String LengthInMinutes) {
        this.LengthInMinutes = LengthInMinutes;
    }

    public String getGlobalDistributorName() {
        return GlobalDistributorName;
    }

    public void setGlobalDistributorName(String GlobalDistributorName) {
        this.GlobalDistributorName = GlobalDistributorName;
    }

    public List<Director> getDirectors() {
        return Directors;
    }

    public void setDirectors(List<Director> Directors) {
        this.Directors = Directors;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String Rating) {
        this.Rating = Rating;
    }

    public List<EventVideo> getVideos() {
        return Videos;
    }

    public void setVideos(List<EventVideo> Videos) {
        this.Videos = Videos;
    }

    public String getOriginalTitle() {
        return OriginalTitle;
    }

    public void setOriginalTitle(String OriginalTitle) {
        this.OriginalTitle = OriginalTitle;
    }

    public List<Actor> getCast() {
        return Cast;
    }

    public void setCast(List<Actor> Cast) {
        this.Cast = Cast;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getGenres() {
        return Genres;
    }

    public void setGenres(String Genres) {
        this.Genres = Genres;
    }

    public String getShortSynopsis() {
        return ShortSynopsis;
    }

    public void setShortSynopsis(String ShortSynopsis) {
        this.ShortSynopsis = ShortSynopsis;
    }

    public String getSynopsis() {
        return Synopsis;
    }

    public void setSynopsis(String Synopsis) {
        this.Synopsis = Synopsis;
    }

    public String getProductionCompanies() {
        return ProductionCompanies;
    }

    public void setProductionCompanies(String ProductionCompanies) {
        this.ProductionCompanies = ProductionCompanies;
    }

    public String getLocalDistributorName() {
        return LocalDistributorName;
    }

    public void setLocalDistributorName(String LocalDistributorName) {
        this.LocalDistributorName = LocalDistributorName;
    }

    public String getProductionYear() {
        return ProductionYear;
    }

    public void setProductionYear(String ProductionYear) {
        this.ProductionYear = ProductionYear;
    }

    @Override
    public String toString() {
        return "Event [RatingLabel = " + RatingLabel + ", EventURL = " + EventURL + ", RatingImageUrl = " + RatingImageUrl + ", Images = " + Images + ", EventType = " + EventType + ", Title = " + Title + ", dtLocalRelease = " + dtLocalRelease + ", ContentDescriptors = " + ContentDescriptors + ", LengthInMinutes = " + LengthInMinutes + ", GlobalDistributorName = " + GlobalDistributorName + ", Directors = " + Directors + ", Rating = " + Rating + ", Videos = " + Videos + ", OriginalTitle = " + OriginalTitle + ", Cast = " + Cast + ", ID = " + ID + ", Genres = " + Genres + ", ShortSynopsis = " + ShortSynopsis + ", Synopsis = " + Synopsis + ", ProductionCompanies = " + ProductionCompanies + ", LocalDistributorName = " + LocalDistributorName + ", ProductionYear = " + ProductionYear + "]";
    }
}
