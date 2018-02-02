package eu.tinoba.androidarcitecturetemplate.data.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Base64;

import java.util.Map;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import static android.provider.Settings.Secure.ANDROID_ID;

public final class SecureSharedPreferences implements SharedPreferences {

    private static final String UTF8 = "utf-8";

    private static final String ENCRYPTION_DECRYPTION_METHOD = "PBEWithMD5AndDES";

    private static final int ITERATION_COUNT = 20;

    private final char[] pbePassword;

    protected Context context;

    private SharedPreferences preferences;

    public SecureSharedPreferences(final Context context, final SharedPreferences delegate, final String secret) {
        this.preferences = delegate;
        this.context = context;
        this.pbePassword = secret.toCharArray();
    }

    public Editor edit() {
        return new Editor();
    }

    /**
     * NOT SUPPORTED
     */

    @Override
    public Map<String, ?> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getBoolean(final String key, final boolean defValue) {
        final String v = preferences.getString(key, null);
        return v != null ? Boolean.parseBoolean(decrypt(v)) : defValue;
    }

    @Override
    public float getFloat(final String key, final float defValue) {
        final String v = preferences.getString(key, null);
        return v != null ? Float.parseFloat(decrypt(v)) : defValue;
    }

    @Override
    public int getInt(final String key, final int defValue) {
        final String v = preferences.getString(key, null);
        return v != null ? Integer.parseInt(decrypt(v)) : defValue;
    }

    @Override
    public long getLong(final String key, final long defValue) {
        final String v = preferences.getString(key, null);
        return v != null ? Long.parseLong(decrypt(v)) : defValue;
    }

    @Override
    public String getString(final String key, final String defValue) {
        final String v = preferences.getString(key, null);
        return v != null ? decrypt(v) : defValue;
    }

    /**
     * NOT SUPPORTED
     */

    @Nullable
    @Override
    public Set<String> getStringSet(final String key, final Set<String> defValues) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(final String s) {
        return preferences.contains(s);
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(final OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        preferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(final OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        preferences.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    /**
     * Encrypts value using PBE with MD5 and DES.
     *
     * @param value value to be encrypted.
     * @return encrypted value.
     */
    private String encrypt(final String value) {
        try {
            final byte[] bytes = value != null ? value.getBytes(UTF8) : new byte[0];
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ENCRYPTION_DECRYPTION_METHOD);
            SecretKey key = keyFactory.generateSecret(new PBEKeySpec(pbePassword));
            Cipher pbeCipher = Cipher.getInstance(ENCRYPTION_DECRYPTION_METHOD);
            pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(
                    Settings.Secure.getString(context.getContentResolver(), ANDROID_ID).getBytes(UTF8), ITERATION_COUNT));
            return new String(Base64.encode(pbeCipher.doFinal(bytes), Base64.NO_WRAP), UTF8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Decrypts encrypted value using PBE with MD5 and DES.
     *
     * @param value value to be decrypted.
     * @return original value.
     */
    private String decrypt(final String value) {
        try {
            final byte[] bytes = value != null ? Base64.decode(value, Base64.DEFAULT) : new byte[0];
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ENCRYPTION_DECRYPTION_METHOD);
            SecretKey key = keyFactory.generateSecret(new PBEKeySpec(pbePassword));
            Cipher pbeCipher = Cipher.getInstance(ENCRYPTION_DECRYPTION_METHOD);
            pbeCipher.init(Cipher.DECRYPT_MODE, key,
                           new PBEParameterSpec(Settings.Secure.getString(context.getContentResolver(), ANDROID_ID).getBytes(UTF8),
                                                ITERATION_COUNT));
            return new String(pbeCipher.doFinal(bytes), UTF8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final class Editor implements SharedPreferences.Editor {

        SharedPreferences.Editor delegate;

        Editor() {
            this.delegate = SecureSharedPreferences.this.preferences.edit();
        }

        @Override
        public Editor putBoolean(String key, boolean value) {
            delegate.putString(key, encrypt(Boolean.toString(value)));
            return this;
        }

        @Override
        public Editor putFloat(String key, float value) {
            delegate.putString(key, encrypt(Float.toString(value)));
            return this;
        }

        @Override
        public Editor putInt(String key, int value) {
            delegate.putString(key, encrypt(Integer.toString(value)));
            return this;
        }

        @Override
        public Editor putLong(String key, long value) {
            delegate.putString(key, encrypt(Long.toString(value)));
            return this;
        }

        @Override
        public Editor putString(String key, String value) {
            delegate.putString(key, encrypt(value));
            return this;
        }

        @Override
        public SharedPreferences.Editor putStringSet(String key, Set<String> values) {
            delegate.putStringSet(key, values);
            return this;
        }

        @Override
        public void apply() {
            delegate.apply();
        }

        @Override
        public Editor clear() {
            delegate.clear();
            return this;
        }

        @Override
        public boolean commit() {
            return delegate.commit();
        }

        @Override
        public Editor remove(String s) {
            delegate.remove(s);
            return this;
        }
    }
}
