package com.example.api.bfpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.jdbc.support.DatabaseStartupValidator;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
public class DependsOnDatabaseStartupValidatorFactoryPostProcessor implements BeanFactoryPostProcessor {
  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    /*final String[] startups = beanFactory.getBeanNamesForType(DatabaseStartupValidator.class);
    if (startups.length != 0) {
      for (final String name : beanFactory.getBeanNamesForType(EntityManagerFactory.class)) {
        beanFactory.getBeanDefinition(name).setDependsOn(startups);
      }
    }*/
  }
}
