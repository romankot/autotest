package com.kremor.dataprovider;

import com.kremor.domainentities.User;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public final class DataProviders{
    private DataProviders() {
    }

    @DataProvider (name = "substitutionTestArray")
    public static Object[][] subsarray() throws Exception {
        return new Object[][] {};
    }

    @DataProvider (name = "DefaultUser")
    public static Object[][] credentials() throws IOException {
        return new Object[][] {{new User("your email ", "and password")}};
    }

}
