package com.pw.core.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.pw.core.enums.crypto.PwCrypto;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

public class CryptoUtil {
    public static String md5(String source){
        return MD5.create().digestHex(source);
    }

    public static String b64Encode(String source){
        return Base64.encode(source);
    }

    public static String b64Decode(String source){
        return Base64.decodeStr(source);
    }

    public static String b64(String source, PwCrypto crypto){
        if(crypto == PwCrypto.DECODE){
            return b64Decode(source);
        }
        if(crypto == PwCrypto.ENCODE){
            return b64Encode(source);
        }
        return "";
    }

    public static String aesEncode(String source, String key){
        byte[] keys = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), key.getBytes()).getEncoded();
        return SecureUtil.aes(keys).encryptBase64(source);
    }

    public static String aesDecode(String source, String key){
        byte[] keys = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), key.getBytes()).getEncoded();
        return SecureUtil.aes(keys).decryptStr(source);
    }

    public static String aes(String source, String key, PwCrypto crypto){
        if(crypto == PwCrypto.DECODE){
            return aesDecode(source, key);
        }
        if(crypto == PwCrypto.ENCODE){
            return aesEncode(source, key);
        }
        return "";
    }

    public static String desEncode(String source, String key){
        byte[] keys = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue(), key.getBytes()).getEncoded();
        return SecureUtil.des(keys).encryptBase64(source);
    }

    public static String desDecode(String source, String key){
        byte[] keys = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue(), key.getBytes()).getEncoded();
        return SecureUtil.des(keys).decryptStr(source);
    }

    public static String des(String source, String key, PwCrypto crypto){
        if(crypto == PwCrypto.DECODE){
            return desDecode(source, key);
        }
        if(crypto == PwCrypto.ENCODE){
            return desEncode(source, key);
        }
        return "";
    }

    public static String rsaEncode(String source, String publicKey){
        return new RSA(null, publicKey).encryptBase64(source, KeyType.PublicKey);
    }

    public static String rsaDecode(String data, String privateKey){
        return new RSA(privateKey, null).decryptStr(data, KeyType.PrivateKey);
    }

    public static String rsa(String source, String key, PwCrypto crypto){
        if(crypto == PwCrypto.DECODE){
            return rsaDecode(source, key);
        }
        if(crypto == PwCrypto.ENCODE){
            return rsaEncode(source, key);
        }
        return "";
    }

    public static Map<String, String> getKeyPair(){
        KeyPair keyPair = SecureUtil.generateKeyPair(AsymmetricAlgorithm.RSA.getValue());
        Map<String, String> keyPairMap = new HashMap<>();
        keyPairMap.put("publicKey", Base64.encode(keyPair.getPublic().getEncoded()));
        keyPairMap.put("privateKey", Base64.encode(keyPair.getPrivate().getEncoded()));
        return keyPairMap;
    }

    public static String sha256(byte[] source){
        return DigestUtil.sha256Hex(source);
    }

    public static String sha256(String source){
        return DigestUtil.sha256Hex(source);
    }

}
