package com.sparanzza.website.client.application;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.intendia.gwt.autorest.client.RequestResourceBuilder;
import com.intendia.gwt.autorest.client.ResourceVisitor;
import com.intendia.reactivity.client.*;
import com.intendia.rxgwt2.user.RxHandlers;
import com.sparanzza.website.client.ApplicationEntryPoint;
import com.sparanzza.website.client.NameTokens;
import com.sparanzza.website.shared.NominatimRestService;
import com.sparanzza.website.shared.NominatimRestService_RestServiceModel;
import elemental2.dom.HTMLDivElement;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import static org.jboss.gwt.elemento.core.Elements.div;


@Singleton
public class HomePresenter extends PresenterChild<HomePresenter.MyView> {

    public static @Singleton class MyPlace extends Place {
        @Inject MyPlace(Single<ApplicationEntryPoint.ClientModule.Presenters> p) {
            super(NameTokens.homePage, p.map(ApplicationEntryPoint.ClientModule.Presenters::home));
        }
    }


    @Inject HomePresenter(MyView view, ApplicationPresenter.MainContent parent, Provider<MyPopupPresenter> popup) {
        super(view, parent);

        onReveal(RxHandlers.click(view.popup).doOnNext(ev -> addToPopupSlot(popup.get())));

    }


    public static class MyView extends CompositeView {

        final FlowPanel container;
        final Button popup;
        @Inject MyView() {
            HTMLDivElement p = div().add("Hello from Elemento!").get();
            popup = new Button("Rest Nominatim");
            container = new FlowPanel();

            container.add(popup);
            container.getElement().setAttribute("style", "display: flex; justify-content: center; margin: 50px;");

            initWidget(container);
        }
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
                dialog.getCaption().setText("Autorest Nominatim!️");
                FlowPanel panel = new FlowPanel(); dialog.add(panel);
                FlowPanel msg = new FlowPanel();
                panel.add(msg);
                panel.add(close = new Button("Close"));

                NominatimRestService nominatim = new NominatimRestService_RestServiceModel(() -> osm());
                nominatim.search("Málaga,España", "json").subscribe(n -> {
                    msg.add(new HTML("[" + (n.importance * 10.) + "] " + n.display_name + " (" + n.lon + "," + n.lat + ")"));
                    GWT.log("[" + (n.importance * 10.) + "] " + n.display_name + " (" + n.lon + "," + n.lat + ")");
                });
            }
            static ResourceVisitor osm() { return new RequestResourceBuilder().path("https://nominatim.openstreetmap.org/"); }
        }
    }
}
