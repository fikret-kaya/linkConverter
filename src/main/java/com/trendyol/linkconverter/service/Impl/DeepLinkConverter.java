package com.trendyol.linkconverter.service.Impl;

import com.trendyol.linkconverter.service.Converter;

public class DeepLinkConverter implements Converter {
    private String convertedLink;
    private final String CONVERSION_TYPE = "web-to-deep";
    private String pageType;

    @Override
    public void convertLink(String link) {
        convertedLink = "ty://?Page=Home"; // default is empty home page
        pageType = "other";

        if (link.startsWith("https://www.trendyol.com/sr?q=")) { // check is search link or not
            convertedLink = "ty://?Page=Search&Query=" +
                    link.replace("https://www.trendyol.com/sr?q=", "");
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
        if (link.startsWith("https://www.trendyol.com") &&
                link.contains("-p-")) {
            link.replace("https://www.trendyol.com", ""); // remove base
            link = link.substring(link.indexOf("-p-")+3);   // remove before -g- including -g-

            if (!link.contains("?")) {
                return "ty://?Page=Product&ContentId=" + link;
            } else {
                String newLink;
                String[] splitArrForProductId = link.split("\\?");
                if (splitArrForProductId.length == 2) {
                    newLink = "ty://?Page=Product&ContentId=" + splitArrForProductId[0];

                    String[] arrOfLink = splitArrForProductId[1].split("\\&");
                    for (String str : arrOfLink) {
                        if (str.contains("boutiqueId=")) {
                            newLink += "&CampaignId=" + str.replace("boutiqueId=", "");
                        } else if (str.contains("merchantId=")) {
                            newLink += "&MerchantId=" + str.replace("merchantId=", "");
                        }
                    }
                    return newLink;
                }
            }
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
