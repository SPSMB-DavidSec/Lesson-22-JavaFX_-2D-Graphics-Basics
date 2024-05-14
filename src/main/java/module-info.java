module com.example.fx_canvas_demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fx_canvas_demo to javafx.fxml;
    exports com.example.fx_canvas_demo;
}