package com.sparanzza.website.client.application;

import com.google.gwt.user.client.ui.FlowPanel;
import com.intendia.reactivity.client.CompositeView;
import com.intendia.reactivity.client.Place;
import com.intendia.reactivity.client.PresenterChild;
import com.intendia.reactivity.client.View;
import com.sparanzza.website.client.ApplicationEntryPoint;
import com.sparanzza.website.client.NameTokens;
import com.sparanzza.website.client.ui.AlertDialog;
import elemental2.dom.HTMLElement;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.sparanzza.website.client.i18n.WebsiteConstants.I18N;
import static org.jboss.gwt.elemento.core.Elements.a;
import static org.jboss.gwt.elemento.core.Elements.span;

public class SkillsPresenter extends PresenterChild<SkillsPresenter.MyView> {

    public static @Singleton class MyPlace extends Place {
        @Inject MyPlace(Single<ApplicationEntryPoint.ClientModule.Presenters> p) {
            super(NameTokens.SKILLSPAGES.getPath(), p.map(ApplicationEntryPoint.ClientModule.Presenters::skills));
        }
    }

    public static class MyView extends CompositeView implements View {

        FlowPanel container;

        @Inject
        MyView() {

            container = new FlowPanel();
            container.getElement().setAttribute("style", "margin: 0px 35px;");
            HTMLElement alertConstruction = span().add(I18N.underConstruction())
                    .add(span().add(a("https://www.linkedin.com/in/sparanzza/").add(" por favor visite perfil linkedIn."))).element();
            container.add(new AlertDialog(alertConstruction));

            initWidget(container);
        }
    }

    @Inject
    SkillsPresenter(SkillsPresenter.MyView view, ApplicationPresenter.MainContent at) {
        super(view, at);
    }
}
