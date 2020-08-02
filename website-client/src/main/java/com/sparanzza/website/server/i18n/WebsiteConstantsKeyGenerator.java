package com.sparanzza.website.server.i18n;

import com.google.gwt.i18n.server.KeyGenerator;
import com.google.gwt.i18n.server.Message;
import com.google.gwt.i18n.server.keygen.FullyQualifiedMethodNameKeyGenerator;

public class WebsiteConstantsKeyGenerator implements KeyGenerator {
    private final FullyQualifiedMethodNameKeyGenerator fullyQualifiedMethodNameKeyGenerator = new FullyQualifiedMethodNameKeyGenerator();

    @Override
    public String generateKey(Message msg) {
        String k = fullyQualifiedMethodNameKeyGenerator.generateKey(msg);
        k = k.replaceAll("\\.sparanzza\\.shared\\.i18n", "sparanzza.client.i18n");
        return k;
    }

}

