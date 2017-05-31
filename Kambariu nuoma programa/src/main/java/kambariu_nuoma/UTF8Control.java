package kambariu_nuoma;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class UTF8Control extends ResourceBundle.Control {

    public List<String> getFormats(String basename) {

        if (basename == null) {
            throw new NullPointerException();
        }
        return Arrays.asList("properties");

    }

    @Override
    public ResourceBundle newBundle(String baseName, Locale locale, String format,
                                    ClassLoader loader, boolean reload) {

        if (baseName == null || locale == null || format == null || loader == null) {
            throw new NullPointerException();
        }

        ResourceBundle bundle = null;

        if (format.equals("properties")) {

            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, format);
            InputStream stream = null;

            try {
                if (reload) {
                    URL url = loader.getResource(resourceName);
                    if (url != null) {
                        URLConnection connection;
                        connection = url.openConnection();

                        if (connection != null) {
                            connection.setUseCaches(false);
                            stream = connection.getInputStream();
                        }
                    }
                } else {
                    stream = loader.getResourceAsStream(resourceName);
                }

                if (stream != null) {
                    InputStreamReader is = new InputStreamReader(stream, "UTF-8");
                    bundle = new PropertyResourceBundle(is);
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bundle;
    }
}