package com.sparanzza.application;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.intendia.reactivity.client.*;
import com.sparanzza.AppEntryPoint;
import com.sparanzza.NameTokens;
import io.reactivex.Single;
import io.reactivex.subjects.BehaviorSubject;

import javax.inject.Inject;
import javax.inject.Singleton;


public class ContactPresenter extends PresenterChild<ContactPresenter.MyView> {

    public static @Singleton class MyPlace extends Place {
        private BehaviorSubject<String> navigationHistory = BehaviorSubject.createDefault("");
        @Inject MyPlace(Single<AppEntryPoint.ClientModule.Presenters> p, EventBus bus) {
            super(NameTokens.contactPage, p.map(AppEntryPoint.ClientModule.Presenters::contact));
            // In GWTP proxy events wake up the presenter, but this will break code splitting,
            // alternatively you can create a middle store or just inject the place in the presenter
            bus.addHandler(PlaceManager.NavigationEvent.TYPE, event -> {
                // p.subscribe(ready -> …); you can wake up the presenter here as GWTP does (bad idea)
                // alternatively, we memoize the state to keep track of the previously visited pages
                String last = navigationHistory.getValue();
                if (last.length() > 0) last += ", ";
                last += event.getRequest().getNameToken();
                navigationHistory.onNext(last);
            });
        }
    }

    public static class MyView extends CompositeView implements View {
        @UiTemplate("ContactView.ui.xml") interface Ui extends UiBinder<Widget, MyView> {
            Ui binder = GWT.create(Ui.class);
        }

        @UiField Label navigationHistory;

        @Inject MyView() { initWidget(Ui.binder.createAndBindUi(this)); }

        public void setNavigationHistory(String navigationHistory) {
            this.navigationHistory.setText(navigationHistory);
        }
    }

    @Inject ContactPresenter(MyView view, ApplicationPresenter.MainContent at, MyPlace place) {
        super(view, at);
        onReveal(place.navigationHistory.doOnNext(n -> getView().setNavigationHistory(n)));
    }
}