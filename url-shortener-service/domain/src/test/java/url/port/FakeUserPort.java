package url.port;

import com.example.common.exception.BusinessException;
import com.example.user.port.UserPort;

public class FakeUserPort implements UserPort {

    @Override
    public void isExistUser(long id) {
        if (id == 1){
            return;
        }
        throw new BusinessException("user.not.found.error");
    }
}
