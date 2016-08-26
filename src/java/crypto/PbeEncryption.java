package crypto;

import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Khepry Quixote <fracking.analysis@gmail.com>
 */
public class PbeEncryption implements Serializable {
    
    Cipher dCipher = null;
    Cipher eCipher = null;
    
     /** 
     * Minimum length for a decent password 
     */  
    public final int MIN_KEY_LENGTH = 10;  
   
    /** 
     * The random number generator. 
     */  
    protected java.util.Random r = new java.util.Random();  
   
    /* Set of characters that is valid. Must be printable, memorable, 
     * and "won't break HTML" (i.e., not '<', '>', '&', '=', ...). 
     * or break shell commands (i.e., not '<', '>', '$', '!', ...). 
     * I, L and O are good to leave out, as are numeric zero and one. 
     */  
    protected static char[] goodChar = {  
        // Comment out next two lines to make upper-case-only, then  
        // use String toUpper() on the user's input before validating.  
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',  
        'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',  
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N',  
        'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',  
        '2', '3', '4', '5', '6', '7', '8', '9',  
        '+', '-', '@',  
    };  
    
    public PbeEncryption() {
        
    }

    public PbeEncryption(String key, Integer iterations, String saltString) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {

        PBEKeySpec pbeKeySpec;
        PBEParameterSpec pbeParamSpec;
        SecretKeyFactory secretKeyFactory;

        byte[] salt = "itbesalt".getBytes();
        
        // Salt
        if (saltString != null) {
            salt = saltString.getBytes();
        }

        // Iteration count
        if (iterations == null) {
            iterations = 23456;
        }

        // Create PBE parameter set
        pbeParamSpec = new PBEParameterSpec(salt, iterations);

        // if so needed, generate a key
        if (key.equals("")) {
            key = getNewKey();
        }
        
        // convert user password into a SecretKey object,
        // using a PBE key factory.
        pbeKeySpec = new PBEKeySpec(key.toCharArray());
        secretKeyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);

        // Create and initialize PBE EnCipher with key and parameters
        eCipher = Cipher.getInstance("PBEWithMD5AndDES");
        eCipher.init(Cipher.ENCRYPT_MODE, secretKey, pbeParamSpec);

        // Create and initialize PBE DeCipher with key and parameters
        dCipher = Cipher.getInstance("PBEWithMD5AndDES");
        dCipher.init(Cipher.DECRYPT_MODE, secretKey, pbeParamSpec);
    }
   
    /* Generate a Password object with a random password. */  
    public String getNewKey() {  
        return getNewKey(MIN_KEY_LENGTH);  
    }  
   
    /* Generate a Password object with a random password. */  
    public String getNewKey(int length) {  
        if (length < 1) {  
            throw new IllegalArgumentException(  
                    "Ridiculous password length " + length);  
        }  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < length; i++) {  
            sb.append(goodChar[r.nextInt(goodChar.length)]);  
        }  
        return sb.toString();  
    }  
    
    public String encrypt(String clearText) throws IllegalBlockSizeException, BadPaddingException {
        return new String(Base64.encodeBase64(eCipher.doFinal(clearText.getBytes())));
    }

    public String decrypt(String base64CryptText) throws IllegalBlockSizeException, BadPaddingException {
        return new String(dCipher.doFinal(Base64.decodeBase64(base64CryptText.getBytes())));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
            throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        
        String key = "abcdefgh";
        PbeEncryption pbeEncryption = new PbeEncryption(key, 23456, "bluesalt");

        String inputText = "This is an example of PBE encryption with Base64 enclosure.";

        String base64CryptText = pbeEncryption.encrypt(inputText);
        String clearText = pbeEncryption.decrypt(base64CryptText);

        System.out.println("CryptKey: " + key);
        System.out.println("InputText: " + inputText);
        System.out.println("Base64CryptText: " + base64CryptText);
        System.out.println("ClearText: " + clearText);

    }
}
