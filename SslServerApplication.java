package com.snhu.sslserver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
@RestController
class ServerController {
	@RequestMapping("/hash")
	public String myHash() throws NoSuchAlgorithmException {
		String data = "Justin Swinney, This is my custom String!"; // Unique data string.
		MessageDigest msg = MessageDigest.getInstance("SHA-256"); //Object creation using MessageDigest Library.
		byte[] hashB = msg.digest(data.getBytes()); //hash value of data string.
		String checkSum = byteConversion(hashB); // using checkSum and byteConversion method to generate the hex string.
		return "<p>String Data: " + data + "<br> Cryptographic Hash Algorithm: " + msg + "<br>" + "CheckSum Value: " + checkSum + "</p>"; // desired output msg.

	}

	private String byteConversion(byte[] bytes) { //Converting byte array to hex.
		StringBuilder hexStr = new StringBuilder(2 * bytes.length);
		for (byte x : bytes) { // loop
			String hex = Integer.toHexString(0xff & x); // converting byte to hex (0-255).
			if (hex.length() == 1) {
				hexStr.append('0'); //add 0 if needed.
			}
			hexStr.append(hex); //appending hex string.
		}
		return hexStr.toString(); //returning value.
	}
}
