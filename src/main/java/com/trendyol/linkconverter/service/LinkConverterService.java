package com.trendyol.linkconverter.service;

import com.trendyol.linkconverter.dto.LinkDto;

public interface LinkConverterService {
    LinkDto webUrlToDeepLink(LinkDto linkDto);
    LinkDto deepLinkToWebUrl(LinkDto linkDto);
}
