package com.sparanzza.website.shared;

import io.reactivex.Observable;

public class DefaultNominatimRestService implements NominatimRestService{
    @Override
    public Observable<SearchResult> search(String query, String format) {
        System.out.println("Hola desde AutoRest");
        return null;
    }
}