import java.io.IOException;
import java.util.LinkedList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage primaryStage;
    private Pane rootLayout;
    private Pane weightLayout;
    private Pane analysisLayout;
    
    private TLX tlx;
    
    private Integer[] factors_combined;
    private Integer count = 0;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TLX");
        tlx = new TLX();
        factors_combined = tlx.getFactorsCombined();

        initRootLayout();
        
        Button confirm = (Button) primaryStage.getScene().lookup("#confirm");
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                confirmData();
            }
        });
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("RootLayout.fxml"));
            rootLayout = (Pane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public void confirmData(){    	
    	tlx.md.setRating(((Slider) primaryStage.getScene().lookup("#md_slider")).getValue());
    	tlx.pd.setRating(((Slider) primaryStage.getScene().lookup("#pd_slider")).getValue());
    	tlx.td.setRating(((Slider) primaryStage.getScene().lookup("#td_slider")).getValue());
    	tlx.op.setRating(((Slider) primaryStage.getScene().lookup("#op_slider")).getValue());
    	tlx.fr.setRating(((Slider) primaryStage.getScene().lookup("#fr_slider")).getValue());
    	tlx.ef.setRating(((Slider) primaryStage.getScene().lookup("#ef_slider")).getValue());
    	
    	initWeightLayout();
    }
    
    public void initWeightLayout(){
    	try {
             FXMLLoader loader = new FXMLLoader();
             loader.setLocation(Main.class.getResource("WeightLayout.fxml"));
             weightLayout = (Pane) loader.load();

             Scene scene = new Scene(weightLayout);
             primaryStage.setScene(scene);
             primaryStage.show();
         } catch (IOException e) {
             e.printStackTrace();
         }
    	
    	sumWeights();
    	
    	Button button_a = (Button) primaryStage.getScene().lookup("#button_a");
    	Button button_b = (Button) primaryStage.getScene().lookup("#button_b");
    	
        button_a.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
            	LinkedList<Factor> factors = tlx.getFactors();
            	for(Factor x : factors){
            		if(x.title == button_a.getText()) x.increaseWeight();
            	}
                sumWeights();
            }
        });
        button_b.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
            	LinkedList<Factor> factors = tlx.getFactors();
            	for(Factor x : factors){
            		if(x.title == button_b.getText()) x.increaseWeight();
            	}
                sumWeights();
            }
        });
    }
    
    private void sumWeights(){
    	Button button_a = (Button) primaryStage.getScene().lookup("#button_a");
    	Button button_b = (Button) primaryStage.getScene().lookup("#button_b");
    	
    	if(count < factors_combined.length){
    		LinkedList<Factor> factors = tlx.getFactors();
    		button_a.setText(factors.get(factors_combined[count]).title);
    		button_b.setText(factors.get(factors_combined[count+1]).title);
    		
    		count+=2;
    	}else{
    		tlx.calculateAVG();
    		initAnalysisLayout();
    	}
    }
    
    public void initAnalysisLayout(){
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("AnalysisLayout.fxml"));
            analysisLayout = (Pane) loader.load();

            Scene scene = new Scene(analysisLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	Label avg = (Label) primaryStage.getScene().lookup("#avg");
    	avg.setText(tlx.getAVG().toString());
    }
}
