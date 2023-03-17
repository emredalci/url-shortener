package com.example.adapters.user;

import com.example.user.exception.UserNotFoundException;
import com.example.user.port.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFakeDataAdapter implements UserPort {

    @Override
    public void isExistUser(long id) {
        //fake user service
        if (id == 1){
            return;
        }
        throw new UserNotFoundException("user.not.found.error");
    }
}
