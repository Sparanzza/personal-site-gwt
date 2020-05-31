package com.sparanzza.website.client.ui;

import com.google.gwt.user.client.ui.Composite;
import elemental2.dom.HTMLDivElement;
import org.jboss.gwt.elemento.core.Widgets;
import org.jboss.gwt.elemento.core.builder.HtmlContentBuilder;

import static com.sparanzza.website.client.resources.Resources.res;
import static org.jboss.gwt.elemento.core.Elements.div;

public class Footer extends Composite {

    public Footer() {

        HtmlContentBuilder<HTMLDivElement> footer = div().css(res().style().topnav());
        initWidget(Widgets.widget(footer));

    }

}
