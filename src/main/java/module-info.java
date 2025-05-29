module org.example.sanwichapp_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.fasterxml.jackson.databind;

    opens org.example.sanwichapp_gui to javafx.fxml;
    exports org.example.sanwichapp_gui;
    exports org.example.sanwichapp_gui.Screens;
    opens org.example.sanwichapp_gui.Screens to javafx.fxml;
}