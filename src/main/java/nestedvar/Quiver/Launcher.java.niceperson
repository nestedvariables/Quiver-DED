package nestedvar.Quiver;

import javax.security.auth.login.LoginException;
import javax.swing.text.html.ImageView;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;   
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;

public class Launcher extends Application implements EventHandler<ActionEvent>{

    
    static Button buttonRestart = new Button();
    static Button buttonStart = new Button();
    static Button buttonStop = new Button();
    static Quiver quiver;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        Pane layout = new Pane();
        Scene scene = new Scene(layout, 500, 300);

        TextField nameField = new TextField();
        nameField.setPromptText("Enter the name of the bot!");
        HBox nameBox = new HBox();
        nameBox.getChildren().add(nameField);
        
        TextField statusField = new TextField();
        statusField.setPromptText("Enter the status to be displayed use %shard% to display which shard the server is on");
        HBox statusBox = new HBox();
        statusBox.getChildren().add(statusField);
        statusBox.setLayoutY(30);

        TextField tokenField = new TextField();
        tokenField.setPromptText("Enter the bot token");
        HBox tokenBox = new HBox();
        tokenBox.getChildren().add(tokenField);
        tokenBox.setLayoutY(60);

        TextField shardField = new TextField();
        shardField.setPromptText("Enter the shard count. Use -1 if you dont know how to calculate shard count");
        HBox shardBox = new HBox();
        shardBox.getChildren().add(shardField);
        shardBox.setLayoutY(90);

        TextField webhookField = new TextField();
        webhookField.setPromptText("Enter the startup log webhook link");
        HBox webhookBox = new HBox();
        webhookBox.getChildren().add(webhookField);
        webhookBox.setLayoutY(120);

        buttonStart.setText("Start Quiver");
        buttonStart.setOnAction(this);
        buttonStart.setTranslateX(50);
        buttonStart.setTranslateY(250);

        buttonRestart.setText("Restart Quiver");
        buttonRestart.setOnAction(this);
        buttonRestart.setLayoutX(200);
        buttonRestart.setLayoutY(250);

        buttonStop.setText("Stop Quiver");
        buttonStop.setOnAction(this);
        buttonStop.setTranslateX(350);
        buttonStop.setTranslateY(250);


        window.setTitle("Quiver");
        window.getIcons().add(new Image("file:Quiver.png"));
        layout.getChildren().addAll(
            buttonStart,
            buttonStop,
            buttonRestart,
            nameBox,
            statusBox,
            tokenBox,
            shardBox,
            webhookBox
            );
        window.setScene(scene);
        window.show();        

    }

    @Override
    public void handle(ActionEvent event){
        if(event.getSource() == buttonStop){
            System.out.println("Pressed the Stop button");
        }
        if(event.getSource() == buttonRestart){
            System.out.println("Pressed the Restart button");
        }
        if(event.getSource() == buttonStart){
            System.out.println("Pressed the Start button");
        }
    }

    public static void restart() {
        try {
            quiver.exit();
            quiver = new Quiver();
        }
        catch (LoginException e) {
            System.out.println("🎟 Looks like your token is invalid.");
            System.exit(1);
        }
    }
}