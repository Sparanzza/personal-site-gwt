package com.sparanzza.website.client.application;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.web.bindery.event.shared.EventBus;
import com.intendia.reactivity.client.*;
import com.sparanzza.website.client.ApplicationEntryPoint;
import com.sparanzza.website.client.NameTokens;
import io.reactivex.Single;
import io.reactivex.subjects.BehaviorSubject;

import javax.inject.Inject;
import javax.inject.Singleton;


public class SayHelloPresenter extends PresenterChild<SayHelloPresenter.MyView> {

    public static @Singleton class MyPlace extends Place {
        private BehaviorSubject<String> navigationHistory = BehaviorSubject.createDefault("");

        @Inject
        MyPlace(Single<ApplicationEntryPoint.ClientModule.Presenters> p, EventBus bus) {
            super(NameTokens.SAYHOELLOPAGE.getPath(), p.map(ApplicationEntryPoint.ClientModule.Presenters::sayHello));
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

        FlowPanel container;

        @Inject
        MyView() {
            container = new FlowPanel();
            container.getElement().setAttribute("style", "display: flex; justify-content: center; margin: 50px;");
            container.add(new HTML("HELLO PRESENTER"));
            initWidget(container);
        }

    }

    @Inject
    SayHelloPresenter(MyView view, ApplicationPresenter.MainContent at) {
        super(view, at);
    }
}
