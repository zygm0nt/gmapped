package pl.ftang.example.feeder.model;

import org.jcouchdb.document.BaseDocument;
import org.svenson.JSONProperty;

/**
 * User: mcl
 * Date: 3/21/11 9:01 AM
 */
public class Note extends BaseDocument {

    Long objectId;
    String eventCaseId;
    String eventMsisdn;
    String note;
    String createdDate;
    String eventCategory;
    String city;
    String address;
    String firstName;
    String lastName;

    @JSONProperty("objectId")
    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long id) {
        this.objectId = id;
    }

    @JSONProperty("event_case_id")
    public String getEventCaseId() {
        return eventCaseId;
    }

    public void setEventCaseId(String eventCaseId) {
        this.eventCaseId = eventCaseId;
    }

    @JSONProperty("event_msisdn")
    public String getEventMsisdn() {
        return eventMsisdn;
    }

    public void setEventMsisdn(String eventMsisdn) {
        this.eventMsisdn = eventMsisdn;
    }

    @JSONProperty("note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @JSONProperty("created_date")
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @JSONProperty("event_category")
    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    @JSONProperty("city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @JSONProperty("address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JSONProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JSONProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
