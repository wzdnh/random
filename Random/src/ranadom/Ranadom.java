package ranadom;

import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Ranadom extends Application{
    
    public int max;
    public int min;
    
    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setPadding(new Insets(50, 50, 50, 50));
        pane.setHgap(20);
        pane.setVgap(20);
        pane.add(new Label("生成的随机数范围："), 0, 0);
        pane.add(new Label("生成的随机数个数："), 0, 2);
        pane.add(new Label("生成的随机数对数："), 0, 4);
        
        Button btA = new Button();
        Button btB = new Button();
        Button btC = new Button();

        btA.setText("输出随机对数：");//随机数对数输出框
        btA.setMinWidth(125);
        btB.setText("输出随机数：");//个数输出框
        btB.setMinWidth(125);
        btC.setText("随机排序：");
        btC.setMinWidth(125);
        
        pane.add(btA,0,5);
        pane.add(btB,0,3);
        pane.add(btC,0,1);
        
        TextField textFieldA = new TextField();
        TextField textFieldB = new TextField();
        TextField textFieldC = new TextField();
        TextField textFieldE = new TextField();//随机数的个数输入框
        
        Label labResult = new Label();//结果框
        labResult.setWrapText(true);
        labResult.setTextFill(Color.web("#0076a3"));
        labResult.setFont(new Font("Arial", 30));
        
        HBox hBoxResule = new HBox();  
        hBoxResule.setMaxSize(600, 700);
        hBoxResule.getChildren().add(labResult);
    
        HBox box = new HBox();
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(5, 0, 5, 0));
        box.setSpacing(185);//
        box.getChildren().addAll(textFieldA , textFieldB);
        box.setMinWidth(600);
            
        pane.add(textFieldE,1, 2);//随机数个数输入框
        HBox hBoxF = new HBox();
                
        pane.add(textFieldC,1,4);
        pane.add(hBoxResule,1, 6);
        pane.add(box, 1, 0 );
        
        pane.setStyle("-fx-background-image:url('/background.jpg')");
        Scene scene = new Scene(pane,1050,710);//
        primaryStage.setTitle("随机数小程序!");
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
        //随机数对数操作代码
        btA.setOnAction((ActionEvent e) -> { 
            textFieldE.setText(null);//将他清空，避免出现歧义
            
            int min = Integer.parseInt(textFieldA.getText());
            int max = Integer.parseInt(textFieldB.getText());
            int a = min + (int)(Math.random() * (max - min + 1));
            String result = String.valueOf(a);
            
            ArrayList arrayListA = new ArrayList();
            ArrayList arrayListC = new ArrayList();
            
                
            int number = Integer.parseInt(textFieldC.getText());
            int l = max - min + 1;
            int k = number * 2;

            //带检查逻辑错误的算法
            if(l < k){
                labResult.setText("请重新输入范围或随机数对数！");
            }
            else{

                while(min <= max){
                    arrayListC.add(min);
                    min = min + 1;
                }

                Collections.shuffle(arrayListC);//随机打乱arrayListC

                //对随机数进行格式处理，以及生成不重复的成对的随机数
                if(number == 0){
                    labResult.setText(result);
                }
                else{
                    //number//随机数的对数 
                    int i = 0;
                    int j = 0;
                    int numRanadom = (number * 3);

                    while (i < numRanadom){

                        if (j == 2){
                            arrayListA.add("   ");
                        }
                        else{

                            String secend = String.valueOf(arrayListC.get(0));
                            arrayListA.add(secend);
                            if (arrayListC.size() > 0){
                                arrayListC.remove(0);
                            }
                        }
                        
                        j = j + 1;
                        if (j == 3){
                            j = 0;
                        }

                        i++;
                    } 

                    labResult.setText(arrayListA.toString().trim().replaceAll("\\[|\\]|\\,", ""));

                }
            }
          
        });
        
        //随机数个数操作代码
        btB.setOnAction((ActionEvent e) -> {          
            ArrayList arrayListB = new ArrayList();
            int min = Integer.parseInt(textFieldA.getText());
            int max = Integer.parseInt(textFieldB.getText());
            int d = Integer.parseInt(textFieldE.getText());
            
            int f = 0;
            while (f < d){
                int g = min + (int)(Math.random() * (max - min + 1));
                arrayListB.add(" " + g + " ");
                f++;
            }         
            
            labResult.setText(arrayListB.toString().trim().replaceAll("\\[|\\]|\\,", ""));
        });
        
        btC.setOnAction((ActionEvent e) -> { 
            
            ArrayList arrayListD = new ArrayList();
            ArrayList arrayListE = new ArrayList();
            
            int min = Integer.parseInt(textFieldA.getText());
            int max = Integer.parseInt(textFieldB.getText());
            while(min <= max){
                arrayListD.add(min);
                min = min + 1;
            }

            Collections.shuffle(arrayListD);//随机打乱arrayListD
            
            String spaceBack = "            ";
            while(!arrayListD.isEmpty()){
                int z = (int)arrayListD.get(0);
                arrayListD.remove(0);
                arrayListE.add(z);
                arrayListE.add(spaceBack);
                
            }
            labResult.setText(arrayListE.toString().trim().replaceAll("\\[|\\]|\\,", ""));
               
        });   
    }
  
    public static void main(String[] args) {
        launch(args);
    }
}