package com.trendyol.linkconverter.controllerlayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trendyol.linkconverter.controller.LinkConverterController;
import com.trendyol.linkconverter.dto.LinkDto;
import com.trendyol.linkconverter.service.Impl.LinkConverterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class LinkConverterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LinkConverterServiceImpl service;

    @InjectMocks
    private LinkConverterController controller;

    private LinkDto webUrl, deepLink;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        webUrl = new LinkDto("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064");
        deepLink = new LinkDto("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064");
    }

    @Test
    public void webUrlToDeepLink() throws Exception{
        when(service.webUrlToDeepLink(any())).thenReturn(deepLink);
        MvcResult mvcResult = mockMvc.perform(post("/urlToDeepLink").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(webUrl))).
                        andExpect(status().isOk()).andReturn();
        verify(service, times(1)).webUrlToDeepLink(any());

        then(mvcResult.getResponse().getContentAsString()).isEqualTo(asJsonString(deepLink));
    }

    @Test
    public void deepLinkToWebUrl() throws Exception{
        when(service.deepLinkToWebUrl(any())).thenReturn(webUrl);
        MvcResult mvcResult = mockMvc.perform(post("/deepLinkToUrl").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(deepLink))).
                        andExpect(status().isOk()).andReturn();
        verify(service, times(1)).deepLinkToWebUrl(any());

        then(mvcResult.getResponse().getContentAsString()).isEqualTo(asJsonString(webUrl));
    }

    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
