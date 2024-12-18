package com.it.dashboard.util.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.MessageDigest;

public class PasswordEncryption 
{
	
	private static boolean checkPassword (String pass) throws Exception
    {
        String  hash;       // md5 hash from supplied password
        String  comp;       // md5 has from database
        
        // Get the hex md5 hash from file "pass.dat"
        // -----------------------------------------------------------------
        
        BufferedReader br = new BufferedReader(new FileReader("pass.dat"));
        comp = br.readLine();
        br.close();
        
        // Encrypt the given password to hex md5 hash
        // -----------------------------------------------------------------
        
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        StringBuffer  sb = new StringBuffer("");
                
        for ( byte b : md5.digest(pass.getBytes()) )
        {
            sb.append(String.format("%02X", b));
        }
        
        hash = sb.toString();
        
        // And finally compare the two for equality
        // -----------------------------------------------------------------
        
        return hash.equals(comp);
   }
    
    
    


	
	


}
