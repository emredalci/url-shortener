package url.port;

import user.port.UserPort;

public class FakeUserPort implements UserPort {

    @Override
    public void isExistUser(long id) {
        if (id == 1){
            return;
        }
        throw new RuntimeException("Error"); //TODO: Özelleştirilmiş exvception yazılacak
    }
}
