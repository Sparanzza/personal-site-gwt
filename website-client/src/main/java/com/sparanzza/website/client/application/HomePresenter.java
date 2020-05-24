package com.sparanzza.website.client.application;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.intendia.reactivity.client.CompositeView;
import com.intendia.reactivity.client.Place;
import com.intendia.reactivity.client.PresenterChild;
import com.intendia.reactivity.client.View;
import com.sparanzza.website.client.ApplicationEntryPoint;
import com.sparanzza.website.client.NameTokens;
import com.sparanzza.website.client.ui.AlertDialog;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.sparanzza.website.client.i18n.WebsiteConstants.I18N;


@Singleton
public class HomePresenter extends PresenterChild<HomePresenter.MyView> {

    public static @Singleton class MyPlace extends Place {
        @Inject MyPlace(Single<ApplicationEntryPoint.ClientModule.Presenters> p) {
            super(NameTokens.HOMEPAGE.getPath(), p.map(ApplicationEntryPoint.ClientModule.Presenters::home));
        }
    }

    @Inject
    HomePresenter(MyView view, ApplicationPresenter.MainContent parent) {
        super(view, parent);
    }

    public static class MyView extends CompositeView  implements View{

        final FlowPanel container;
        @Inject MyView() {

            container = new FlowPanel();
            container.getElement().setAttribute("style", "margin: 50px;");
            container.add(new AlertDialog("Esta web constante construccion, fecha de la actualizacion 24/05/2020, https://github.com/Sparanzza/personal-site"));
            container.add( new HTML(I18N.mainTextHome()));

            initWidget(container);
        }
    }
}
