package com.banco_digital.utilitarios;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

@Component
public class CodificarImagem {
    //metodo que codifica a imagem em String recebendo uma URL
    public static String encodeImage(String imgPath, String savePath) throws Exception {

        // ler a imagem do arquivo
        FileInputStream stream = new FileInputStream(imgPath);

        // cria um array de byte para a imagem stream
        int bufLength = 2048;
        byte[] buffer = new byte[2048];
        byte[] data;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int readLength;
        while ((readLength = stream.read(buffer, 0, bufLength)) != -1) {
            out.write(buffer, 0, readLength);
        }
        data = out.toByteArray();
        String imageString = Base64.getEncoder().encodeToString(data);
        FileWriter fileWriter = new FileWriter(savePath);
        fileWriter.write(imageString);

        // fecha o streams
        fileWriter.close();
        out.close();
        stream.close();

        return imageString;
    }

    //metodo que decodifica a imagem em string de volta ao formato de imagem
    public static void decodeImage(String txtPath, String savePath) throws Exception {
        // ler o texto do arquivo
        FileInputStream inputStream = new FileInputStream(txtPath);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int len = 2048;
        byte[] buffer = new byte[len];
        byte[] textData;
        int readLength;
        while ((readLength = inputStream.read(buffer, 0, len)) != -1) {
            out.write(buffer, 0, readLength);
        }
        textData = out.toByteArray();
        byte[] data = Base64.getDecoder().decode(new String(textData));
        FileOutputStream fileOutputStream = new FileOutputStream(savePath);
        fileOutputStream.write(data);
        out.close();

        // fecha o streams
        fileOutputStream.close();
        inputStream.close();
    }

    //metodo que codifica a imagem em String recebendo uma BufferedImage
    public static String encodeToString(BufferedImage image, String tipo) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, tipo, bos);
            byte[] imageBytes = bos.toByteArray();
            imageString = Base64.getEncoder().encodeToString(imageBytes);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
}
