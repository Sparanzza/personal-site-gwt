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
public class LastStepsPresenter extends PresenterChild<LastStepsPresenter.MyView> {

    public static @Singleton class MyPlace extends Place {
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

            HTMLElement alertConstruction = span().add(I18N.underConstruction())
                    .add(span().add(a("https://github.com/Sparanzza/personal-site").add(" Github project"))).element();
            container.add(new AlertDialog(alertConstruction));

            HTMLDivElement contentPresenter = div().add(div().style("display: flex; justify-content: center;")
                    .add(img("/img/icons/route.svg").css(res().style().mv100())))
                    .add(div().style("text-align: center; margin: 20px;")
                            .add(h(1).add("Algo más que una experiencia laboral")
                            .add(h(5).add("Podría decir que he querido superarme siempre en mi trabajo, dia a dia, pero lo que estoy mas orgulloso es ..."))))
                    .add(div().style("text-align: justify")

                    .add(div().css(res().style().mv100())
                            .add(div().style("max-width: 900px; margin: auto;").add(h(2).add("En la actualidad").style("margin: 30px 0px;"))
                            .add(Widgets.element(new HTML(I18N.lastStepsIntendia())))
                            .add(div().style("display: flex; justify-content: center; margin-top: 30px; opacity: .5;")
                                    .add(img("/img/logos/intendia.png").style("width: 300px")))))

                    .add(div().css(res().style().mv100())
                            .add(div().style("max-width: 900px; margin: auto;").add(h(2).add("Spin Off").style("margin: 30px 0px;"))
                            .add(Widgets.element(new HTML(I18N.lastStepsMissMotion())))
                            .add(div().style("display: flex; justify-content: center; margin-top: 30px; opacity: .75;")
                                    .add(img("/img/logos/missmotion.png").style("width: 200px")))))

                    .add(div().css(res().style().mv100())
                            .add(div().style("max-width: 900px; margin: auto;").add(h(2).add("Grandes aventuras").style("margin: 30px 0px;"))
                            .add(Widgets.element(new HTML(I18N.lastStepsGoldenRace())))
                            .add(div().style("display: flex; justify-content: center; margin-top: 30px; mix-blend-mode: multiply; opacity: .75;")
                                    .add(img("/img/logos/goldenrace.png").style("width: 400px;")))))

                    .add(div().css(res().style().mv100())
                            .add(div().style("max-width: 900px; margin: auto;").add(h(2).add("El cambio").style("margin: 30px 0px;"))
                            .add(Widgets.element(new HTML(I18N.lastStepsCiclo())))
                            .add(div().style("mix-blend-mode: darken; display: flex; justify-content: center; margin-top: 30px; opacity: .75;")
                                    .add(img("/img/logos/ilerna.webp").style("width: 200px; margin-right:10px"))
                                    .add(img("/img/logos/hostgreen.png").style("width: 300px; height: 200px;")))))

                    .add(div().css(res().style().mv100())
                            .add(div().style("max-width: 900px; margin: auto;").add(h(2).add("Periodo broadcast").style("margin: 30px 0px;"))
                            .add(Widgets.element(new HTML(I18N.lastStepsLasexta())))
                            .add(div().style("display: flex; justify-content: center; margin-top: 30px; opacity: .5;")
                                    .add(img("/img/logos/lasexta.webp").style("width: 100px"))))))
                    .element();
            container.add(Widgets.widget(contentPresenter));

            initWidget(container);
        }
    }

    @Inject
    LastStepsPresenter(LastStepsPresenter.MyView view, ApplicationPresenter.MainContent at) {
        super(view, at);
    }
}
