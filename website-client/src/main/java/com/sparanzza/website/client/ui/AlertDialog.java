package com.sparanzza.website.client.ui;

import com.google.gwt.user.client.ui.Composite;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.Widgets;

import static com.sparanzza.website.client.resources.Resources.res;
import static org.jboss.gwt.elemento.core.Elements.*;
import static org.jboss.gwt.elemento.core.EventType.click;

public class AlertDialog extends Composite {

    private final HTMLElement closeBtn;
    private final HTMLDivElement alert;

    public AlertDialog(HTMLElement widget) {
        alert = div().css(res().style().alertDialog())
                .add(closeBtn = span().add(i().css("far fa-times-circle")).css(res().style().alertDialogCloseBtn())
                        .on(click, e -> close()).get())
                .add(span().add(widget))
                .get();
        initWidget(Widgets.widget(alert));
    }

    public void close() {
        alert.setAttribute("style", "display: none;");
    }
}
