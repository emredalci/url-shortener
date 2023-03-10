package com.example.unit.user;

import com.example.adapters.user.UserFakeDataAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserFakeDataAdapterTest {

    UserFakeDataAdapter userFakeDataAdapter;

    @BeforeEach
    void setUo(){
        userFakeDataAdapter = new UserFakeDataAdapter();
    }

    @Test
    void ShouldNot_ThrowException_WhenUserIdExist(){
        //GIVEN
        long id = 1;
        //WHEN
        //THEN
        Assertions.assertDoesNotThrow(() -> userFakeDataAdapter.isExistUser(id));
    }

}
