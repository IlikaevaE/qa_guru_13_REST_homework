package config;


import org.aeonbits.owner.Config;

public interface WebDriverConfig extends Config {
    @Key("baseURL")
    @DefaultValue("http://demowebshop.tricentis.com")
    String getBaseURL();
}
