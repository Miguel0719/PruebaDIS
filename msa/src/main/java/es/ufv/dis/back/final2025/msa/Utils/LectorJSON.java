package es.ufv.dis.back.final2025.msa.Utils;

import es.ufv.dis.back.final2025.msa.Model.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import org.springframework.stereotype.Component;

/**
 * Clase para manejar la lectura/escritura de archivos JSON con usuarios.
 * Usa GSON para serialización/deserialización.
 */
@Component
public class LectorJSON {

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    private static final String JSON_PATH = "msa/src/main/resources/usuarios.json";

    /**
     * Lee un archivo JSON y devuelve una lista de usuarios.
     * @param path Ruta del archivo JSON.
     * @return Lista de usuarios (o lista vacía si hay errores).
     */
    public List<Usuario> leerJSON(String path) {
        if (!Files.exists(Paths.get(path))) {
            System.err.println("Error: Archivo no encontrado - " + path);
            return Collections.emptyList(); // Devuelve lista vacía en lugar de null
        }

        try (FileReader reader = new FileReader(path)) {
            Type tipoListaUsuarios = new TypeToken<ArrayList<Usuario>>() {}.getType();
            List<Usuario> usuarios = gson.fromJson(reader, tipoListaUsuarios);
            return usuarios != null ? usuarios : Collections.emptyList();
        } catch (IOException | JsonSyntaxException e) {
            System.err.println("Error al leer el JSON: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Escribe una lista de usuarios en un archivo JSON.
     * @param usuarios Lista de usuarios a serializar.
     * @param path Ruta de destino del archivo.
     */
    public void escribirJSON(List<Usuario> usuarios, String path) {
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            System.err.println("Error al escribir el JSON: " + e.getMessage());
            throw new RuntimeException("No se pudo guardar el archivo", e);
        }
    }
}