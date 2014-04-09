package model.textparser;

import javax.crypto.SecretKey;

public class DESSecretKey implements SecretKey {

    private final byte[] key;

    public DESSecretKey(byte[] key) {
        this.key = key;
    }

    @Override
    public String getAlgorithm() {
        return "DES";
    }

    @Override
    public String getFormat() {
        return "RAW";
    }

    @Override
    public byte[] getEncoded() {
        return key;
    }
}