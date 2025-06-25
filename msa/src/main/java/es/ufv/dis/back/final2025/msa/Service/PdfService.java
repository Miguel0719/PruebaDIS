package es.ufv.dis.back.final2025.msa.Service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import es.ufv.dis.back.final2025.msa.Model.Usuario;
import org.springframework.stereotype.Service;
import es.ufv.dis.back.final2025.msa.Model.Direccion;
import es.ufv.dis.back.final2025.msa.Model.MetodoPago;

import java.io.FileOutputStream;
import java.util.List;

@Service
public class PdfService {

    public boolean generateUsersPdf(List<Usuario> usuarios, String filename) {
        try {
            new java.io.File("pdf-files").mkdirs();
            Document document = new Document(PageSize.A4, 50, 50, 100, 72);
            PdfWriter.getInstance(document, new FileOutputStream("pdf-files/" + filename)); // Nombre personalizable
            document.open();
            addUsersToDocument(document, usuarios);
            document.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void addUsersToDocument(Document document, List<Usuario> usuarios) throws DocumentException {
        // Título del documento
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
        Paragraph title = new Paragraph("Listado de Usuarios", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        // Contenido de cada usuario
        Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
        for (Usuario usuario : usuarios) {
            addUserSection(document, usuario, contentFont);
            document.add(Chunk.NEWLINE);
        }
    }

    private void addUserSection(Document document, Usuario usuario, Font font) throws DocumentException {
        document.add(new Paragraph("ID: " + usuario.getId(), font));
        document.add(new Paragraph("Nombre completo: " + usuario.getNombre() + " " + usuario.getApellidos(), font));
        document.add(new Paragraph("NIF: " + usuario.getNif(), font));
        document.add(new Paragraph("Email: " + usuario.getEmail(), font));
        document.add(new Paragraph("Dirección: " + formatAddress(usuario.getDireccion()), font));
        document.add(new Paragraph("Método de pago: " + formatPaymentMethod(usuario.getMetodoPago()), font));
        document.add(new Paragraph("----------------------------------------------------", font));
    }

    private String formatAddress(Direccion direccion) {
        return String.format("%s, %d %s, %s %s",
                direccion.getCalle(),
                direccion.getNumero(),
                direccion.getPisoLetra(),
                direccion.getCodigoPostal(),
                direccion.getCiudad());
    }

    private String formatPaymentMethod(MetodoPago metodoPago) {
        String tarjeta = String.valueOf(metodoPago.getNumeroTarjeta());
        String lastFourDigits = tarjeta.length() > 4 ? tarjeta.substring(tarjeta.length() - 4) : tarjeta;
        return String.format("Tarjeta terminada en %s (Titular: %s)",
                lastFourDigits,
                metodoPago.getNombreAsociado());
    }
}