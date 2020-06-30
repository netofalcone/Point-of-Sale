package pos.core.security;

import javax.servlet.http.HttpServletResponse;

public final class SecurityUtil {

    public static HttpServletResponse fillAccessControlHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, content-type, refresh-token, user");
        response.setHeader("Access-Control-Expose-Headers", "authorization, content-type, refresh-token, user");

        return response;
    }
}
