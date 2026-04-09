package com.ticket.electronico.shared.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

public class QRGenerator {

    public static String generarQRBase64(String contenido) {
        try {
            int width = 250;
            int height = 250;

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            var bitMatrix = qrCodeWriter.encode(contenido, BarcodeFormat.QR_CODE, width, height);

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
                }
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "PNG", outputStream);

            return Base64.getEncoder().encodeToString(outputStream.toByteArray());

        } catch (Exception e) {
            throw new RuntimeException("Error al generar QR", e);
        }
    }
}