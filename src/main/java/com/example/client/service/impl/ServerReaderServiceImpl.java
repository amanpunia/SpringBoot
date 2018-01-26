package com.example.client.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.springframework.stereotype.Service;

import com.example.client.service.ServerReaderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ServerReaderServiceImpl implements ServerReaderService {

	@Override
	@HystrixCommand(ignoreExceptions = { UnrecoverableKeyException.class, KeyManagementException.class,
			KeyStoreException.class, NoSuchAlgorithmException.class, CertificateException.class,
			IOException.class, RuntimeException.class }, fallbackMethod = "raiseRuntimeException")
	public String readFromServer() throws UnrecoverableKeyException, KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {
		InputStream keystoreInput = this.getClass().getResourceAsStream("/ssl-server.jks");
		InputStream truststoreInput = this.getClass().getResourceAsStream("/ssl-server.jks");
		setSSLFactories(keystoreInput, "changeit", truststoreInput);
		keystoreInput.close();
		truststoreInput.close();

		// System.setProperty("javax.net.ssl.trustStore","classspath:ssl-server.jks");//this
		// didn't work-hence the manual
		// settings of SSL Factories
		// System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
		String httpsURL = "https://localhost:8443/message";
		URL myUrl = new URL(httpsURL);
		HttpsURLConnection conn = (HttpsURLConnection) myUrl.openConnection();
		InputStream is = conn.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);

		String inputLine;
		String resp = "RESP:";

		while ((inputLine = br.readLine()) != null) {
			System.out.println(inputLine);
			resp += inputLine;
		}

		br.close();
		return resp;
	}

	private String raiseRuntimeException(Throwable e) {
		throw new RuntimeException(e.getMessage());
	}

	private void setSSLFactories(InputStream keyStream, String keyStorePassword, InputStream trustStream)
			throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException,
			UnrecoverableKeyException, KeyManagementException {
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		char[] keyPassword = keyStorePassword.toCharArray();
		keyStore.load(keyStream, keyPassword);

		KeyManagerFactory keyFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		keyFactory.init(keyStore, keyPassword);

		KeyManager[] keyManagers = keyFactory.getKeyManagers();

		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		char[] trustPassword = keyStorePassword.toCharArray();
		trustStore.load(trustStream, trustPassword);

		TrustManagerFactory trustFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		trustFactory.init(trustStore);

		TrustManager[] trustManagers = trustFactory.getTrustManagers();

		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(keyManagers, trustManagers, null);
		SSLContext.setDefault(sslContext);
	}

}
