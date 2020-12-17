import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class EopX509TrustManager implements X509TrustManager {
        EopX509TrustManager() {
           // 这里可以进行证书的初始化操作  
        }
        // 检查客户端的可信任状态  
        public void checkClientTrusted(X509Certificate chain[], String authType) throws CertificateException {
        }
        // 检查服务器的可信任状态  
        public void checkServerTrusted(X509Certificate chain[], String authType) throws CertificateException {
        }
        // 返回接受的发行商数组  
        public   X509Certificate[] getAcceptedIssuers(){
                     return null;
           }
        }

