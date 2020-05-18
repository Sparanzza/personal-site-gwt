package com.sparanzza.website.client.application;

import com.google.gwt.user.client.ui.HTML;
import com.intendia.reactivity.client.CompositeView;
import com.intendia.reactivity.client.Place;
import com.intendia.reactivity.client.PresenterChild;
import com.sparanzza.website.client.NameTokens;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

public class EmptyPresenter extends PresenterChild<EmptyPresenter.MyView> {

    public static @Singleton class MyPlace extends Place {
        @Inject MyPlace(Provider<EmptyPresenter> p) {
            super(NameTokens.emptyPage, asSingle(p));
        }
    }

    public static class MyView extends CompositeView {
        @Inject MyView() { initWidget(new HTML("\uD83D\uDE42")); }
    }

    @Inject EmptyPresenter(MyView view, ApplicationPresenter.MainContent parent) { super(view, parent); }
}