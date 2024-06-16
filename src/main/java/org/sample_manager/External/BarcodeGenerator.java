package org.sample_manager.External;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public abstract class BarcodeGenerator implements Serializable {

    public static void execute(String barcodeText) {
        int width = 500;
        int height = 200;
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.MARGIN, 0);
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix bitMatrix = new Code128Writer().encode(barcodeText, BarcodeFormat.CODE_128, width, height, hintMap);

        Path path = FileSystems.getDefault().getPath("temp_code.png");
        try (FileOutputStream fos = new FileOutputStream(path.toString())) {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", fos);
        } catch (Exception e) {
            System.out.println("Error when generating the barcode: " + e.getMessage());
        }
    }
}
