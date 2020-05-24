package com.sparanzza.website.client.application;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.intendia.reactivity.client.CompositeView;
import com.intendia.reactivity.client.Place;
import com.intendia.reactivity.client.PresenterChild;
import com.intendia.reactivity.client.View;
import com.sparanzza.website.client.NameTokens;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

public class ErrorPresenter extends PresenterChild<ErrorPresenter.MyView> {

    public static @Singleton
    class MyPlace extends Place {
        @Inject
        MyPlace(Provider<ErrorPresenter> p) {
            super(NameTokens.ERRORPAGE.getPath(), asSingle(p));
        }
    }

    public static class MyView extends CompositeView implements View {

        FlowPanel container;

        @Inject
        MyView() {

            container = new FlowPanel();
            container.getElement().setAttribute("style", "display: flex; justify-content: center; margin: 50px;");
            container.add(new HTML("ERROR PRESENTER"));
            initWidget(container);
        }
    }

    @Inject
    ErrorPresenter(MyView view, ApplicationPresenter.MainContent parent) {
        super(view, parent);
    }
}
