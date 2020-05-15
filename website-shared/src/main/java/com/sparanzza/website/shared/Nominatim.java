package com.sparanzza.website.shared;

import com.intendia.gwt.autorest.client.AutoRestGwt;
import io.reactivex.Observable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@AutoRestGwt
@Path("search")
public interface Nominatim {
    @GET Observable<SearchResult> search(@QueryParam("q") String query, @QueryParam("format") String format);

}
