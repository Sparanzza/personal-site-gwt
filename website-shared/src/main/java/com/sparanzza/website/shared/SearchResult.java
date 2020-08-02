package com.sparanzza.website.shared;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class SearchResult {
    public String display_name; //ex: "Málaga, Provincia de Málaga, Andalusia, Spain",
    public String lat; //ex: "36.7210805",
    public String lon; //ex: "-4.4210409",
    public double importance; //ex: 0.73359836669253,
}