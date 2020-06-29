package pos.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pos.dto.LoginDTO;

public class JsonUtil {

    private static final Gson GSON = new GsonBuilder().create();

    public static String toJson(LoginDTO dto) {
        return GSON.toJson(dto);
    }
}
