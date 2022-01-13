package com.trendyol.linkconverter.service;

import com.trendyol.linkconverter.service.Impl.DeepLinkConverter;
import com.trendyol.linkconverter.service.Impl.WebUrlConverter;

public class ConverterFactory {

    public Converter getConverter(String converterType) {
        if (converterType == null) {
            return null;
        } else if (converterType.equalsIgnoreCase("toDeepLink")) {
            return new DeepLinkConverter();
        } else if (converterType.equalsIgnoreCase("toWebUrl")) {
            return new WebUrlConverter();
        } else {
            return null;
        }

    }
}
