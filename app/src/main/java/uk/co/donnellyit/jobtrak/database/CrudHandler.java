package uk.co.donnellyit.jobtrak.database;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.UnsavedRevision;

import java.util.Map;

import uk.co.donnellyit.jobtrak.model.CouchDocImpl;

/**
 * Created by chrisdonnelly on 26/11/15.
 */
public class CrudHandler {
    public static void  update(final CouchDocImpl item) throws CouchbaseLiteException {
        item.getDocument().update(new Document.DocumentUpdater() {
            @Override
            public boolean update(UnsavedRevision newRevision) {
                Map<String, Object> properties = newRevision.getUserProperties();
                properties = item.buildProperties(properties);
                newRevision.setUserProperties(properties);
                return true;
            }
        });
    }

    public static Document create(Database db, Map<String, Object> properties) throws CouchbaseLiteException {
        Document document = db.createDocument();
        document.putProperties(properties);

        return document;
    }

    public static Document read(Database db, String id) {
        return db.getDocument(id);
    }

    public static void delete() throws CouchbaseLiteException {

    }

}
