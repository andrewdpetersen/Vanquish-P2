package Configs;

/**
 * DB Persistence Configuration
 *
 * @description This class contains all the session information for Hibernate to function
 * within Spring
 * @date 10/29/2021
 * @author Vanquish
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {
    // Hibernate Variables
    // @Value("")
}
