package com.in28minutes.rest.webservices.restfulwebservices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public static final Contact DEFAULT_CONTACT = new Contact(
			"Irfan Khan", "www.deliverylelo.com", "irfankhan457@gmail.com");
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
			"My Awesome Api", "My Awesome Api Documentation", "1.0", "urn:tos",
	          DEFAULT_CONTACT, "Apache 2.0", 
	          "http://www.apache.org/licenses/LICENSE-2.0", 
	          new ArrayList<VendorExtension>());


	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
		.apiInfo(DEFAULT_API_INFO);
	}
	
	@Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

    }
}
