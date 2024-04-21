package hello;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private final JdbcTemplate jdbcTemplate;

    public CustomerController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/customers")
    public ResponseEntity<Void> save(@RequestBody Customer customer) {
        jdbcTemplate.update(
                "INSERT INTO customers(first_name, last_name) VALUES (?,?)",
                customer.firstName(),
                customer.lastName()
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> list() {
        List<Customer> customers = jdbcTemplate.query(
                "select id, first_name, last_name from customers",
                (resultSet, rowNum) -> new Customer(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                ));
        return ResponseEntity.ok().body(customers);
    }
}
