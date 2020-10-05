package ehu.isad;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ComboBoxExperiments extends Application  {

    ListView<Argazki> listViewOfArgazki;
    ImageView imageView=new ImageView();
    ComboBox comboBilduma;

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Elefanteak, landareak etc*/

        comboBilduma = new ComboBox();

        List<String> bildumak = List.of("abereak", "landareak", "frutak");


        ObservableList<String> bildumaList = FXCollections.observableArrayList(bildumak);


        comboBilduma.setItems(bildumaList);

        Map<String, List<Argazki>> bildumaMap = new HashMap<>();

        bildumaMap.put("abereak", List.of(
                new Argazki("Elefantea", "elefantea.jpeg"),
                new Argazki("Txakurra", "txakurra.jpeg"),
                new Argazki("Untxia", "untxia.png")
        ));

        bildumaMap.put("landareak", List.of(
                new Argazki("Kaktusa", "cactus.png"),
                new Argazki("Berdea", "landareberdea.jpeg"),
                new Argazki("Ezezaguna", "landarehoria.jpeg")
        ));

        bildumaMap.put("frutak", List.of(
                new Argazki("Marrubia", "fresa.jpeg"),
                new Argazki("Sandia", "sandia.png"),
                new Argazki("Sagarra", "sagarra.jpeg")
        ));

        ObservableList<Argazki> argazkiList = FXCollections.observableArrayList();
        argazkiList.addAll(bildumaMap.get("abereak"));

        listViewOfArgazki = new ListView<>(argazkiList);
        listViewOfArgazki.getSelectionModel().selectedItemProperty().addListener(  (observable, oldValue, newValue) -> {
            if (observable.getValue() == null) return;

            String fitx = observable.getValue().getFitx();

            try {
                imageView.setImage(lortuIrudia(fitx /* 48x48 */));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


        /* ################################################################
        Kaktusa (patata nire kasuan */
        /*InputStream is = getClass().getResourceAsStream("/potato.png");
        BufferedImage reader = ImageIO.read(is);
        Image image = SwingFXUtils.toFXImage(reader,null);
        ImageView imageView = new ImageView(image);
        VBox vbox = new VBox(imageView);
        vbox.setAlignment(Pos.BASELINE_CENTER);
        vbox.setPadding(new Insets(0,0,0,0));
        Scene scene = new Scene(vbox, 350, 250);
        primaryStage.setScene(scene);
        primaryStage.show();*/


        /* ################################################################
        Txanpona
         */
        /*
        primaryStage.setTitle("ComboBox Experiment 1");

        ComboBox comboBox = new ComboBox();

        comboBox.getItems().add("BTC");
        comboBox.getItems().add("ETH");
        comboBox.getItems().add("LTC");

        comboBox.setEditable(true);

        comboBox.getSelectionModel().selectFirst();

        String txanpon = ((String) comboBox.getValue()).toLowerCase();
        String txanpona= Float.toString(this.getBalioa(txanpon));
        Label txanponaL = new Label("1 "+txanpon+" = "+txanpona);

        comboBox.setOnAction(e -> {
            String txanpon1 = ((String) comboBox.getValue()).toLowerCase();
            String txanpona1= Float.toString(this.getBalioa(txanpon1));
            txanponaL.setText("1 "+txanpon1+" = "+txanpona1);
        });

        VBox vbox = new VBox(txanponaL,comboBox);

        Scene scene = new Scene(vbox, 200, 120);
        primaryStage.setScene(scene);
        primaryStage.show();*/
        comboBilduma.setOnAction(e -> {
            argazkiList.clear();
            argazkiList.addAll(bildumaMap.get(comboBilduma.getValue()));
        });
        VBox vbox = new VBox(comboBilduma,listViewOfArgazki,imageView);

        Scene scene = new Scene(vbox, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private Image lortuIrudia(String location) throws IOException {

        InputStream is = getClass().getResourceAsStream("/" + location);
        BufferedImage reader = ImageIO.read(is);
        return SwingFXUtils.toFXImage(reader, null);

    }

    /*Txanpona*//*public float getBalioa(String txanpon){

        Gson gson = new Gson();
        Txanpona txanpona = gson.fromJson(this.getURL(txanpon), Txanpona.class);
        return txanpona.price;

    }

    public String getURL(String txanpon){
        String inputLine = "";
        URL coinmarket;

        try{
            coinmarket = new URL("https://api.gdax.com/products/"+txanpon+"-eur/ticker");
            URLConnection yc = coinmarket.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            inputLine = in.readLine();
            in.close();
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputLine;
    }
*/
    public static void main(String[] args) {
        Application.launch(args);
    }


}
