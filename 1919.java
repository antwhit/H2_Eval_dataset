import java.util.*;

class HTTPResourceLoader {

    private String resourcePath;

    private HTTPResource resource;

    HTTPResourceLoader() {
    }

    HTTPResourceLoader(String address) {
        load(address);
    }

    void load(int errcode) {
        load(HTTPErrorPage.getPath(errcode));
    }

    void load(String address) {
        resourcePath = filter(address);
        if (resourcePath.endsWith("/")) {
            resourcePath = resourcePath.concat(dlhttpd.getDefaultFile());
            System.out.println(resourcePath);
        }
        resourcePath = dlhttpd.getDocRoot().concat(resourcePath);
        System.out.println("Resource: " + resourcePath);
        try {
            resource = new HTTPFileResource(resourcePath);
        } catch (HTTPErrorException e) {
            System.out.println(e.getErrorNumber());
            try {
                resource = new HTTPErrorResource(e.getErrorNumber());
            } catch (HTTPErrorException e2) {
                System.out.println("Fatal HTTPErrorResource Exception");
            }
        }
    }

    HTTPResource getResource() {
        return resource;
    }

    /**
	 * This function filters the requested file by removing strings
	 * like "../something" from it. This protects agains ".." attacks.
	 *
	 * @param address The file requested.
	 *
	 * @return The sanitized file address.
	 */
    private String filter(String address) {
        int i = address.indexOf("..");
        while (i > 0) {
            address = address.substring(0, i);
            i = address.indexOf("..");
        }
        return parse(address);
    }

    private static String parse(String s) {
        String temp = "";
        StringTokenizer tokens = new StringTokenizer(s, "?");
        s = tokens.nextToken();
        int i = 0, j;
        j = s.indexOf("%20");
        if (j == -1) return s; else {
            while ((j = s.indexOf("%20", i)) > -1) {
                temp = temp + s.substring(i, j) + ' ';
                i = j + 3;
            }
            temp = temp + s.substring(i);
            return temp;
        }
    }
}
