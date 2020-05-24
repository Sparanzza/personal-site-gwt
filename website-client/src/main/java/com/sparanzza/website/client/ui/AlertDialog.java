package com.sparanzza.website.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.sparanzza.website.client.resources.Resources;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.Widgets;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.span;
import static org.jboss.gwt.elemento.core.EventType.click;

public class AlertDialog extends Composite {

    private final HTMLElement closeBtn;
    private final HTMLDivElement alert;

    public AlertDialog(String msg) {
        alert = div().css(Resources.res().style().alertDialog())
                .add(closeBtn = span().css(Resources.res().style().alertDialogCloseBtn())
                        .add("×").on(click, e -> close()).get())
                .add(span().css(Resources.res().style().alertDialogCloseBtn()).add(msg))
                .get();
        initWidget(Widgets.widget(alert));
    }

    public void close() {
        alert.setAttribute("style", "display: none;");
    }
}
