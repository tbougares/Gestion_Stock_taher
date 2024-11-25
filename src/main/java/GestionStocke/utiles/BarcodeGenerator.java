package GestionStocke.utiles;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class BarcodeGenerator {

    public static void generateBarcodeImage(String barcodeText, String filePath) throws WriterException, IOException {
        if (barcodeText == null || barcodeText.isEmpty()) {
            throw new IllegalArgumentException("Le texte du code-barre ne peut pas être vide ou null.");
        }

        BitMatrix bitMatrix = new MultiFormatWriter().encode(barcodeText, BarcodeFormat.CODE_128, 300, 150);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}