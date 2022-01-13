package com.trendyol.linkconverter.service;

public interface Converter {
    void convertLink(String link);
    String getConvertedLink();
    String getConversionType();
    String getPageType();
}
