package uk.co.donnellyit.jobtrak.model;

import java.util.Map;

/**
 * Created by chrisdonnelly on 17/09/2014.
 */
public interface CouchDoc {
    Map<String, Object> buildProperties(Map<String, Object> properties);

}
