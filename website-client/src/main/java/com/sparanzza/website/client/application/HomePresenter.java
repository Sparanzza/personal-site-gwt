package com.sparanzza.website.client.application;

import com.google.gwt.user.client.ui.FlowPanel;
import com.intendia.reactivity.client.CompositeView;
import com.intendia.reactivity.client.Place;
import com.intendia.reactivity.client.PresenterChild;
import com.intendia.reactivity.client.View;
import com.sparanzza.website.client.ApplicationEntryPoint;
import com.sparanzza.website.client.NameTokens;
import com.sparanzza.website.client.ui.AlertDialog;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import io.reactivex.Single;
import org.jboss.gwt.elemento.core.Widgets;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.sparanzza.website.client.i18n.WebsiteConstants.I18N;
import static com.sparanzza.website.client.resources.Resources.res;
import static org.jboss.gwt.elemento.core.Elements.*;


@Singleton
public class HomePresenter extends PresenterChild<HomePresenter.MyView> {

    public static @Singleton
    class MyPlace extends Place {
        @Inject
        MyPlace(Single<ApplicationEntryPoint.ClientModule.Presenters> p) {
            super(NameTokens.HOMEPAGE.getPath(), p.map(ApplicationEntryPoint.ClientModule.Presenters::home));
        }
    }

    @Inject
    HomePresenter(MyView view, ApplicationPresenter.MainContent parent) {
        super(view, parent);
    }

    public static class MyView extends CompositeView implements View {

        final FlowPanel container;

        @Inject
        MyView() {
            container = new FlowPanel();
            //container.getElement().setAttribute("style", "margin: 0px 35px;");

            HTMLElement alertConstruction = span().add(I18N.underConstruction())
                    .add(span().add(a("https://github.com/Sparanzza/personal-site").add(" Github project"))).element();
            container.add(new AlertDialog(alertConstruction));
            HTMLDivElement contentPresenter = div().style("text-align: center;")
                    .add(div().css(res().style().mv100())
                            .add(h(1).add(I18N.topTitleHome()))
                            .add(h(4).add(I18N.topSubtitleHome())))
                    .add(div().css(res().style().cyanShadowSection() + " " + res().style().mv100())
                            .add(p().add(I18N.mainTextHome()))).element();

            container.add(Widgets.widget(contentPresenter));
            initWidget(container);
        }
    }
}
