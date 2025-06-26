package es.ufv.dis.front.final2025.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import es.ufv.dis.front.final2025.api.ApiClient;
import es.ufv.dis.front.final2025.components.UserDialog;
import es.ufv.dis.front.final2025.components.AddUserDialog;
import es.ufv.dis.front.final2025.model.User;

@Route("")
public class MainView extends VerticalLayout {
    private Grid<User> grid = new Grid<>(User.class);

    public MainView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);

        // Configurar el Grid
        configureGrid();

        // Botón para añadir usuario
        Button addUserButton = new Button("Añadir usuario", e -> {
            AddUserDialog dialog = new AddUserDialog();
            dialog.open();
            dialog.addOpenedChangeListener(event -> {
                if (!event.isOpened()) {
                    refreshGrid();
                }
            });
        });

        // Botón para generar PDF
        Button generatePdfButton = new Button("Generar PDF", e -> {
            ApiClient.generatePdf();
        });

        // Añadir componentes a la vista
        add(new H1("Gestión de Usuarios"), grid, addUserButton, generatePdfButton);
        refreshGrid();
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.removeAllColumns();

        // Columnas básicas
        grid.addColumn(User::getId).setHeader("ID");
        grid.addColumn(User::getName).setHeader("Nombre");
        grid.addColumn(User::getEmail).setHeader("Email");

        // Botón de editar
        grid.addComponentColumn(user -> {
            Button editButton = new Button("Editar", e -> {
                UserDialog dialog = new UserDialog(user);
                dialog.open();
                dialog.addOpenedChangeListener(event -> {
                    if (!event.isOpened()) {
                        refreshGrid();
                    }
                });
            });
            return editButton;
        }).setHeader("Acciones");

        // Doble click para ver detalles
        grid.addItemDoubleClickListener(event -> {
            UserDialog dialog = new UserDialog(event.getItem());
            dialog.setReadOnly(true);
            dialog.open();
        });
    }

    private void refreshGrid() {
        grid.setItems(ApiClient.getAllUsers());
    }
}