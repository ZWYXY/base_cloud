package ogr.spring.service;

public interface RedisService {

    void setString(String key, String value);

    String getString(String key);

}
