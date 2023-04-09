package com.back.balbadak.resolver;

import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolver;

import java.io.IOException;

public class FilePathResolver extends PathResourceResolver implements ResourceResolver {
    @Override
    protected Resource getResource(String resourcePath, Resource location) throws IOException {
        System.out.println("HERE : " + resourcePath);
        String[] path = resourcePath.split("/");
        resourcePath = resourcePath.replaceAll("%20", " ");
        return super.getResource(resourcePath, location);
    }
}
