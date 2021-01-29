package com.sysacad.alkemy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class GeneratePassword {

public static void main(String[] args) {
		
		for (int i = 0; i < 2; i++) {
			System.out.println(passwordEncoder().encode("12345"));
		
		}
	
}
	
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
