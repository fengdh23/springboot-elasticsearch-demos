package springdata.elasticsearch.config;

import org.apache.http.ssl.TrustStrategy;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Host name does not match the certificate subject provided by the peer
 */
public class TrustAllStrategy implements TrustStrategy {
    @Override
    public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        return true;
    }
}
