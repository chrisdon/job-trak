package uk.co.donnellyit.jobtrak.model;

import com.couchbase.lite.Document;

/**
 * Created by chrisdonnelly on 26/11/15.
 */
public abstract class CouchDocImpl implements CouchDoc {
    private Document document;

    public CouchDocImpl(Document doc) {
        document = doc;
    }

    public Document getDocument() {
        return document;
    }

    public String getId() {
        return document.getId();
    }
}
