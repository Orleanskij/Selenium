package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserUtils {

    public static final String USERS_PATH = "src/test/resources/users.json";
    private static List<User> users = new ArrayList<>();

    static {
        ObjectMapper mapper = new ObjectMapper();
        try {
            users = Arrays.asList(mapper.readValue(new File(USERS_PATH), User[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getUser(String userNumber) {
        return users.get(Integer.parseInt(userNumber));
    }
}
