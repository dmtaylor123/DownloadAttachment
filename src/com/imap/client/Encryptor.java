package com.imap.client;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

public class Encryptor
{
       private Cipher encipher;
       private Cipher decipher;
       private String k = "ev5JIS80rg5gD0eI"; // This is the salt

       Encryptor() throws Exception
       {
           SecretKey key = new SecretKeySpec(k.getBytes(), "AES");
           encipher = Cipher.getInstance("AES/GCM/NoPadding");
           decipher = Cipher.getInstance("AES/GCM/NoPadding");
           encipher.init(Cipher.ENCRYPT_MODE, key);
           decipher.init(Cipher.DECRYPT_MODE, key);
       }
      
       public String encrypt(String str) throws Exception
       {
          // Encode the string into bytes using utf-8
           byte[] utf8 = str.getBytes("StandardCharsets.UTF_8");

           // Encrypt
           byte[] enc = encipher.doFinal(utf8);

          // Encode bytes to base64 to get a string
          return Base64.getMimeEncoder().encodeToString(enc);
       }
      
       public String decrypt(String str) throws Exception
       {
            // Decode base64 to get bytes
            byte[] dec = Base64.getMimeDecoder().decode(str);

            byte[] utf8 = decipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "StandardCharsets.UTF_8");
       }     
}

