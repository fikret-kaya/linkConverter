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
public class LinkConverterServiceDeepToWebTest {

    @Autowired
    @InjectMocks
    private LinkConverterServiceImpl service;

    @Mock
    private LinkConverterRepository repository;

    @Test
    public void convertUrlToDeepLinkWithAllIds() {
        // given
        LinkDto linkDto = new LinkDto("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064");

        // when
        LinkDto convertedLink =service.webUrlToDeepLink(linkDto);

        // then
        then(convertedLink).isNotNull();
        then(convertedLink.getLink()).isEqualTo("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064");
    }

    @Test
    public void convertUrlToDeepLinkProductIdOnly() {
        // given
        LinkDto linkDto = new LinkDto("https://www.trendyol.com/casio/erkek-kol-saati-p-1925865");

        // when
        LinkDto convertedLink =service.webUrlToDeepLink(linkDto);

        // then
        then(convertedLink).isNotNull();
        then(convertedLink.getLink()).isEqualTo("ty://?Page=Product&ContentId=1925865");
    }

    @Test
    public void convertUrlToDeepLinkProductIdAndBoutiqueId() {
        // given
        LinkDto linkDto = new LinkDto("https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?boutiqueId=439892");

        // when
        LinkDto convertedLink =service.webUrlToDeepLink(linkDto);

        // then
        then(convertedLink).isNotNull();
        then(convertedLink.getLink()).isEqualTo("ty://?Page=Product&ContentId=1925865&CampaignId=439892");
    }

    @Test
    public void convertUrlToDeepLinkProductIdAndMerchantId() {
        // given
        LinkDto linkDto = new LinkDto("https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?merchantId=105064");

        // when
        LinkDto convertedLink =service.webUrlToDeepLink(linkDto);

        // then
        then(convertedLink).isNotNull();
        then(convertedLink.getLink()).isEqualTo("ty://?Page=Product&ContentId=1925865&MerchantId=105064");
    }

    @Test
    public void convertUrlToDeepLinkSimpleSearch() {
        // given
        LinkDto linkDto = new LinkDto("https://www.trendyol.com/sr?q=elbise");

        // when
        LinkDto convertedLink =service.webUrlToDeepLink(linkDto);

        // then
        then(convertedLink).isNotNull();
        then(convertedLink.getLink()).isEqualTo("ty://?Page=Search&Query=elbise");
    }

    @Test
    public void convertUrlToDeepLinkDetailedSearch() {
        // given
        LinkDto linkDto = new LinkDto("https://www.trendyol.com/sr?q=%C3%BCt%C3%BC");

        // when
        LinkDto convertedLink =service.webUrlToDeepLink(linkDto);

        // then
        then(convertedLink).isNotNull();
        then(convertedLink.getLink()).isEqualTo("ty://?Page=Search&Query=%C3%BCt%C3%BC");
    }

    @Test
    public void convertUrlToDeepLinkHomePageFavoriler() {
        // given
        LinkDto linkDto = new LinkDto("https://www.trendyol.com/Hesabim/Favoriler");

        // when
        LinkDto convertedLink =service.webUrlToDeepLink(linkDto);

        // then
        then(convertedLink).isNotNull();
        then(convertedLink.getLink()).isEqualTo("ty://?Page=Home");
    }

    @Test
    public void convertUrlToDeepLinkHomePageSiparislerim() {
        // given
        LinkDto linkDto = new LinkDto("https://www.trendyol.com/Hesabim/#/Siparislerim");

        // when
        LinkDto convertedLink =service.webUrlToDeepLink(linkDto);

        // then
        then(convertedLink).isNotNull();
        then(convertedLink.getLink()).isEqualTo("ty://?Page=Home");
    }

    @Test
    public void convertUrlToDeepLinkHomePageEmptyLink() {
        // given
        LinkDto linkDto = new LinkDto("");

        // when
        LinkDto convertedLink =service.webUrlToDeepLink(linkDto);

        // then
        then(convertedLink).isNotNull();
        then(convertedLink.getLink()).isEqualTo("ty://?Page=Home");
    }

    @Test
    public void convertUrlToDeepLinkHomePageBrokenLink() {
        // given
        LinkDto linkDto = new LinkDto("ht ps://www.t ol.com/iparislerim");

        // when
        LinkDto convertedLink =service.webUrlToDeepLink(linkDto);

        // then
        then(convertedLink).isNotNull();
        then(convertedLink.getLink()).isEqualTo("ty://?Page=Home");
    }
}
