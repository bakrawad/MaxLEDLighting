package com.example.proj1_1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

public class MaxLEDLighting extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = new Pane();
        Image image = new Image("Circuit.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        pane.setBackground(new Background(backgroundImage));

        Stage stage2 = new Stage();//stage2 for file chooser
        VBox vBox1 = new VBox(10);
        Button btStart = new Button("Start");
        Button btRead = new Button("Read from File");
        btStart.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR , 13));
        btRead.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 13));

        //style for button
        btRead.setStyle("-fx-background-color: #20588d;-fx-text-fill: white;-fx-padding: 10 20;");
        btRead.setOnMouseEntered(e -> btRead.setStyle("-fx-background-color: #010a2a; -fx-text-fill: white; -fx-padding: 10 20;"));
        btRead.setOnMouseExited(e -> btRead.setStyle("-fx-background-color: #20588d;-fx-text-fill: white;-fx-padding: 10 20;"));
        btStart.setStyle("-fx-background-color: #20588d;-fx-text-fill: white;-fx-padding: 10 20;");
        btStart.setOnMouseEntered(e -> btStart.setStyle("-fx-background-color: #010a2a;-fx-text-fill: white; -fx-padding: 10 20;"));
        btStart.setOnMouseExited(e -> btStart.setStyle("-fx-background-color: #20588d;-fx-text-fill: white;-fx-padding: 10 20;"));
        //pane.add(vBox1,57,55);
        vBox1.setLayoutX(550);
        vBox1.setLayoutY(500);
        pane.getChildren().add(vBox1);


       // pane.add(lbarea,80,53);

        //create Label of Result
        Label lbExpected = new Label("The Expected Result" +
                "\n ");

        Label lbLed = new Label("The Expected LED's ");
        TextArea txExpected = new TextArea();
        TextArea txLed = new TextArea();
        txLed.setEditable(false);
        txExpected.setEditable(false);
        lbExpected.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        lbExpected.setStyle("-fx-text-fill: white;");
        lbLed.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        lbLed.setStyle("-fx-text-fill: white;");
        VBox vlabel = new VBox(10);
        vlabel.getChildren().addAll(lbExpected,lbLed);
        VBox vtex = new VBox(10);
        vtex.getChildren().addAll(txExpected,txLed);
        txExpected.setPrefSize(250,20);
        txLed.setPrefSize(250,20);
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(vlabel,vtex);
        hBox.setVisible(false);
        hBox.setLayoutX(800);
        hBox.setLayoutY(300);
        pane.getChildren().add(hBox);

        // add Button and TextFild of Led and Input to add TextFild
        Button btadd = new Button("Add");
        btadd.setStyle("-fx-background-color: #20588d;-fx-text-fill: white;-fx-padding: 10 20;");
        btadd.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR , 13));
        btadd.setOnMouseEntered(e -> btadd.setStyle("-fx-background-color: #010a2a; -fx-text-fill: white; -fx-padding: 10 20;"));
        btadd.setOnMouseExited(e -> btadd.setStyle("-fx-background-color: #20588d;-fx-text-fill: white;-fx-padding: 10 20;"));

        TextField txled = new TextField();
        TextField txinp = new TextField();
        txled.setPadding(new Insets(10,20,10,10));
        txinp.setPadding(new Insets(10,20,10,10));
        VBox vled = new VBox(10);
        Label lbled = new Label("The Number Of LED");
        lbled.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        lbled.setStyle("-fx-text-fill: white;");
        Label lbinp = new Label("The Number Of Input");
        lbinp.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        lbinp.setStyle("-fx-text-fill: white;");
        vled.getChildren().addAll(btadd,lbled,txled,lbinp,txinp);
        vled.setLayoutX(530);
        vled.setLayoutY(280);
        pane.getChildren().add(vled);
        vled.setAlignment(Pos.CENTER);
        //pane.add(hBox,79,40);
        vBox1.getChildren().addAll(btStart,btRead);
        vBox1.setAlignment(Pos.TOP_CENTER);
        VBox vtxled = new VBox(10);

        //Button of add TextBox on LED and Input and Random
        ArrayList<TextField> Ltxled = new ArrayList<>();
        ArrayList<TextField> Ltxinp = new ArrayList<>();
        VBox vtxinp = new VBox(10);

        Button btRandom = new Button("Random");
        btRandom.setStyle("-fx-background-color: #20588d;-fx-text-fill: white;-fx-padding: 10 20;");
        btRandom.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR , 13));
        btRandom.setOnMouseEntered(e -> btRandom.setStyle("-fx-background-color: #010a2a; -fx-text-fill: white; -fx-padding: 10 20;"));
        btRandom.setOnMouseExited(e -> btRandom.setStyle("-fx-background-color: #20588d;-fx-text-fill: white;-fx-padding: 10 20;"));

        Pane paneLed = new Pane();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(50);
        scrollPane.setLayoutY(150);
        scrollPane.setPrefSize(350,400);
        scrollPane.setContent(paneLed);
        btRandom.setLayoutX(410);
        btRandom.setLayoutY(150);
        pane.getChildren().add(btRandom);
        btRandom.setVisible(false);
//        scrollPane.setBackground(new Background(backgroundImage));
//        scrollPane.setStyle("-fx-background-color: #20588d;");
        Image gif = new Image("giphy.gif");
        ImageView imageView = new ImageView(gif);
        imageView.setLayoutX(-58);
        imageView.setLayoutY(20);
        paneLed.getChildren().add(imageView);
        pane.getChildren().add(scrollPane);
        scrollPane.setVisible(false);


        //another stage for TextArea
        Pane pane2 = new Pane();
        Scene scene2 = new Scene(pane2,700,600);
        Stage stagee = new Stage();
        stagee.setScene(scene2);
        pane2.setBackground(new Background(backgroundImage));

        //create TextArea DP table

        TextArea AreaDp = new TextArea();
        AreaDp.setMaxSize(400,200);
        AreaDp.setEditable(false);
        AreaDp.setLayoutY(70);
        AreaDp.setLayoutX(20);
        AreaDp.setPrefSize(1500,500);
        AreaDp.setMinWidth(660);
        AreaDp.setMinHeight(400);
        pane2.getChildren().add(AreaDp);
        // pane.add(varea,80,54);
        Label lbarea = new Label("The DP Table");
        lbarea.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        lbarea.setStyle("-fx-text-fill: white;");
        lbarea.setLayoutX(300);
        lbarea.setLayoutY(40);
        pane2.getChildren().add(lbarea);

        Button btClose = new Button("Close");
        btClose.setStyle("-fx-background-color: #20588d;-fx-text-fill: white;-fx-padding: 10 20;");
        btClose.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR , 13));
        btClose.setOnMouseEntered(e -> btClose.setStyle("-fx-background-color: #010a2a; -fx-text-fill: white; -fx-padding: 10 20;"));
        btClose.setOnMouseExited(e -> btClose.setStyle("-fx-background-color: #20588d;-fx-text-fill: white;-fx-padding: 10 20;"));
        btClose.setOnAction(e ->{
            stagee.close();
        });
        btClose.setLayoutY(500);
        btClose.setLayoutX(300);
        pane2.getChildren().add(btClose);

        //button to open the Stage !
        Button btTable = new Button("Open Table");
        btTable.setStyle("-fx-background-color: #20588d;-fx-text-fill: white;-fx-padding: 10 20;");
        btTable.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR , 13));
        btTable.setOnMouseEntered(e -> btTable.setStyle("-fx-background-color: #010a2a; -fx-text-fill: white; -fx-padding: 10 20;"));
        btTable.setOnMouseExited(e -> btTable.setStyle("-fx-background-color: #20588d;-fx-text-fill: white;-fx-padding: 10 20;"));
        btTable.setLayoutY(400);
        btTable.setLayoutX(1050);
        pane.getChildren().add(btTable);
        btTable.setOnAction(e ->{
            stagee.show();
        });
        btTable.setVisible(false);
        txled.setOnKeyTyped(e ->{
            String s = e.getText();
            if (!ishasInt(txled.getText())||Integer.parseInt(txled.getText())<0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("Please Enter a Number !!");
                alert.show();
                txled.clear();
                return;
            }else {
                txinp.setText(txled.getText());
            }


        });
        AtomicBoolean flag = new AtomicBoolean(false);

        btadd.setOnAction(e ->{ // this button to add textField's
            paneLed.getChildren().clear();
            txLed.clear();
            txExpected.clear();
            AreaDp.clear();
            AreaDp.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
            if (!ishasInt(txled.getText())||!ishasInt(txinp.getText())){//if not enter a number
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("Please Input A The Led And Input Number !!");
                alert.show();
                txled.clear();
                txinp.clear();
                return;
            }

            int z = Integer.parseInt(txinp.getText());
            int x = Integer.parseInt(txled.getText());
            txled.clear();
            txinp.clear();
            if (x==0&&z==0){
                paneLed.getChildren().add(imageView);
            }
            if (x<z){//if number of socket above the led's
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("The Number Of Led Must Be Grater than Input !!");
                alert.show();
                txled.clear();
                txinp.clear();
                return;
            }
            if (x<0||z<0){//if number smaller than zero
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("The Number Is Smaller than Zero !!");
                alert.show();
                txled.clear();
                txinp.clear();
                return;
            }
            scrollPane.setVisible(true);
            btRandom.setVisible(true);
            Ltxled.clear();
            vtxled.getChildren().clear();

            for (int i = 0 ; i < x ; i++){//to create the textfield of LED's
                TextField ti = new TextField();
                ti.setPrefSize(35,35);
                ti.setMaxWidth(35);
                ti.setMaxHeight(35);
                Ltxled.add(ti);
                flag.set(true);//set flag true for random button
            }


//            vtxled.setLayoutX(50);
//            vtxled.setLayoutY(150);
//            vtxled.setMaxSize(150,150);
//            pane.getChildren().add(vtxled);
//            vtxinp.setLayoutX(250);
//            vtxinp.setLayoutY(150);
//            vtxinp.setMaxSize(150,150);
//            pane.getChildren().add(vtxinp);
            double startX_LED = 10, startY_LED = 10;
            for (int i = 0 ; i < x ; i++){//to center the TextField of LED's
                Ltxled.get(i).setLayoutX(startX_LED);
                Ltxled.get(i).setLayoutY(startY_LED);

                paneLed.getChildren().add(Ltxled.get(i));
                startY_LED += 50;
                //vtxled.getChildren().add(Ltxled.get(i));
            }

            Ltxinp.clear();
            vtxinp.getChildren().clear();
            for (int i = 0 ; i < z ; i++){//to create the textfield of Input's
                TextField ti = new TextField();
                ti.setPrefSize(35,35);
                ti.setMaxWidth(35);
                ti.setMaxHeight(35);
                Ltxinp.add(ti);

            }

            double startX_INPUT = 210, startY_INPUT = 10;
            for (int i = 0 ; i < z ; i++){//to center the TextField of input


                Ltxinp.get(i).setLayoutX(startX_INPUT);
                Ltxinp.get(i).setLayoutY(startY_INPUT);

                paneLed.getChildren().add(Ltxinp.get(i));
                startY_INPUT += 50;
                //vtxinp.getChildren().add(Ltxinp.get(i));
            }
            for (int i = 0 ; i < Ltxinp.size() ; i++){//to put in the TextFeild of Input Num from 1 to n
                Ltxinp.get(i).setText(i+1+"");
            }

        });


//        HBox hboxleds = new HBox();
//        hboxleds.setLayoutX(50);
//        hboxleds.setLayoutY(150);
//        hboxleds.getChildren().addAll(vtxled,vtxinp);
//        pane.getChildren().add(hboxleds);
//        hboxleds.setStyle("-fx-spacing: 50; -fx-padding: 0;"); // Apply styles directly to HBox
//        hboxleds.setMaxSize(150,150);
//        hboxleds.setAlignment(Pos.TOP_CENTER);
//        vtxinp.setAlignment(Pos.CENTER);
//        vtxled.setAlignment(Pos.CENTER);






        btStart.setOnAction(e ->{//Button to start the Dynamic Algorithm

            if (!CheckTextField(Ltxinp.size(),Ltxled.size())){//to check if you add the TextField
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("Please add the Led's and the Input !!");
                alert.show();
                return;
            }
//            for (int i = 0;i<Ltxled.size();i++){//this for loop to check of TextField's of LED's
//                if (Ltxled.get(i).getText().equals("")){//to check if empty
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Error !!");
//                    alert.setContentText("Please Fill the textField's");
//                    alert.show();
//                    return;
//                }
//                if (!ishasInt(Ltxled.get(i).getText())){//to check if it has a correct value
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Error !!");
//                    alert.setContentText("Please put a correct value");
//                    alert.show();
//                    return;
//                }
//            }
            if (!CheckTheField(Ltxled)){//this check of TextField's of LED's
                return;
            }
//            for (int i = 0; i < Ltxled.size(); i++) {//to check if it has a repeat number
//                for (int j = i+1; j < Ltxled.size(); j++) {
//                    if (Ltxled.get(i).getText().equals(Ltxled.get(j).getText())){
//                        Alert alert = new Alert(Alert.AlertType.ERROR);
//                        alert.setTitle("Error !!");
//                        alert.setContentText("Please Dont repeat the number !!");
//                        alert.show();
//                        return;
//                    }
//                }
//            }
            if(!Duplicat(Ltxled)){//to check if Duplicat
                return;
            }

//            for (int i = 0;i<Ltxinp.size();i++) {//this for loop to check of TextField's of Input's
//                if (Ltxinp.get(i).getText().equals("")) {//to check if empty
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Error !!");
//                    alert.setContentText("Please Fill the textField's");
//                    alert.show();
//                    return;
//                }
//                if (!ishasInt(Ltxinp.get(i).getText())) {//to check if it has a correct value
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Error !!");
//                    alert.setContentText("Please put a correct value");
//                    alert.show();
//                    return;
//                }
//            }
//            for (int i = 0; i < Ltxinp.size(); i++) {//to check if it has a repeat number
//                for (int j = i+1; j < Ltxinp.size(); j++) {
//                    if (Ltxinp.get(i).getText().trim().equals(Ltxinp.get(j).getText().trim())){
//                        Alert alert = new Alert(Alert.AlertType.ERROR);
//                        alert.setTitle("Error !!");
//                        alert.setContentText("Please Dont repeat the number !!");
//                        alert.show();
//                        return;
//                    }
//                }
//            }
            if (!CheckTheField(Ltxinp)){//this check of TextField's of LED's
                return;
            }
            if (!Duplicat(Ltxinp)){//to check if Duplicat
                return;
            }
            flag.set(false);//set flag false for Random button
            hBox.setVisible(true);
            btTable.setVisible(true);
            lbarea.setVisible(true);


            int x[] = new int[Ltxinp.size()];
            int y[] = new int [Ltxled.size()];

            for (int i = 0 ; i<Ltxinp.size();i++){ //this for loop to put the value og textfield in array

                x[i]=Integer.parseInt(Ltxinp.get(i).getText());
            }
            for (int i = 0 ; i<Ltxled.size();i++){
                y[i]=Integer.parseInt(Ltxled.get(i).getText());
            }

            int c[][] = LED(x,y);//table of Led
            String p[][] = LEDOfDirc(x,y);//the direct of led
            ArrayList<Integer> res = result(x, x.length,y.length,p);//array for result
//            String result[] = result(x,x.length,y.length,p).toString().split("");//to convert the result to String from method

//            for (int i = 0;i<result.length;i++){//to convert the string result to array of int
//                if (result[i].equals(",")&& i == result.length-1){
//                    break;
//                }
//                if (result[i].equals(",")){
//                    res.add(Integer.parseInt(result[i+1]));
//                    i++;
//                    continue;
//                }
//                res.add(Integer.parseInt(result[i]));
//            }

            txExpected.setText(res.size()+"");//this to put the expected result into a textarea
            String led = " ";
            for (int i = 0 ; i<res.size();i++){//this for loop to print the expected Led's
                led+=res.get(i)+" | ";
            }
            txLed.setText(led);
            AreaDp.clear();


            AreaDp.appendText("             ");//to print the DP Table
            AreaDp.appendText("\t");
            for (int i =0;i<y.length;i++){
                AreaDp.appendText(y[i]+"\t");
            }
            AreaDp.appendText("\n");
            for (int i =0;i<c.length;i++){ //this for loop to print the area dp
                AreaDp.appendText(i+"\t");
                for (int j = 0;j<c[i].length;j++){
                    AreaDp.appendText(c[i][j]+"\t| ");
                }
                AreaDp.appendText("\n");
            }
            AreaDp.appendText("\n");
            AreaDp.appendText("__________________________________");
            AreaDp.appendText("\n\n");
            AreaDp.appendText("             ");
            AreaDp.appendText("\t");
            for (int i =0;i<y.length;i++){
                AreaDp.appendText(y[i]+"\t");
            }
            AreaDp.appendText("\n");
            for (int i =0;i<c.length;i++){ //to print the direct of DP Table
                AreaDp.appendText(i+"\t");
                for (int j = 0;j<c[i].length;j++){
                    AreaDp.appendText(p[i][j]+"\t|");
                }
                AreaDp.appendText("\n");
            }
            /*
                ArrayList<TextField> Ltxled = new ArrayList<>();
                ArrayList<TextField> Ltxinp = new ArrayList<>();
             */

            for (int i = 0; i < Ltxinp.size(); i++) {//to start put Lines in the textFild's
                for (int j = 0; j < Ltxled.size(); j++) {
                    for (int u = 0; u < res.size(); u++) {
                        if ((res.get(u) + "").equals(Ltxled.get(j).getText())) {
                            if (Ltxinp.get(i).getText().equals(Ltxled.get(j).getText().trim())) {
                                Line l = new Line();
                                l.setStroke(Color.GREEN);
                                l.setStrokeWidth(3);
                                l.setStartX(Ltxinp.get(i).getLayoutX());
                                l.setStartY(Ltxinp.get(i).getLayoutY());
                                l.setEndX(Ltxled.get(j).getLayoutX());
                                l.setEndY(Ltxled.get(j).getLayoutY());
                                paneLed.getChildren().add(l);
                            }
                        }
                    }
                }
            }
            /*
            this to add cooler to the LED's
             */
            for (int i = 0; i < res.size(); i++) {//this to color the textField of LED
                for (int j = 0; j < Ltxled.size(); j++) {
                    if (Integer.parseInt(Ltxled.get(j).getText())==res.get(i)){
                        Ltxled.get(j).setStyle("-fx-background-color: #ffff87;");
                    }
                }
            }
            for (int i = 0; i < res.size(); i++) {//this to put LED Left The Input and color the textField
                for (int j = 0; j < Ltxinp.size(); j++) {
                    if (Integer.parseInt(Ltxinp.get(j).getText())==res.get(i)){
                        double v = Ltxinp.get(j).getLayoutX();
                        double b = Ltxinp.get(j).getLayoutY();
                        Ltxinp.get(j).setStyle("-fx-background-color: #ffff87;");
                        ImageView img = new ImageView(new Image("LED.png"));
                        img.setFitHeight(25);
                        img.setFitWidth(25);
                        img.setLayoutY(b);
                        img.setLayoutX(v+50);

                        paneLed.getChildren().add(img);
                    }
                }
            }


        });

        btRandom.setOnAction(e ->{//Button to set Random of Number of Led
            if (!flag.get()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("Please add New Element !!");
                alert.show();
                return;
            }
            if (!CheckTextField(Ltxinp.size(),Ltxled.size())){//to check if you add the TextField
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("Please add the Led's and the Input !!");
                alert.show();
                return;
            }
            int x = Ltxled.size();
            ArrayList<Integer>ListOfRandom = new ArrayList<>();
            for (int i = 1; i <= x ; i++) {//to create list of random
                ListOfRandom.add(i);
            }
            Collections.shuffle(ListOfRandom);
            for (int i = 0; i < Ltxled.size(); i++) {//to print it to TextField led
                Ltxled.get(i).setText(ListOfRandom.get(i)+"");
            }
        });



        btRead.setOnAction(e ->{//to choose the file
            FileChooser fc = new FileChooser();
            File file = fc.showOpenDialog(stage2);// open stage file chooser
            if (file != null) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    int z =1;
                    ArrayList<Integer>x = new ArrayList<>();
                    ArrayList<Integer>y = new ArrayList<>();
                    while ((line = br.readLine()) != null) {//to Read the Line's
                        String[] data = line.split(",");

                        if (z == 1){
                            x.add(Integer.parseInt(data[0]));
                            z++;
                            continue;
                        }
                        if (z ==2){
                            for (int i = 0;i<data.length;i++){
                                y.add(Integer.parseInt(data[i]));
                            }
                            break;
                        }
                    }

                        int m = x.get(0);//the number of input
                        int n = y.size();//the number of led
                        if (m > n){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error !!");
                            alert.setContentText("The Number Of Led Must Be Grater than Input !!");
                            alert.show();
                            txled.clear();
                            txinp.clear();
                            return;
                        }
                        txled.setText(m+"");
                        txinp.setText(n+"");
                        btadd.fire();

                    for (int i = 0; i < n; i++) {//to put the file value in the led textfield
                        Ltxled.get(i).setText(y.get(i)+"");
                    }


                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Done!");
                    alert.setContentText("the item has been added!!");
                    alert.show();
                    br.close();
                } catch (IOException e1) {
                    e1.printStackTrace();

                }
                catch (Exception e1) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error!");
                    alert.setContentText("Please Select a valid file !!");
                    alert.show();
                }
            }
        });




        Scene scene = new Scene(pane,1280,620);
        stage.setScene(scene);
        stage.setTitle("Max LED Light!");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public static boolean ishasInt(String data){//to check if has Int
        try {
            Double d = Double.parseDouble(data);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }
    private static int[][] LED(int x[],int y[]){ //to return the DP table of Int
        int m = x.length ;
        int n = y.length;

        int[][] c = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++)
            c[i][0] = 0;

        for (int j = 1; j <= n; j++)
            c[0][j] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x[i-1] == y[j-1] ) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                } else {
                    if (c[i][j - 1] >= c[i - 1][j]) {
                        c[i][j] = c[i][j - 1];
                    } else {
                        c[i][j] = c[i - 1][j];
                    }
                }
            }
        }
        return c;
    }
    private static String[][] LEDOfDirc(int x[],int y[]){//to return the DP table Of Dirct's
        int m = x.length ;
        int n = y.length;

        int[][] c = new int[m + 1][n + 1];
        String[][] p = new String[m+1][n+1];
        for (int i = 1; i <= m; i++)
            c[i][0] = 0;

        for (int j = 1; j <= n; j++)
            c[0][j] = 0;
        ;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x[i-1] == y[j-1] ) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    p[i][j]= "T";
                } else {
                    if (c[i][j - 1] >= c[i - 1][j]) {
                        c[i][j] = c[i][j - 1];
                        p[i][j] = "L";
                    } else {
                        c[i][j] = c[i - 1][j];
                        p[i][j]="U";

                    }
                }
            }
        }
        for (int i = 0; i < p.length ; i++) {
            for (int j = 0; j <p[i].length ; j++) {
                if (p[i][j]==null){
                    p[i][j]="E";
                }
            }
        }
        return p;
    }

    private static ArrayList<Integer> result(int x[],int m,int n,String p[][]){//to return the result of expected LED's
        ArrayList<Integer> res = new ArrayList<>();
        int i = m;
        int j = n;
        while (true){
            if (i == 0 || j==0){
                break;
            }
            if (p[i][j].equals("T")){
                res.add(0,x[i-1]);
                i=i-1;
                j=j-1;
            }
            else if (p[i][j].equals("U")){
                i=i-1;
            }else {
                j=j-1;
            }
        }
        return res;
    }

    private static boolean CheckTextField(int x,int y){//to check if you add the TextField
        if (x == 0 || y==0)
            return false;
        return true;
    }

    private static boolean CheckTheField(ArrayList<TextField> x){ //this Method check of TextField's of LED's
        for (int i = 0;i<x.size();i++){
            if (x.get(i).getText().equals("")){//to check if empty
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("Please Fill the textField's");
                alert.show();
                return false;
            }
            if (!ishasInt(x.get(i).getText())){//to check if it has a correct value
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("Please put a correct value");
                alert.show();
                return false;
            }
            if (Integer.parseInt(x.get(i).getText())<0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("The Number Is Smaller than Zero !! !!");
                alert.show();
                return false;
            }
        }
        return true;
    }

    private static boolean Duplicat (ArrayList<TextField> x){//this method to check if it has a Duplicat number
        for (int i = 0; i < x.size(); i++) {
            for (int j = i+1; j < x.size(); j++) {
                if (x.get(i).getText().equals(x.get(j).getText())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error !!");
                    alert.setContentText("Please Dont repeat the number !!");
                    alert.show();
                    return false;
                }
            }
        }
        return true;
    }

}