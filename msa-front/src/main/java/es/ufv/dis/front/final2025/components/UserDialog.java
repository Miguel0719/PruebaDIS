package es.ufv.dis.front.final2025.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import es.ufv.dis.front.final2025.api.ApiClient;
import es.ufv.dis.front.final2025.model.User;

public class UserDialog extends Dialog {
    private Binder<User> binder = new Binder<>(User.class);
    private boolean readOnly = false;

    public UserDialog(User user) {
        setWidth("600px");

        FormLayout form = new FormLayout();

        // Campos básicos
        TextField id = new TextField("ID");
        TextField name = new TextField("Nombre");
        TextField email = new TextField("Email");

        // Campos de dirección
        TextField street = new TextField("Calle");
        TextField city = new TextField("Ciudad");
        TextField zipCode = new TextField("Código Postal");

        // Configurar binder
        binder.forField(id).bind(u -> u.getId(), null);
        binder.forField(name).bind(u -> u.getName(), (u, v) -> u.setName(v));
        binder.forField(email).bind(u -> u.getEmail(), (u, v) -> u.setEmail(v));
        binder.forField(street).bind(u -> u.getAddress().getStreet(), (u, v) -> u.getAddress().setStreet(v));
        binder.forField(city).bind(u -> u.getAddress().getCity(), (u, v) -> u.getAddress().setCity(v));
        binder.forField(zipCode).bind(u -> u.getAddress().getZipCode(), (u, v) -> u.getAddress().setZipCode(v));

        // Configurar modo lectura/edición
        if (readOnly) {
            id.setReadOnly(true);
            name.setReadOnly(true);
            email.setReadOnly(true);
            street.setReadOnly(true);
            city.setReadOnly(true);
            zipCode.setReadOnly(true);
        }

        // Botón de guardar
        Button saveButton = new Button("Guardar", e -> {
            if (binder.writeBeanIfValid(user)) {
                ApiClient.updateUser(user);
                close();
            }
        });

        form.add(id, name, email, street, city, zipCode, saveButton);
        add(form);
        binder.readBean(user);
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
}