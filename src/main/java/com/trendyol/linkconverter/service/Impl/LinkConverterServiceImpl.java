package com.trendyol.linkconverter.service.Impl;

import com.trendyol.linkconverter.dto.LinkDto;
import com.trendyol.linkconverter.entity.Link;
import com.trendyol.linkconverter.repository.LinkConverterRepository;
import com.trendyol.linkconverter.service.Converter;
import com.trendyol.linkconverter.service.ConverterFactory;
import com.trendyol.linkconverter.service.LinkConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LinkConverterServiceImpl implements LinkConverterService {

    private final LinkConverterRepository linkConverterRepository;
    private ConverterFactory converterFactory = new ConverterFactory();

    @Override
    public LinkDto webUrlToDeepLink(LinkDto linkDto) {
        Converter converter = converterFactory.getConverter("toDeepLink");
        converter.convertLink(linkDto.getLink());

        saveToDatabase(linkDto, converter);

        return new LinkDto(converter.getConvertedLink());
    }

    @Override
    public LinkDto deepLinkToWebUrl(LinkDto linkDto) {
        Converter converter = converterFactory.getConverter("toWebUrl");
        converter.convertLink(linkDto.getLink());

        saveToDatabase(linkDto, converter);

        return new LinkDto(converter.getConvertedLink());
    }

    private void saveToDatabase(LinkDto linkDto, Converter converter) {
        Link link = new Link();
        link.setWeb_link(linkDto.getLink());
        link.setDeep_link(converter.getConvertedLink());
        link.setConversion_type(converter.getConversionType());
        link.setPage_type(converter.getPageType());

        linkConverterRepository.save(link);
    }
}
