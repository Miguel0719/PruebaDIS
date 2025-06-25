package es.ufv.dis.back.final2025.msa.service;

import es.ufv.dis.back.final2025.msa.model.Usuario;
import es.ufv.dis.back.final2025.msa.utils.LectorJSON;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final String JSON_PATH = "data/usuarios.json";
    private final LectorJSON lectorJSON;

    public UserService(LectorJSON lectorJSON) {
        this.lectorJSON = lectorJSON;
    }

    public List<Usuario> getAllUsers() {
        return lectorJSON.leerJSON(JSON_PATH);
    }

    public Optional<Usuario> getUserById(String id) {
        return lectorJSON.leerJSON(JSON_PATH).stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public void saveUser(Usuario usuario) {
        List<Usuario> usuarios = getAllUsers();
        usuarios.add(usuario);
        lectorJSON.escribirJSON(usuarios, JSON_PATH);
    }

    public void updateUser(String id, Usuario updatedUser) {
        List<Usuario> usuarios = getAllUsers();
        usuarios.removeIf(u -> u.getId().equals(id));
        updatedUser.setId(id); // Asegura que el ID no cambie
        usuarios.add(updatedUser);
        lectorJSON.escribirJSON(usuarios, JSON_PATH);
    }
}