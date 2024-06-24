package hotelService.hotel.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Slf4j
@Component
public class EncryptDecrypt {

    public String decrypt(String strToEncrypt) {

        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(getSecretCode(), "AES");
            byte[] iv = new byte[12];
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new GCMParameterSpec(128, iv));
            byte[] decodedString = Base64.getDecoder().decode(strToEncrypt);
            return new String(cipher.doFinal(decodedString));
        } catch (Exception e) {
            log.info("Exception while decrypting ", e);
        }
        return null;
    }


    private byte[] getSecretCode() {
        return "GAF7uWpTx7n+E0yHqodPkW7AdVmQRFSE".getBytes();
    }
}
