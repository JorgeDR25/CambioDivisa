package cambiodivisa;

import javafx.application.Application;
import javafx.application.Preloader.ErrorNotification;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application {

	private TextField linea1Text,linea2Text;
	private ComboBox<Divisa> divisa1Combo,divisa2Combo;
	private Button convertirButton;
	private Alert ErrorDeAlerta;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		Divisa euro = new Divisa("Euro", 1.0);
		Divisa libra = new Divisa("Libra", 0.8873);
		Divisa dolar = new Divisa("Dolar", 1.2007);
		Divisa yen = new Divisa("Yen", 133.59);
		
		linea1Text = new TextField("0");
		linea1Text.setMaxWidth(80); 
		
		linea2Text = new TextField("0");
		linea2Text.setMaxWidth(80);
		linea2Text.setEditable(false);
		
		
		
		divisa1Combo= new ComboBox <Divisa>();
		divisa1Combo.getItems().addAll(
			euro,libra,dolar,yen
       );
		divisa1Combo.getSelectionModel().select(1);
		
		divisa2Combo= new ComboBox <Divisa>();
		divisa2Combo.getItems().addAll(
			euro,libra,dolar,yen
       );
		divisa2Combo.getSelectionModel().select(2);
		
		
		HBox fila1 =new HBox(5,linea1Text,divisa1Combo);
		fila1.setAlignment(Pos.CENTER);
		
		HBox fila2 =new HBox(5,linea2Text,divisa2Combo);
		fila2.setAlignment(Pos.CENTER);
		
		convertirButton = new Button("Convertir");
		convertirButton.setDefaultButton(true);
		convertirButton.setOnAction(e -> onConvertirButtonAction());
		
		VBox root = new VBox(5,fila1,fila2,convertirButton);
		root.setAlignment(Pos.CENTER);
		Scene scene =new Scene(root,320,250);
		
		
		primaryStage.setTitle("Cambio Divisa");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	private void onConvertirButtonAction() {
		
		try {
		Divisa divisaEntrada = divisa1Combo.getSelectionModel().getSelectedItem();
		Divisa divisaSalida = divisa2Combo.getSelectionModel().getSelectedItem();
		double cantidad = Double.parseDouble(linea1Text.getText());
		Divisa.fromTo(divisaEntrada, divisaSalida,cantidad);
		linea2Text.setText(Divisa.fromTo(divisaEntrada, divisaSalida,cantidad).toString());
		}catch(Exception e) {
			ErrorDeAlerta = new Alert(AlertType.ERROR);
			ErrorDeAlerta.setHeaderText("Intento de inicio de sesión");
			ErrorDeAlerta.setContentText("Error");
			ErrorDeAlerta.showAndWait();
		}
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
