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

            HTMLElement alertConstruction = span().add(I18N.underConstruction())
                    .add(span().add(a("https://github.com/Sparanzza/personal-site").add(" Github project"))).element();
            container.add(new AlertDialog(alertConstruction));
            HTMLDivElement contentPresenter = div().style("text-align: center;")
                    .add(div().css(res().style().mv100())
                            .add(h(1).style("margin: 20px;").add(I18N.topTitleHome()))
                            .add(h(5).add(I18N.topSubtitleHome())))
                    .add(div().css(res().style().cyanShadowSection() + " " + res().style().mv100())
                            .add(div().style("max-width: 900px; margin: auto;").add(h(2).add(I18N.hi()).style("margin: 30px;"))
                            .add(p().style("padding-bottom: 40px;").add(I18N.mainTextHome()))))
                    .add(div().style("max-width: 900px; margin: auto;").css(res().style().containerSkillsHome())
                            .add(div()
                                    .add(img("/img/icons/computer-screen.svg"))
                                    .add(h(4).add(I18N.frontEnd()))
                                    .add(Widgets.element(new HTML(I18N.skillFrontHome())))
                                    .add(h(4).add("Dev. tools & libraries"))
                                    .add(ul()
                                            .add(li().add("GWT"))
                                            .add(li().add("Angular"))
                                            .add(li().add("Babylon.js"))
                                            .add(li().add("HTML5"))
                                            .add(li().add("CSS3"))
                                            .add(li().add("TypeScript")))
                            )
                            .add(div()
                                    .add(img("/img/icons/server-storage.svg"))
                                    .add(h(4).add(I18N.backEnd()))
                                    .add(Widgets.element(new HTML(I18N.skillBackHome())))
                                    .add(h(4).add("Dev. tools & libraries"))
                                    .add(ul()
                                            .add(li().add("MySQL"))
                                            .add(li().add("MongoDb"))
                                            .add(li().add("Firebase"))
                                            .add(li().add("AutoRest"))
                                            .add(li().add("Express.js"))
                                            .add(li().add("Spring Boot")))
                            )
                            .add(div()
                                    .add(img("/img/icons/cloud.svg"))
                                    .add(h(4).add(I18N.integration()))
                                    .add(Widgets.element(new HTML(I18N.skillIntegrationHome())))
                                    .add(h(4).add("Tools & Soft."))
                                    .add(ul()
                                            .add(li().add("Intellij"))
                                            .add(li().add("VSCode"))
                                            .add(li().add("Github"))
                                            .add(li().add("Bitbucket"))
                                            .add(li().add("Jira"))
                                            .add(li().add("Confluence"))
                                            .add(li().add("Jenkins"))
                                            .add(li().add("Terminal")))
                            )
                            .add(div()
                                    .add(img("/img/icons/television.svg"))
                                    .add(h(4).add(I18N.broadcast()))
                                    .add(Widgets.element(new HTML(I18N.skillBroadcastHome())))
                                    .add(h(4).add("Soft. tools"))
                                    .add(ul()
                                            .add(li().add("Vizrt Certificate Pro."))
                                            .add(li().add("Autodesk Maya"))
                                            .add(li().add("Autodesk 3D Studio Max"))
                                            .add(li().add("Blender"))
                                            .add(li().add("After Effects"))
                                            .add(li().add("The Foundry Nuke")))
                            )
                            .add(div()
                                    .add(img("/img/icons/brush.svg"))
                                    .add(h(4).add(I18N.designArt()))
                                    .add(Widgets.element(new HTML(I18N.skillDesignHome())))
                                    .add(h(4).add("Soft. tools"))
                                    .add(ul()
                                            .add(li().add("Photoshop"))
                                            .add(li().add("Illustrator"))
                                            .add(li().add("Sketch"))
                                            .add(li().add("Invision"))
                                            .add(li().add("Zeplin"))
                                            .add(li().add("Pen & Paper")))
                            )
                    ).element();

            container.add(Widgets.widget(contentPresenter));
            initWidget(container);
        }
    }
}
