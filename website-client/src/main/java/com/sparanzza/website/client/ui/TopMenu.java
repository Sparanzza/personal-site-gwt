package com.sparanzza.website.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.sparanzza.website.client.NameTokens;
import elemental2.dom.HTMLDivElement;
import org.jboss.gwt.elemento.core.Widgets;
import org.jboss.gwt.elemento.core.builder.HtmlContentBuilder;

import java.util.Arrays;

import static com.sparanzza.website.client.resources.Resources.res;
import static org.jboss.gwt.elemento.core.Elements.a;
import static org.jboss.gwt.elemento.core.Elements.div;

public class TopMenu extends Composite {

    public TopMenu() {

        HtmlContentBuilder<HTMLDivElement> navBar = div().css(res().style().topnav());
        Arrays.asList(NameTokens.values()).stream().filter(t -> t.getPath() != "").forEach(t -> navBar.add(a("#" + t.getPath()).add(t.getTitle())));
        initWidget(Widgets.widget(navBar));

    }

}
