package crypto;

import java.io.*;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Khepry Quixote <fracking.analysis@gmail.com>
 */
public class AesEncryption implements Serializable {

    Cipher ecipher;
    Cipher dcipher;

    public AesEncryption(SecretKey key)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        // Create an 8-byte initialization vector 
        byte[] iv = new byte[]{
            0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f
        };

        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
        ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // CBC requires an initialization vector 
        ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
        dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
    }
    // Buffer used to transport the bytes from one stream to another 
    byte[] buf = new byte[1024];

    public void encrypt(InputStream in, OutputStream out)
            throws IOException {
        // Bytes written to out will be encrypted 
        out = new CipherOutputStream(out, ecipher);

        // Read in the cleartext bytes and write to out to encrypt 
        int numRead = 0;
        while ((numRead = in.read(buf)) >= 0) {
            out.write(buf, 0, numRead);
        }
        out.close();
        buf = new byte[1024];
    }

    public void decrypt(InputStream in, OutputStream out)
            throws IOException {
        // Bytes read from in will be decrypted 
        in = new CipherInputStream(in, dcipher);

        // Read in the decrypted bytes and write the cleartext to out 
        int numRead = 0;
        while ((numRead = in.read(buf)) >= 0) {
            out.write(buf, 0, numRead);
        }
        out.close();
        buf = new byte[1024];
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, FileNotFoundException, IOException {
        // Generate a temporary key. In practice, you would save this key. 
        // See also e464 Encrypting with DES Using a Pass Phrase. 
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        // the "strong" key size would be 128-bit
        // however, 192 and 256-bit key sizes are possible
        // when the "local.policy.jar" and "US_export_policy.jar" files
        // found in the <java-home>/lib/security folders were replaced
        // with the same-named "unlimited" strength ones downloaded from
        // Sun's site at https://cds.sun.com/is-bin/INTERSHOP.enfinity/WFS/CDS-CDS_Developer-Site/en_US/-/USD/ViewProductDetail-Start?ProductRef=jce_policy-6-oth-JPR@CDS-CDS_Developer
        kgen.init(128);
        SecretKey key = kgen.generateKey();

        // Create encrypter/decrypter class 
        AesEncryption encrypter = new AesEncryption(key);

        File aesTestFile = new File("AesTest");
        if (!aesTestFile.exists()) {
            aesTestFile.mkdir();
        }

        String inputText = "This here is an AES test encryption/decryption of a string, y'all!";

        File inputTextFile = new File("AesTest/AesTestInput.txt");
        if (!inputTextFile.exists()) {
            PrintWriter pw = new PrintWriter(inputTextFile);
            pw.write(inputText);
            pw.close();
        }

        File encryptedFile = new File("AesTest/AesTestCrypt.txt");
        File clearTextFile = new File("AesTest/AesTestClear.txt");

        FileInputStream fileInputStream = null;

        byte[] inputTextBytes = null;

        inputTextBytes = inputText.getBytes(Charset.forName("UTF8"));

        // to test the encryption/decryption of a string,
        // comment out the four lines of source code below
        // and the program will use the "inputText" string
        // above instead of the specified "inputTextFile"
        inputTextBytes = new byte[(int) inputTextFile.length()];
        fileInputStream = new FileInputStream(inputTextFile);
        fileInputStream.read(inputTextBytes);
        fileInputStream.close();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inputTextBytes);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Encrypt the ByteArrayInputStream via AES into the ByteArrayOutputStream
        encrypter.encrypt(byteArrayInputStream, byteArrayOutputStream);

        // Base64 encode the ByteArrayOutputStream so that it can be output to a file
        byte[] base64EncodedBytes = Base64.encodeBase64(byteArrayOutputStream.toByteArray());
        FileOutputStream fos = new FileOutputStream(encryptedFile);
        fos.write(base64EncodedBytes);
        fos.close();

        // Read the AES and Base64 encoded file into a byte array
        base64EncodedBytes = new byte[(int) encryptedFile.length()];
        fileInputStream = new FileInputStream(encryptedFile);
        fileInputStream.read(base64EncodedBytes);
        fileInputStream.close();

        // Base64 decode the byte array
        byte[] base64DecodedBytes = Base64.decodeBase64(base64EncodedBytes);
        // clear the encoded bytes array
        // so as to leave nothing in memory
        // to serve as a clue for hackers
        base64EncodedBytes = new byte[]{};

        // Decrypt the byte array via AES into a clear text file
        encrypter.decrypt(new ByteArrayInputStream(base64DecodedBytes), new FileOutputStream(clearTextFile));
        // clear the byte array
        // so as to leave nothing in memory
        // to serve as a clue for hackers
        base64DecodedBytes = new byte[]{};
    }
}
