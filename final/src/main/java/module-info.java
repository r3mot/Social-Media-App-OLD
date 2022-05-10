module social {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens social.Controllers to javafx.fxml;
    exports social;
}
