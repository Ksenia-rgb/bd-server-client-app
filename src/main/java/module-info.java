module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires com.zaxxer.hikari;
    requires org.apache.poi.ooxml;
    requires io.github.cdimascio.dotenv.java;
    // requires org.apache.poi.poi;

    opens bd.client.app.ui to javafx.fxml;
}