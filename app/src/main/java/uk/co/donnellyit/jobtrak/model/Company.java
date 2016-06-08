package uk.co.donnellyit.jobtrak.model;

import com.couchbase.lite.Document;

import java.util.Map;

import io.realm.RealmObject;

/**
 * Created by chrisdonnelly on 13/06/2014.
 */
public class Company extends RealmObject {
    public final static String ADDRESS = "address";
    public final static String NAME = "name";
    public final static String TYPE = "type";
    public final static String URL = "url";

    private String type;
    private String name;
    private String address;
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /*
    public Company(Document document) {
        super(document);
        Map<String, Object> properties = document.getProperties();
        if(properties != null) {
            address = (String) properties.get(ADDRESS);
            name = (String) properties.get(NAME);
            type = (String) properties.get(TYPE);
            url = (String) properties.get(URL);
        }

    }


    @Override
    public Map<String, Object> buildProperties(Map<String, Object> properties) {
        properties.put(ADDRESS, address);
        properties.put(NAME, name);
        properties.put(TYPE, type);
        properties.put(URL, url);
        return properties;
    }
    */

}
