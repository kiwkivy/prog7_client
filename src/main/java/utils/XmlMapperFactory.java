package utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Класс, реализующий десериализацию xml.
 */
public class XmlMapperFactory {

    private static final XmlMapper INSTANCE = new XmlMapper();


    static {
        INSTANCE.findAndRegisterModules();
        INSTANCE.setVisibility(
                INSTANCE.getSerializationConfig()
                .getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
        );
    }

    /**
     * @return INSTANCE
     */
    public static XmlMapper getInstance() {
        return INSTANCE;
    }

}
