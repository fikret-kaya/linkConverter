package com.trendyol.linkconverter.controller;

import com.trendyol.linkconverter.dto.LinkDto;
import com.trendyol.linkconverter.service.LinkConverterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkConverterController {

    Logger logger = LoggerFactory.getLogger(LinkConverterController.class);

    @Autowired
    private LinkConverterService linkConverterService;

    @PostMapping("/urlToDeepLink")
    public ResponseEntity<LinkDto> webUrlToDeepLink(@RequestBody LinkDto requestLinkDto) {
        LinkDto responseLinkDto = linkConverterService.webUrlToDeepLink(requestLinkDto);

        logger.info("Web url converted to deep link: " + responseLinkDto.getLink());

        return new ResponseEntity<>(responseLinkDto, HttpStatus.OK);
    }

    @PostMapping("/deepLinkToUrl")
    public ResponseEntity<LinkDto> deepLinkToWebUrl(@RequestBody LinkDto requestLinkDto) {
        LinkDto responseLinkDto = linkConverterService.deepLinkToWebUrl(requestLinkDto);

        logger.info("Deep link converted to web url: " + responseLinkDto.getLink());

        return new ResponseEntity<>(responseLinkDto, HttpStatus.OK);
    }
}
