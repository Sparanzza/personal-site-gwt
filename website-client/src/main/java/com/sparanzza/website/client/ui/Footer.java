package com.sparanzza.website.client.ui;

import com.google.gwt.user.client.ui.Composite;
import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.Widgets;

import static com.sparanzza.website.client.i18n.WebsiteConstants.I18N;
import static com.sparanzza.website.client.resources.Resources.res;
import static org.jboss.gwt.elemento.core.Elements.*;

public class Footer extends Composite {

    public Footer() {

        HTMLElement container = footer().css(res().style().cyanShadowSection()).style("height: 500px; margin-top:100px")
                .add(div().style("top: 50px; position: relative; color: #fff;")
                        .add(div().add(I18N.nothingVentured()).css(res().style().footerSign()))
                        .add(div().css(res().style().footerSocialLink())
                                .add(a("https://www.twitter.com/sparanzza", "_blank").add(i().css("fa fa-twitter")))
                                .add(a("https://www.linkedin.com/in/sparanzza", "_blank").add(i().css("fab fa-linkedin-in")))
                                .add(a("https://www.github.com/sparanzza", "_blank").add(i().css("fa fa-github")))
                                .add(a("mailto:aurelio@sparanzza.com", "_blank").add(i().css("far fa-envelope"))))
                        .add(div().css(res().style().madeWith())
                                .add(span().add(I18N.handcrafted()).css())
                                .add(span().add(i().css("far fa-copyright").style("margin: 0px 3px;")))
                                .add(span().add("Sparanzza").style("font-weight: 700;"))
                                .add(div().style("margin-top: 20px;")
                                        .add(div().add("/ * " + I18N.madeWith() + " * /").style("font-weight: 700;"))
                                        .add(div().css(res().style().footerLink())
                                                .add(a("http://www.gwtproject.org/", "_blank")
                                                        .add("GWTproject.com").css())
                                                .add(a("http://github.com/intendia-oss", "_blank")
                                                        .add("Reactivity - Autorest - Rxjava-GWT").css())
                                                .add(a("https://github.com/tbroyer/gwt-maven-archetypes", "_blank")
                                                        .add("gwt-maven-archetypes").css())
                                                .add(a("https://github.com/Sparanzza/personal-site", "_blank")
                                                        .add("sparanzza.com repository").css()))
                                )))
                .element();
        initWidget(Widgets.widget(container));

    }

}
