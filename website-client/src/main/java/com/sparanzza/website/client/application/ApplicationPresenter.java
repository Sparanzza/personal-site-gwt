package com.sparanzza.website.client.application;


import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;
import com.intendia.reactivity.client.CompositeView;
import com.intendia.reactivity.client.PlaceManager.LockInteractionEvent;
import com.intendia.reactivity.client.PresenterChild;
import com.intendia.reactivity.client.RevealableComponent;
import com.intendia.reactivity.client.RootPresenter.RootContentSlot;
import com.intendia.reactivity.client.Slots.NestedSlot;
import com.intendia.reactivity.client.View;
import com.sparanzza.website.client.ui.Footer;
import com.sparanzza.website.client.ui.TopMenu;
import dagger.Lazy;
import io.reactivex.Observable;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.intendia.rxgwt2.user.RxUser.register;

/**
 * This is the top-level presenter of the hierarchy. Other presenters reveal themselves within this presenter.
 * <p/>
 * The goal of this sample is to show how to use nested presenters. These can be useful to decouple multiple presenters
 * that need to be displayed on the screen simultaneously.
 */
public class ApplicationPresenter extends PresenterChild<ApplicationPresenter.MyView> {

    /**
     * This is the top-level view of the application. Every time another presenter wants to reveal itself, {@link
     * MyView} will add its content of the target inside the {@code mainContentPanel}.
     */
    public static @Singleton
    class MyView extends CompositeView implements View {

        FlowPanel rootContent;
        FlowPanel loadingMessage;
        SimplePanel mainContentPanel;
        TopMenu topMenu;
        Footer footer;

        @Inject
        MyView(MainContent slot) {
            rootContent = new FlowPanel();
            loadingMessage = new FlowPanel(); rootContent.add(loadingMessage);
            topMenu = new TopMenu(); rootContent.add(topMenu);
            mainContentPanel = new SimplePanel(); rootContent.add(mainContentPanel);
            rootContent.add(new Footer());
            initWidget(rootContent);
            bindSlot(slot, (HasOneWidget) mainContentPanel);
        }

        public void showLoading(boolean visible) {
            loadingMessage.setVisible(visible);
        }
    }

    public static @Singleton
    class MainContent extends NestedSlot<RevealableComponent> {
        @Inject
        MainContent(Lazy<ApplicationPresenter> presenter) {
            super(Single.fromCallable(presenter::get));
        }
    }

    @Inject
    ApplicationPresenter(MyView view, RootContentSlot root, EventBus bus) {
        super(view, root);
        view.showLoading(false);
        onReveal(onLockInteraction(bus).doOnNext(ev -> getView().showLoading(ev.shouldLock())));
    }

    private static Observable<LockInteractionEvent> onLockInteraction(EventBus bus) {
        return Observable.create(em -> register(em, bus.addHandler(LockInteractionEvent.TYPE, em::onNext)));
    }
}
