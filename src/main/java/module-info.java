module ci.miage {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.base;
    requires java.base;


    //requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires MaterialFX;
    requires java.sql;
    requires mysql.connector.java;
    requires javax.mail;
    requires jfoenix;
    requires controlsfx;
    opens ci.miage.vue to javafx.fxml;
    opens ci.miage.controller to javafx.fxml;
    exports ci.miage;
    exports ci.miage.controller;
    exports ci.miage.utilis;
    exports ci.miage.modele;
}