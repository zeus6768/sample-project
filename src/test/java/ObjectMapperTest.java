import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hello.Customer;

public class ObjectMapperTest {

    @Test
    void test() throws JsonProcessingException {
        String json = "{ \"firstName\" : \"Lizzy\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        Customer lizzy = objectMapper.readValue(json, Customer.class);
        System.out.println(lizzy);
    }
}
