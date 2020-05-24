package com.sparanzza.website.client;

public enum NameTokens {
    HOMEPAGE("home", "Inicio"),
    LASTSTEPSPAGE("last-steps", "Ultimos pasos"),
    CURRICULUMVITAEPAGE("curriculum-vitae", "Curriculum vitae"),
    SKILLSPAGES("skills", "Perfiles"),
    SAYHOELLOPAGE("say-hello", "Saluda"),
    ERRORPAGE("", "error");

    private final String path;
    private final String title;

    NameTokens(String path, String title) {
        this.path = path;
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }
}