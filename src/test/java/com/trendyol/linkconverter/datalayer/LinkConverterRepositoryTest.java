package com.trendyol.linkconverter.datalayer;

import com.trendyol.linkconverter.entity.Link;
import com.trendyol.linkconverter.repository.LinkConverterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
public class LinkConverterRepositoryTest {

    @Autowired
    LinkConverterRepository repository;

    @Test
    public void saveLogToDb() {
        // given
        Link link = new Link();
        link.setWeb_link("https://www.trendyol.com/casio/erkek-kol-saati-p-1925865");
        link.setDeep_link("ty://?Page=Product&ContentId=1925865");
        link.setConversion_type("web-to-deep");
        link.setPage_type("product");

        // when
        Link savedLink = repository.save(link);

        // then
        then(savedLink.getId()).isNotNull();
        then(savedLink.getWeb_link()).isEqualTo(link.getWeb_link());
        then(savedLink.getWeb_link()).isEqualTo(link.getWeb_link());
        then(savedLink.getConversion_type()).isEqualTo(link.getConversion_type());
        then(savedLink.getPage_type()).isEqualTo(link.getPage_type());
    }
}
