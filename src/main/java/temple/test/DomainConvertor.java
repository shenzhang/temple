package temple.test;

import org.springframework.core.convert.converter.Converter;

/**
 * User: shenzhang
 * Date: 9/8/14
 * Time: 11:19 PM
 */
public class DomainConvertor implements Converter<String, ConvertDomain> {
    @Override
    public ConvertDomain convert(String source) {
        ConvertDomain domain = new ConvertDomain();
        String[] values = source.split(",");
        domain.setFirstName(values[0]);
        domain.setLastName(values[1]);
        return domain;
    }
}
