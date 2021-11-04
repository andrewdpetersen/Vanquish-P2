import VanquishP2.Application.Beans.Service.ValidatorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.mockito.Mockito.mock;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CredentialValidatorsTest {
    ValidatorService mockValidator = mock(ValidatorService.class);

    @BeforeAll
    void setup(){
        mockValidator = new ValidatorService();
    }

    @Test
    public void emailChecker() {

    }

    @Test
    public void passwordChecker() {

    }

    @Test
    public void usernameChecker() {

    }
}
