package com.fsaravia.utilities;



import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.StringTokenizer;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class Encriptar {

    public static String encriptarIrreversible(String textoplano) throws IllegalStateException {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1"); // Instancia de generador SHA-1
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage());
        }

//		try {
        md.update(textoplano.getBytes()); // Generación de resumen de mensaje
//		} catch(UnsupportedEncodingException e) {
//			throw new IllegalStateException(e.getMessage());
//		}
        byte[] raw = md.digest(); // Obtención del resumen de mensaje
        String hash = Encriptar.getString(raw);
//		String hash = (new BASE64Encoder()).encode(raw); // Traducción a BASE64
        return hash;
    }

    private static String getString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            sb.append((int) ( 0x00FF & b ));
            if (i + 1 < bytes.length) {
                sb.append("-");
            }
        }
        return sb.toString();
    }

    private static byte[] getBytes(String str) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        StringTokenizer st = new StringTokenizer(str, "-", false);
        while (st.hasMoreTokens()) {
            int i = Integer.parseInt(st.nextToken());
            bos.write((byte) i);
        }
        return bos.toByteArray();
    }
    //ALGORITMO REVERSIBLE
    
    private static byte[] SALT_BYTES = {(byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03};
    private static int ITERATION_COUNT = 19;
    private static String passPhrase = "unaclave";

    public static String encriptarReversible(/*String passPhrase, */String str) {
        Cipher ecipher = null;
        Cipher dcipher = null;
        try {
            // Crear la key
            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), SALT_BYTES, ITERATION_COUNT);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());

            // Preparar los parametros para los ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT_BYTES, ITERATION_COUNT);

            // Crear los ciphers
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } catch (javax.crypto.NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (java.security.InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        try {
            // Encodear la cadena a bytes usando utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encriptar
            byte[] enc = ecipher.doFinal(utf8);

            // Encodear bytes a base64 para obtener cadena
//            return new sun.misc.BASE64Encoder().encode(enc);
            return getString(enc);
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        }

        return null;
    }

    public static String desencriptar(/*String passPhrase, */String str) {
        Cipher ecipher = null;
        Cipher dcipher = null;

        try {
            // Crear la key
            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), SALT_BYTES, ITERATION_COUNT);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());

            // Preparar los parametros para los ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT_BYTES, ITERATION_COUNT);

            // Crear los ciphers
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } catch (javax.crypto.NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (java.security.InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        try {
            // Decodear base64 y obtener bytes
            //byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
            byte[] dec = getBytes(str);
            // Desencriptar
            byte[] utf8 = dcipher.doFinal(dec);

            // Decodear usando utf-8
            return new String(utf8, "UTF8");
        } catch (javax.crypto.BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}