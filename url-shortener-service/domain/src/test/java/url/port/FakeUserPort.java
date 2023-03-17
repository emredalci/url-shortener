package url.port;

import com.example.user.exception.UserNotFoundException;
import com.example.user.port.UserPort;

public class FakeUserPort implements UserPort {

    @Override
    public void isExistUser(long id) {
        if (id == 1){
            return;
        }
        throw new UserNotFoundException("user.not.found.error");
    }
}
