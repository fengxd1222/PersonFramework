package com.fengxudong.framework.init.scanner;

import com.fengxudong.framework.init.RoleMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.data.type.classreading.MethodsMetadataReaderFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 注解扫描注册器
 */
@Slf4j
@Configuration
public class RoleMappingScannerRegister implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    private ResourceLoader resourceLoader;

//    @Autowired
//    private

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(RoleMappingScan.class.getName()));

        assert annotationAttributes != null;
        String[] baseScanPackages = annotationAttributes.getStringArray("baseScanPackage");
        if (baseScanPackages.length == 0)
            throw new RuntimeException("@RoleMappingScan:baseScanPackage is empty,please check it");

        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
//        CachingMetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourceLoader);
        MethodsMetadataReaderFactory readerFactory = new MethodsMetadataReaderFactory(resourceLoader);

        Map<String, Object> urlPartternRoleMappings = new HashMap<>();
        for (String baseScanPackage : baseScanPackages) {
            baseScanPackage = baseScanPackage.contains("/**/*.class") ? baseScanPackage : baseScanPackage + "/**/*.class";
            try {
                Resource[] resources = resolver.getResources(new ClassPathResource(baseScanPackage).getPath());
                for (Resource resource : resources) {
                    MetadataReader metadataReader = readerFactory.getMetadataReader(resource);
                    handle(metadataReader, urlPartternRoleMappings, RoleMapping.class);
                }
            } catch (IOException e) {
                log.error("RoleMappingScannerRegisterError", e);
                break;
            }
        }
        //保存urlPartternMappings
        urlPartternRoleMappings.forEach((k,v)->{
            log.info("urlPartternRoleMappings : {}={}",k,v);
        });

    }

    private <T> void handle(MetadataReader metadataReader, Map<String, Object> urlPartternMappings, Class<T> target) {
        String canonicalName = target.getCanonicalName();

        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        Map<String, Object> requestMappingMap = annotationMetadata.getAnnotationAttributes(RequestMapping.class.getCanonicalName());
        if(requestMappingMap==null){
            return;
        }
        String[] values = (String[]) requestMappingMap.get("value");
        if(values.length<=0){
            return;
        }

        for (MethodMetadata next : annotationMetadata.getAnnotatedMethods(canonicalName)) {
            Map<String, Object> roleMappingDescriptionsMapping = next.getAnnotationAttributes(canonicalName);
            if(roleMappingDescriptionsMapping==null){
                continue;
            }
            Map<String, Object> requestMappingPathMapping = handlePathWithMethod(next);
            if(requestMappingPathMapping==null){
                continue;
            }
            String[] childPaths = (String[]) requestMappingPathMapping.get("value");
            for (String childPath : childPaths) {
                //spring也会扫描映射器，这里不再校验url重复
                for (String parentPath : values) {
                    urlPartternMappings.put(parentPath + childPath, roleMappingDescriptionsMapping.get("description"));
                }
            }
        }

    }

    private Map<String, Object> handlePathWithMethod(MethodMetadata next) {
        Map<String, Object> requestMappingPathMapping = next.getAnnotationAttributes(GetMapping.class.getCanonicalName());
        if (requestMappingPathMapping != null) return requestMappingPathMapping;

        requestMappingPathMapping = next.getAnnotationAttributes(PostMapping.class.getCanonicalName());
        if (requestMappingPathMapping != null) return requestMappingPathMapping;

        requestMappingPathMapping = next.getAnnotationAttributes(RequestMapping.class.getCanonicalName());
        if (requestMappingPathMapping != null) return requestMappingPathMapping;

        requestMappingPathMapping = next.getAnnotationAttributes(PutMapping.class.getCanonicalName());
        if (requestMappingPathMapping != null) return requestMappingPathMapping;

        requestMappingPathMapping = next.getAnnotationAttributes(DeleteMapping.class.getCanonicalName());
        return requestMappingPathMapping;
    }
}


























