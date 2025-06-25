package es.ufv.dis.back.final2025.msa.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import es.ufv.dis.back.final2025.msa.model.Usuario;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.List;

@Service
public class PdfService {
    public void generatePdf(List<Usuario> usuarios, String outputPath) {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Informe de Usuarios", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            for (Usuario usuario : usuarios) {
                document.add(new Paragraph("\nUsuario: " + usuario.getNombre() + " " + usuario.getApellidos()));
                document.add(new Paragraph("NIF: " + usuario.getNif()));
                document.add(new Paragraph("Dirección: " +
                        usuario.getDireccion().getCalle() + ", " +
                        usuario.getDireccion().getNumero() + " " +
                        usuario.getDireccion().getPisoLetra()));
                document.add(new Paragraph("Tarjeta: ****-" +
                        String.valueOf(usuario.getMetodoPago().getNumeroTarjeta()).substring(12)));
            }

            document.close();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar PDF", e);
        }
    }
}