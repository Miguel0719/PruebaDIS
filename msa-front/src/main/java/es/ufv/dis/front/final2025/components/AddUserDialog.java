package es.ufv.dis.front.final2025.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import es.ufv.dis.front.final2025.api.ApiClient;
import es.ufv.dis.front.final2025.model.User;

public class AddUserDialog extends Dialog {
    public AddUserDialog() {
        setWidth("600px");

        FormLayout form = new FormLayout();
        User newUser = new User();
        newUser.setId(java.util.UUID.randomUUID().toString());

        TextField name = new TextField("Nombre");
        TextField email = new TextField("Email");
        TextField street = new TextField("Calle");
        TextField city = new TextField("Ciudad");
        TextField zipCode = new TextField("Código Postal");

        Button saveButton = new Button("Guardar", e -> {
            newUser.setName(name.getValue());
            newUser.setEmail(email.getValue());
            newUser.setAddress(new User.Address());
            newUser.getAddress().setStreet(street.getValue());
            newUser.getAddress().setCity(city.getValue());
            newUser.getAddress().setZipCode(zipCode.getValue());
            ApiClient.addUser(newUser);
            close();
        });

        form.add(name, email, street, city, zipCode, saveButton);
        add(form);
    }
}