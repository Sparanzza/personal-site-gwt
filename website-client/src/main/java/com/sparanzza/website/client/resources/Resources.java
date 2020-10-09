package com.sparanzza.website.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;

public interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    static Resources res() {
        return INSTANCE;
    }

    static void inject() {
        INSTANCE.normalize().ensureInjected();
        INSTANCE.style().ensureInjected();
    }

    interface Normalize extends CssResource {
    }

    interface Style extends CssResource {
        String topnav();
        @ClassName("alertdialog") String alertDialog();
        @ClassName("alertdialog-closebtn") String alertDialogCloseBtn();
        @ClassName("underline-magical") String underlineMagical();
        @ClassName("footer-sign") String footerSign();
        @ClassName("footer-social-link") String footerSocialLink();
        @ClassName("footer-link") String footerLink();
        @ClassName("made-with") String madeWith();
        @ClassName("cyan-shadow-section") String cyanShadowSection();
        @ClassName("purple-shadow-section") String purpleShadowSection();
        @ClassName("container-skills-home") String containerSkillsHome();
        String mv100();
        String mv50();
        String mv20();
    }

    @Source("normalize.gss") @NotStrict Normalize normalize();
    @Source("style.gss") @NotStrict Style style();
}
