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

        //create TextArea DP table
        VBox varea = new VBox(10);
        TextArea AreaDp = new TextArea();
        varea.getChildren().add(AreaDp);
        AreaDp.setMaxSize(400,200);
        varea.setLayoutX(850);
        varea.setLayoutY(420);
        pane.getChildren().add(varea);
       // pane.add(varea,80,54);
        Label lbarea = new Label("            The DP Table");
        lbarea.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        lbarea.setStyle("-fx-text-fill: white;");
        lbarea.setLayoutX(930);
        lbarea.setLayoutY(395);
        pane.getChildren().add(lbarea);
       // pane.add(lbarea,80,53);

        //create Label of Result
        Label lbExpected = new Label("The Expected Result" +
                "\n ");

        Label lbLed = new Label("The Expected LED's ");
        TextArea txExpected = new TextArea();
        TextArea txLed = new TextArea();
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

        //Button of add TextBox on LED and Input
        ArrayList<TextField> Ltxled = new ArrayList<>();
        ArrayList<TextField> Ltxinp = new ArrayList<>();
        VBox vtxinp = new VBox(10);

        Pane paneLed = new Pane();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(50);
        scrollPane.setLayoutY(150);
        scrollPane.setPrefSize(350,400);
        scrollPane.setContent(paneLed);
//        scrollPane.setBackground(new Background(backgroundImage));
//        scrollPane.setStyle("-fx-background-color: #20588d;");
        Image gif = new Image("giphy.gif");
        ImageView imageView = new ImageView(gif);
        imageView.setLayoutX(-58);
        imageView.setLayoutY(20);
        paneLed.getChildren().add(imageView);
        pane.getChildren().add(scrollPane);

        btadd.setOnAction(e ->{
            paneLed.getChildren().clear();
            if (!ishasInt(txled.getText())||!ishasInt(txinp.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("TELLL !!");
                alert.show();
                txled.clear();
                txinp.clear();
                return;
            }

            int z = Integer.parseInt(txinp.getText());
            int x = Integer.parseInt(txled.getText());
            if (x==0&&z==0){
                paneLed.getChildren().add(imageView);
            }
            if (x<z){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("The Number Of Led Must Be Grater than Input !!");
                alert.show();
                txled.clear();
                txinp.clear();
                return;

            }
            Ltxled.clear();
            vtxled.getChildren().clear();
            for (int i = 0 ; i < x ; i++){
                TextField ti = new TextField();
                ti.setPrefSize(35,35);
                ti.setMaxWidth(35);
                ti.setMaxHeight(35);
                Ltxled.add(ti);

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
            for (int i = 0 ; i < x ; i++){
                Ltxled.get(i).setLayoutX(startX_LED);
                Ltxled.get(i).setLayoutY(startY_LED);

                paneLed.getChildren().add(Ltxled.get(i));
                startY_LED += 50;
                //vtxled.getChildren().add(Ltxled.get(i));
            }

            Ltxinp.clear();
            vtxinp.getChildren().clear();
            for (int i = 0 ; i < z ; i++){
                TextField ti = new TextField();
                ti.setPrefSize(35,35);
                ti.setMaxWidth(35);
                ti.setMaxHeight(35);
                Ltxinp.add(ti);

            }

            double startX_INPUT = 210, startY_INPUT = 10;
            for (int i = 0 ; i < z ; i++){


                Ltxinp.get(i).setLayoutX(startX_INPUT);
                Ltxinp.get(i).setLayoutY(startY_INPUT);

                paneLed.getChildren().add(Ltxinp.get(i));
                startY_INPUT += 50;
                //vtxinp.getChildren().add(Ltxinp.get(i));
            }
            for (int i = 0 ; i < Ltxinp.size() ; i++){
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


            int x[] = new int[Ltxinp.size()];
            int y[] = new int [Ltxled.size()];

            for (int i = 0 ; i<Ltxinp.size();i++){

                x[i]=Integer.parseInt(Ltxinp.get(i).getText());
            }
            for (int i = 0 ; i<Ltxled.size();i++){
                y[i]=Integer.parseInt(Ltxled.get(i).getText());
            }

            int c[][] = LED(x,y);
            String p[][] = LEDOfDirc(x,y);
//            String result[] = result(x,x.length,y.length,p).toString().split("");//to convert the result to String from method
              ArrayList<Integer> r = r(x, x.length,y.length,p);
//            for (int i = 0;i<result.length;i++){//to convert the string result to array of int
//                if (result[i].equals(",")&& i == result.length-1){
//                    break;
//                }
//                if (result[i].equals(",")){
//                    r.add(Integer.parseInt(result[i+1]));
//                    i++;
//                    continue;
//                }
//                r.add(Integer.parseInt(result[i]));
//            }

            txExpected.setText(r.size()+"");//this to put the expected result into a textarea
            String led = " ";
            for (int i = 0 ; i<r.size();i++){//this for loop to print the expected Led's
                led+=r.get(i)+" | ";
            }
            txLed.setText(led);
            AreaDp.clear();


            AreaDp.appendText("        ");
            for (int i =0;i<y.length;i++){
                AreaDp.appendText(y[i]+" ");
            }
            AreaDp.appendText("\n");
            for (int i =0;i<c.length;i++){ //to print the DP Table
                AreaDp.appendText(i+"   ");
                for (int j = 0;j<c[i].length;j++){
                    AreaDp.appendText(c[i][j]+" ");
                }
                AreaDp.appendText("\n");
            }
            AreaDp.appendText("\n");
            AreaDp.appendText("__________________________________");
            AreaDp.appendText("\n\n");
            for (int i =0;i<c.length;i++){//to print the DP Table
                AreaDp.appendText(i+"   ");
                for (int j = 0;j<c[i].length;j++){
                    AreaDp.appendText("|"+p[i][j]+"|"+" ");
                }
                AreaDp.appendText("\n");
            }
            /*
                ArrayList<TextField> Ltxled = new ArrayList<>();
                ArrayList<TextField> Ltxinp = new ArrayList<>();
             */

            for (int i = 0; i < Ltxinp.size(); i++) {//to start put Lines in the textFild's
                for (int j = 0; j < Ltxled.size(); j++) {
                    for (int u = 0; u < r.size(); u++) {
                        if ((r.get(u) + "").equals(Ltxled.get(j).getText())) {
                            if (Ltxinp.get(i).getText().equals(Ltxled.get(j).getText())) {
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
        });

        btRead.setOnAction(e ->{
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

                            for (int i = 0;i<data.length;i++){

                                x.add(Integer.parseInt(data[i]));
                            }
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

                        int m = x.size();
                        int n = y.size();
                        if (m > n){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error !!");
                            alert.setContentText("The Number Of Led Must Be Grater than Input !!");
                            alert.show();
                            txled.clear();
                            txinp.clear();
                        }
                        txled.setText(m+"");
                        txinp.setText(n+"");
                        btadd.fire();

                    for (int i = 0; i < m; i++) {
                        Ltxinp.get(i).setText(x.get(i)+"");
                    }for (int i = 0; i < n; i++) {
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
        ;

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

    private static ArrayList<Integer> r(int x[],int m,int n,String p[][]){//to return the result of expected LED's
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