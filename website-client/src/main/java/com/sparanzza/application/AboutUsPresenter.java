package com.sparanzza.application;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import com.intendia.reactivity.client.CompositeView;
import com.intendia.reactivity.client.Place;
import com.intendia.reactivity.client.PresenterChild;
import com.intendia.reactivity.client.View;
import com.sparanzza.AppEntryPoint;
import com.sparanzza.NameTokens;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

public class AboutUsPresenter extends PresenterChild<AboutUsPresenter.MyView> {

    public static @Singleton class MyPlace extends Place {
        @Inject MyPlace(Single<AppEntryPoint.ClientModule.Presenters> p) {
            super(NameTokens.aboutUsPage, p.map(AppEntryPoint.ClientModule.Presenters::aboutUs));
        }
    }

    @Inject AboutUsPresenter(MyView view, ApplicationPresenter.MainContent at) { super(view, at); }

    public static class MyView extends CompositeView implements View {
        @UiTemplate("AboutUsView.ui.xml") interface Ui extends UiBinder<Widget, MyView> {
            Ui binder = GWT.create(Ui.class);
        }

        @Inject MyView() { initWidget(Ui.binder.createAndBindUi(this)); }
    }
}
