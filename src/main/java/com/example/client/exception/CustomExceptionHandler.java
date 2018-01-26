package com.example.client.exception;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeoutException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.netflix.hystrix.exception.HystrixRuntimeException;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler({ UnrecoverableKeyException.class, KeyManagementException.class, KeyStoreException.class,
			NoSuchAlgorithmException.class, CertificateException.class })
	public void handleSSLException(Exception ex) {
		System.out.println("SSL related exception occured:" + ex.getMessage());
	}

	@ExceptionHandler({ IOException.class })
	public void handleIOException(IOException ex) {
		System.out.println("IO exception occured:" + ex.getMessage());
	}

	@ExceptionHandler({ Exception.class })
	public void handleGenericException(Exception ex) {
		if (ex instanceof HystrixRuntimeException) {
			String message = ex.getCause() == null ? ex.getMessage()
					: (ex.getCause() instanceof TimeoutException) ? "TimeOut"
							: ex.getCause().getMessage() == null ? ex.getMessage() : ex.getCause().getMessage();

			System.out.println(message);
		} else
			System.out.println("Generic exception occured:" + ex.getMessage());
	}
}
