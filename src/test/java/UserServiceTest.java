import de.oerol.User;
import de.oerol.UserRepository;
import de.oerol.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void emailAlreadyRegistered() {
        String email = "idoexist@mail.com";
        User testUser = new User();

        when(userRepository.findByEmail(email)).thenReturn(testUser);
        boolean result = userService.isEmailAlreadyRegistered(email);
        verify(userRepository).findByEmail(email);

        assertTrue(result);
    }

    @Test
    public void emailNotRegistered() {
        String email = "idonotexist@mail.com";

        when(userRepository.findByEmail(email)).thenReturn(null);
        boolean result = userService.isEmailAlreadyRegistered(email);
        verify(userRepository).findByEmail(email);

        assertFalse(result);
    }
}
