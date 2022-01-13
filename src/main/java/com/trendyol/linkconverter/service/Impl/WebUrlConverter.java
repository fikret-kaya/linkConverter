package com.trendyol.linkconverter.service.Impl;

import com.trendyol.linkconverter.service.Converter;

public class WebUrlConverter implements Converter {
    private String convertedLink;
    private final String CONVERSION_TYPE = "deep-to-web";
    private String pageType;

    @Override
    public void convertLink(String link) {
        convertedLink = "https://www.trendyol.com"; // default is empty home page
        pageType = "other";

        if (link.startsWith("ty://?Page=Search&Query=")) { // check is search link or not
            convertedLink = "https://www.trendyol.com/sr?q=" +
                    link.replace("ty://?Page=Search&Query=", "");
            pageType = "search";
        } else {    // check is product link or not
            link = isProductPage(link);
            if (link != null) {
                convertedLink = link;
                pageType = "product";
            }
        }
    }

    public String isProductPage(String link) {
        if (link.startsWith("ty://?Page=Product&ContentId=")) {
            link = link.substring(29);
            String[] splitArr = link.split("\\&");

            if (splitArr.length >= 1) {
                link = "https://www.trendyol.com/brand/name-p-" + splitArr[0];

                for (int i = 1; i < splitArr.length; i++) {
                    String str = splitArr[i];
                    if (str.contains("CampaignId")) {
                        link += "?boutiqueId=" + str.replace("CampaignId=", "");
                    } else if (link.contains("boutiqueId=") && str.contains("MerchantId")) {
                        link += "&merchantId=" + str.replace("MerchantId=", "");
                    } else if (!link.contains("CampaignId=") && str.contains("MerchantId")) {
                        link += "?merchantId=" + str.replace("MerchantId=", "");
                    } else {
                        return null;
                    }
                }
            }
            return link;
        }
        return null;
    }

    @Override
    public String getConvertedLink() {
        return convertedLink;
    }

    @Override
    public String getConversionType() {
        return CONVERSION_TYPE;
    }

    @Override
    public String getPageType() {
        return pageType;
    }
}
