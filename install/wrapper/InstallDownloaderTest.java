import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.junit.Test;

import com.google.common.io.Files;


public class InstallDownloaderTest {

    @Test
    public void testHash() throws Exception {
        final File downloaded = downloadFile();
        InstallDownloader.verify(downloaded.getParentFile());
    }

    private File downloadFile() 
            throws Exception {
        
        final String str = "https://s3.amazonaws.com/lantern/latest.dmg";
        final URL url = new URL(str);
        final HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setConnectTimeout(200*1000);
        conn.setReadTimeout(200*1000);
        conn.setSSLSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());
        conn.setUseCaches(false);
        
        InputStream is = null;
        final File file = new File(Files.createTempDir(), "latest.exe");
        OutputStream os = new FileOutputStream(file);
        try {
            is = conn.getInputStream();
            int read = 0;
            byte[] bytes = new byte[1024];
     
            while ((read = is.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
        } catch (final IOException e) {
        } finally {
            if (is != null) {
                try {is.close();} catch (IOException e) {}
            }
            try {os.close();} catch (IOException e) {}
        }
        return file;
    }
}
