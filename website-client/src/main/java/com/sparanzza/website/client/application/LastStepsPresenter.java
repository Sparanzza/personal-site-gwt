package com.sparanzza.website.client.application;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.intendia.reactivity.client.CompositeView;
import com.intendia.reactivity.client.Place;
import com.intendia.reactivity.client.PresenterChild;
import com.intendia.reactivity.client.View;
import com.sparanzza.website.client.ApplicationEntryPoint;
import com.sparanzza.website.client.NameTokens;
import com.sparanzza.website.client.i18n.WebsiteConstants;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

public class LastStepsPresenter extends PresenterChild<LastStepsPresenter.MyView> {

    public static @Singleton
    class MyPlace extends Place {
        @Inject
        MyPlace(Single<ApplicationEntryPoint.ClientModule.Presenters> p) {
            super(NameTokens.LASTSTEPSPAGE.getPath(), p.map(ApplicationEntryPoint.ClientModule.Presenters::lastSteps));
        }
    }

    public static class MyView extends CompositeView implements View {

        FlowPanel container;

        @Inject
        MyView() {

            container = new FlowPanel();
            container.getElement().setAttribute("style", "display: flex; justify-content: center; margin: 50px;");
            container.add(new HTML(WebsiteConstants.I18N.mainTextLastSteps()));

            initWidget(container);
        }
    }

    @Inject
    LastStepsPresenter(LastStepsPresenter.MyView view, ApplicationPresenter.MainContent at) {
        super(view, at);
    }
}
