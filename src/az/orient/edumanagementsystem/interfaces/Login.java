package az.orient.edumanagementsystem.interfaces;

import java.io.IOException;

public interface Login {
    boolean login(String username, String password) throws IOException;
}