package com.trendyol.linkconverter.servicelayer;

import com.trendyol.linkconverter.dto.LinkDto;
import com.trendyol.linkconverter.repository.LinkConverterRepository;
import com.trendyol.linkconverter.service.Impl.LinkConverterServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
public class LinkConverterServiceWebToDeepTest {

    @Autowired
    @InjectMocks
    private LinkConverterServiceImpl service;

    @Mock
    private LinkConverterRepository repository;

    @Test
    public void convertDeepLinkToUrlWithAllIds() {
        // given
        LinkDto linkDto = new LinkDto("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064");

        // when
        LinkDto convertedLink =service.deepLinkToWebUrl(linkDto);

        // then
        then(convertedLink).isNotNull();
        then(convertedLink.getLink()).isEqualTo("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064");
    }

    @Test
    public void convertDeepLinkToUrlProductIdOnly() {
        // given
        LinkDto linkDto = new LinkDto("ty://?Page=Product&ContentId=1925865");

        // when
        LinkDto convertedLink =service.deepLinkToWebUrl(linkDto);

        // then
        then(convertedLink).isNotNull();
        then(convertedLink.getLink()).isEqualTo("https://www.trendyol.com/brand/name-p-1925865");
    }

    @Test
    public void convertDeepLinkToUrlProductIdAndBoutiqueId() {
        // given
        LinkDto linkDto = new LinkDto("ty://?Page=Product&ContentId=1925865&CampaignId=439892");

        // when
        LinkDto convertedLink =service.deepLinkToWebUrl(linkDto);

        // then
        then(convertedLink).isNotNull();
        then(convertedLink.getLink()).isEqualTo("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892");
    }

    @Test
    public void convertDeepLinkToUrlProductIdAndMerchantId() {
        // given
        LinkDto linkDto = new LinkDto("ty://?Page=Product&ContentId=1925865&MerchantId=105064");

        // when
        LinkDto convertedLink =service.deepLinkToWebUrl(linkDto);

        // then
        then(convertedLink).isNotNull();
        then(convertedLink.getLink()).isEqualTo("https://www.trendyol.com/brand/name-p-1925865?merchantId=105064");
    }

    @Test
    public void convertDeepLinkToUrlSimpleSearch() {
        // given
        LinkDto linkDto = new LinkDto("ty://?Page=Search&Query=elbise");

        // when
        LinkDto convertedLink =service.deepLinkToWebUrl(linkDto);

        // then
        then(convertedLink).isNotNull();
        then(convertedLink.getLink()).isEqualTo("https://www.trendyol.com/sr?q=elbise");
    }

    @Test
    public void convertDeepLinkToUrlDetailedSearch() {
        // given
        LinkDto linkDto = new LinkDto("ty://?Page=Search&Query=%C3%BCt%C3%BC");

        // when
        LinkDto convertedLink =service.deepLinkToWebUrl(linkDto);

        // then
        then(convertedLink).isNotNull();
        then(convertedLink.getLink()).isEqualTo("https://www.trendyol.com/sr?q=%C3%BCt%C3%BC");
    }

    @Test
    public void convertDeepLinkToUrlHomePageFavoriler() {
        // given
        LinkDto linkDto = new LinkDto("ty://?Page=Favorites");

        // when
        LinkDto convertedLink =service.deepLinkToWebUrl(linkDto);

        // then
        then(convertedLink).isNotNull();
        then(convertedLink.getLink()).isEqualTo("https://www.trendyol.com");
    }

    @Test
    public void convertDeepLinkToUrlHomePageSiparislerim() {
        // given
        LinkDto linkDto = new LinkDto("ty://?Page=Orders");

        // when
        LinkDto convertedLink =service.deepLinkToWebUrl(linkDto);

        // then
        then(convertedLink).isNotNull();
        then(convertedLink.getLink()).isEqualTo("https://www.trendyol.com");
    }

    @Test
    public void convertDeepLinkToUrlHomePageEmptyLink() {
        // given
        LinkDto linkDto = new LinkDto("");

        // when
        LinkDto convertedLink =service.deepLinkToWebUrl(linkDto);

        // then
        then(convertedLink).isNotNull();
        then(convertedLink.getLink()).isEqualTo("https://www.trendyol.com");
    }

    @Test
    public void convertDeepLinkToUrlHomePageBrokenLink() {
        // given
        LinkDto linkDto = new LinkDto("ty: /?Page= Query%C3%BCt%C3%BC");

        // when
        LinkDto convertedLink =service.deepLinkToWebUrl(linkDto);

        // then
        then(convertedLink).isNotNull();
        then(convertedLink.getLink()).isEqualTo("https://www.trendyol.com");
    }
}
