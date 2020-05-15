package com.sparanzza.website.client.application;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.*;
import com.intendia.gwt.autorest.client.RequestResourceBuilder;
import com.intendia.gwt.autorest.client.ResourceVisitor;
import com.intendia.reactivity.client.*;
import com.intendia.rxgwt2.user.RxHandlers;
import com.sparanzza.website.AppEntryPoint;
import com.sparanzza.website.NameTokens;
import com.sparanzza.website.shared.Nominatim;
import com.sparanzza.website.shared.Nominatim_RestServiceModel;
import io.reactivex.Observable;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import static com.intendia.rxgwt2.user.RxUser.bindValueChangeOr;


@Singleton
public class HomePresenter extends PresenterChild<HomePresenter.MyView> {

    public static @Singleton class MyPlace extends Place {
        @Inject MyPlace(Single<AppEntryPoint.ClientModule.Presenters> p) {
            super(NameTokens.homePage, p.map(AppEntryPoint.ClientModule.Presenters::home));
        }
    }

    public static class MyView extends CompositeView {
        @UiTemplate("HomeView.ui.xml") interface Ui extends UiBinder<Widget, MyView> {
            Ui binder = GWT.create(Ui.class);
        }

        @UiField IntegerBox a;
        @UiField IntegerBox b;
        @UiField InlineLabel c;
        @UiField Button showPopup;

        @Inject MyView() { initWidget(Ui.binder.createAndBindUi(this)); }
    }

    @Inject HomePresenter(MyView view, ApplicationPresenter.MainContent parent, Provider<MyPopupPresenter> popup) {
        super(view, parent);
        onReveal(Observable.combineLatest(bindValueChangeOr(view.a, 0), bindValueChangeOr(view.b, 0), Integer::sum)
                .doOnNext(c -> view.c.setText(Integer.toString(c))));
        // GWT valueBox send only events on blur, so bind keyUp and force change events to get better interactivity
        onReveal(RxHandlers.keyUp(view.a).doOnNext(ev -> ValueChangeEvent.fire(view.a, view.a.getValue())));
        onReveal(RxHandlers.keyUp(view.b).doOnNext(ev -> ValueChangeEvent.fire(view.b, view.b.getValue())));
        onReveal(RxHandlers.click(view.showPopup).doOnNext(ev -> addToPopupSlot(popup.get())));
    }

    static class MyPopupPresenter extends PresenterWidget<MyPopupPresenter.MyPopupView> {

        @Inject MyPopupPresenter(MyPopupView view) {
            super(view);
            onReveal(RxHandlers.click(view.close).doOnNext(ev -> view.hide()));
        }

        static class MyPopupView extends CompositePopupView {
            final Button close;

            @Inject MyPopupView() {
                DialogBox dialog = new DialogBox(); initWidget(dialog);
                dialog.setModal(false);
                dialog.getCaption().setText("Survival dialog!️");
                FlowPanel panel = new FlowPanel(); dialog.add(panel);
                panel.add(new HTML("<p style='max-width: 400px;'>Hi! this dialog will keep open even if you navigate "
                        + "to some other place. But, it will be hide until you go back to this place. If you want to "
                        + "close it, use the close button.</p>"));
                panel.add(close = new Button("Close"));

                Nominatim nominatim = new Nominatim_RestServiceModel(() -> osm());
                nominatim.search("Málaga,España", "json").subscribe(n -> {
                    GWT.log("[" + (n.importance * 10.) + "] " + n.display_name + " (" + n.lon + "," + n.lat + ")");
                });
            }
            static ResourceVisitor osm() { return new RequestResourceBuilder().path("http://nominatim.openstreetmap.org/"); }
        }
    }
}
