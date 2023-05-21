package com.example.navigatorezd;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;


public class HelloApplication extends Application {
    public static Trie<Integer> trie = new Trie<>();
    public static HashMap<String,Vertice> g = new HashMap<>();
    private static int departureT=0;
    public static double djksAns=0;
    public static double arrivalTime=0;
    public static int speed=0;
    public static double traffic=0;
    public static int option;
    public static long djtime;
    public static long bftime;

    public static String path ="";
    @Override
    public void start(Stage stage) throws IOException {
        Text texts=new Text();
        texts.setX(28);
        texts.setY(60);
        texts.setFill(Color.PINK);

        Text text2=new Text();
        text2.setX(28);
        text2.setY(60);
        text2.setFill(Color.WHITE);
        Rectangle rec=new Rectangle(1000,1000, Color.PURPLE);

        Pane pane=new Pane();
        pane.getChildren().add(rec);
        Text text= new Text(20,20,"From");
        TextField fromtext=new TextField();
        fromtext.setLayoutX(20);
        fromtext.setLayoutY(25);
        fromtext.setText("Write a station");

        text.setFill(Color.WHITE);
        Font font1 =new Font(15);
        text.setFont(font1);
        text.autosize();
        Text suc=new Text("✔");
        suc.setFill(Color.GREEN);
        Font font=Font.font("Times New Roman",FontWeight.EXTRA_BOLD,14);
        suc.setFont(font);
        suc.setVisible(false);
        Text error=new Text("❌");
        error.setFill(Color.RED);
        error.setFont(font);
        error.setVisible(false);

        fromtext.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                ArrayList<String> list = new ArrayList<>();
                list.addAll((Collection<? extends String>)trie.keysWithPrefix(fromtext.getText()));
                if (trie.contains(fromtext.getText())) {
                    texts.setText("");
                    error.setVisible(false);
                    text2.setText("");
                    suc.setX(175);
                    suc.setY(42);
                    suc.setVisible(true);
                } else {
                    suc.setVisible(false);
                    error.setX(175);
                    error.setY(42);
                    texts.setText(retlist(list));
                    text2.setText(retvv(list.size(), fromtext.getText()));
                    error.setVisible(true);
                }
            }
        });


        Text to =new Text("To");
        to.setFont(font1);
        to.setLayoutX(200);
        to.setLayoutY(20);
        to.setFill(Color.WHITE);
        TextField tofield =new TextField();
        tofield.setLayoutX(200);
        tofield.setLayoutY(25);
        tofield.setText("Write a station");
        Text recto1 =new Text();
        recto1.setX(208);
        recto1.setY(60);
        recto1.setFill(Color.PINK);
        Text recto2=new Text();
        recto2.setX(208);
        recto2.setY(60);
        recto2.setFill(Color.WHITE);
        Pane risk=new Pane();
        Text succ =new Text("✔");
        succ.setFill(Color.GREEN);
        succ.setFont(font);
        succ.setVisible(false);
        Text eror =new Text("❌");
        eror.setFill(Color.RED);
        eror.setFont(font);
        eror.setVisible(false);
        risk.getChildren().addAll(suc,error,succ,eror);
        tofield.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                ArrayList<String> list = new ArrayList<>();
                list.addAll((Collection<? extends String>) trie.keysWithPrefix(tofield.getText()));
                if (trie.contains(tofield.getText())) {
                    recto1.setText("");
                    recto2.setText("");
                    eror.setVisible(false);
                    succ.setX(352);
                    succ.setY(42);
                    succ.setVisible(true);
                }else {
                    succ.setVisible(false);
                    eror.setX(352);
                    eror.setY(42);
                    eror.setVisible(true);
                    recto1.setText(retlist(list));
                    recto2.setText(retvv(list.size(), tofield.getText()));}
            }
        });


        Text time=new Text("Time");
        time.setFont(font1);
        time.setLayoutX(400);
        time.setLayoutY(20);
        time.setFill(Color.WHITE);
        TextField timefield=new TextField();
        timefield.setLayoutX(380);
        timefield.setLayoutY(25);
        timefield.setPrefWidth(30);

        TextField minutef =new TextField();
        minutef.setLayoutX(420);
        minutef.setLayoutY(25);
        minutef.setPrefWidth(30);

        Button auto =new Button("Auto Fill");
        auto.setLayoutX(385);
        auto.setLayoutY(55);
        ZonedDateTime autofi = ZonedDateTime.now();
        Text ani =new Text(":");
        ani.setFont(font1);
        ani.setFill(Color.WHITE);
        ani.setLayoutX(413);
        ani.setLayoutY(40);

        Timeline tochki=new Timeline(new KeyFrame(Duration.millis(700), new EventHandler<ActionEvent>() {
            boolean b =true;
            @Override
            public void handle(ActionEvent actionEvent) {
                if(b){
                    ani.setText(" ");
                    b=!b;
                }
                else{
                    ani.setText(":");
                    ani.setFont(font1);
                    ani.setFill(Color.WHITE);
                    b=!b;
                }
            }
        }));
        tochki.setCycleCount(Timeline.INDEFINITE);
        tochki.play();

        auto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                timefield.setText(String.valueOf(autofi.getHour()));
                minutef.setText(String.valueOf(autofi.getMinute()));
            }
        });

        RadioButton carbt=new RadioButton("Car");
        carbt.setLayoutX(470);
        carbt.setLayoutY(15);
        carbt.setTextFill(Color.WHITE);
        carbt.setFont(font1);


        RadioButton busbt =new RadioButton("Bus");
        busbt.setLayoutX(470);
        busbt.setLayoutY(40);
        busbt.setTextFill(Color.WHITE);
        busbt.setFont(font1);

        RadioButton footbt=new RadioButton("On foot");
        footbt.setLayoutX(470);
        footbt.setLayoutY(65);
        footbt.setTextFill(Color.WHITE);
        footbt.setFont(font1);

        EventHandler<ActionEvent> rab=new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(carbt.isSelected()){
                    option=1;

                } else if (busbt.isSelected()) {
                    option=2;

                } else if (footbt.isSelected()) {
                    option=3;
                }
            }
        };
        carbt.setOnAction(rab);
        busbt.setOnAction(rab);
        footbt.setOnAction(rab);
        ToggleGroup toggleGroup=new ToggleGroup();
        carbt.setToggleGroup(toggleGroup);
        busbt.setToggleGroup(toggleGroup);
        footbt.setToggleGroup(toggleGroup);

        Rectangle ansf=new Rectangle(560,280,Color.WHITE);
        ansf.setX(20);
        ansf.setY(100);
        ansf.setVisible(false);
        Button btn = new Button("GO");
        btn.setLayoutX(550);
        btn.setLayoutY(35);
        Label ans =new Label();
        ans.setLayoutX(25);
        ans.setLayoutY(105);
        ans.setMaxWidth(550);
        ans.setWrapText(true);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                departureT= Integer.parseInt(timefield.getText());
                if(departureT!=0&&option!=0&&!fromtext.getText().equals("Write a station")&&!tofield.getText().equals("Write a station")){
                    ansf.setVisible(true);
                    String realAnswer = "";
                    double f=bf(fromtext.getText(),tofield.getText());
                    djksAns=djks(fromtext.getText(),tofield.getText());
                    realAnswer+=path+"\n";
                    realAnswer+="\n"+setans(option,departureT,djksAns,g.get(tofield.getText()).westness-g.get(fromtext.getText()).westness);
                    realAnswer+="\n"+"Djkstra algorathims calculating time "+djtime+" ms";
                    realAnswer+="\n"+"Bellman-Ford algorathims calculating time "+bftime+" ms";
                    ans.setText(realAnswer);

                }

            }
        });
        pane.getChildren().addAll(text, fromtext, to,texts,text2 ,tofield,recto1,recto2, timefield,btn, time, minutef, auto, ani,ansf,ans,carbt,busbt,footbt, suc,error,succ,eror);

        Scene scene=new Scene(pane,600,400);
        stage.setScene(scene);
        stage.show();
    }

    public String retlist(ArrayList<String> list){
        String ans="";
        if(list.size()>=20){
            return null;}
        else {
            for (int i = 0; i < list.size(); i++) {
                ans+=list.get(i)+"\n";
            }}
        return ans;
    }
    public String retvv(int size,String pr){
        String ans="";
        if(size>=20){
            return null;}
        else {
            for (int i = 0; i < size; i++) {
                ans+=pr+"\n";
            }}
        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan ;
        try {
            scan = new Scanner(new File("C:\\Users\\Lenovo\\IdeaProjects\\NavigatorEZD\\src\\main\\resources\\DB.txt"));
        }catch (Exception e){
            scan =new Scanner(System.in);
        }
        int j=0;
        while (scan.hasNextLine()) {
            String row = scan.nextLine();
            String verticeName = row.substring(row.indexOf('.') + 1, row.indexOf('_')).trim().toLowerCase();
            Vertice v = g.computeIfAbsent(verticeName, s -> new Vertice()).setWestness(Integer.parseInt((row.contains("!")) ? row.substring(0, row.indexOf('!')) : row.substring(0, row.indexOf('.'))));
            String[] neighbourNames = row.substring(row.indexOf('_') + 1, row.indexOf(']')).split(",");
            String[] distances = row.substring(row.indexOf(']') + 1).split(",");
            for (int i = 0; i < neighbourNames.length; i++) {
                Vertice neighbour = g.computeIfAbsent(neighbourNames[i].trim().toLowerCase(), s -> new Vertice());
                neighbour.setWestness(v.westness + 1);
                v.neighbours.putIfAbsent(neighbourNames[i].trim().toLowerCase(), Double.valueOf(distances[i].trim()));
                neighbour.neighbours.putIfAbsent(verticeName, Double.valueOf(distances[i].trim()));
            }
            trie.put(verticeName,j);

            j++;
        }
        launch();
    }

    public static double bf(String from, String where){
        long t = System.currentTimeMillis();
        HashMap<String, Double> bl = new HashMap<>();
        for (String names : g.keySet()) {
            if (!names.equals(from)) bl.put(names, Double.MAX_VALUE);
            else bl.put(names, 0.);
        }
        Stack<String> currentOnes;
        HashSet<String> visited;
        for (int i = 0; i < g.size() - 1; i++) {
            currentOnes = new Stack<>();
            visited = new HashSet<>();
            currentOnes.add(from);
            visited.add(from);
            while (!currentOnes.isEmpty()) {
                String current = currentOnes.pop();
                Vertice v = g.get(current);
                double distanceToCurrent = bl.get(current);
                for (String neighbours : v.neighbours.keySet()) {
                    if (visited.add(neighbours))
                        currentOnes.add(neighbours);
                    if (bl.get(neighbours) > distanceToCurrent + v.neighbours.get(neighbours)) {
                        bl.put(neighbours, distanceToCurrent + v.neighbours.get(neighbours));
                    }
                }
            }
        }
        bftime=(System.currentTimeMillis()-t);
        return  bl.get(where);
    }
    public static double djks(String from , String where){
        long t = System.currentTimeMillis();
        LinkedList<Pack> d = new LinkedList<>();
        List<String> pp = new ArrayList<>();
        HashSet<String> visited;
        pp.add(from);
        d.add(new Pack(0, from, pp ));
        visited = new HashSet<>();
        visited.add(from);
        while (!d.get(0).name.equals(where)) {
            Vertice cur = g.get(d.get(0).name);
            for (String n : cur.neighbours.keySet()){
                List<String> pptemp = new ArrayList<>(d.get(0).path);
                pptemp.add(n);
                if (visited.add(n))
                    d.add(new Pack(cur.neighbours.get(n) + d.get(0).val, n,pptemp));
            }
            d.remove(0);
            Collections.sort(d);
        }
        djtime=(System.currentTimeMillis()-t);
        String ppp = "";
        for(String ans : d.get(0).path){
            ppp+=">>"+ans;
        }
        path=ppp.substring(2);
        return d.get(0).val ;
    }
    public static String setans(int option,int DepartureT,double djksAns,int westnes){
        double ArrivalTime = 0;
        double traffic = 0;
        int speed = 0;
        double pakop=1;

        if (option == 1) {
            traffic = pakop;
            speed = 65;
            ArrivalTime = getArrivalTime(DepartureT, djksAns, ArrivalTime , speed , traffic,westnes);
        } else if (option == 2) {
            traffic = 0.7;
            speed = 50;
            ArrivalTime = getArrivalTime(DepartureT, djksAns, ArrivalTime , speed , traffic,westnes);
        } else if (option == 3) {
            speed = 7;
            ArrivalTime = djksAns / speed;
        }
        int answer = (int) (ArrivalTime * 100);

        int hours = answer / 60;
        int minutes = answer % 60;
        String s="YOUR ARRIVAL TIME : " + hours + " hours " +minutes + " minutes";
        return s;
    }
    public static double getArrivalTime(int departureT, double djksAns, double arrivalTime , int speed , double traffic,int westnes) {
        if(westnes>=0){
            if (departureT >= 8 && departureT <= 10) {
                traffic = 1 * traffic;
                arrivalTime = (djksAns /speed) * traffic;
            } else if (departureT >= 10 && departureT <= 12) {
                traffic = 0.6 * traffic;
                arrivalTime = (djksAns / speed) * traffic;
            } else if (departureT >= 12 && departureT <= 14) {
                traffic = 0.8 * traffic;
                arrivalTime = (djksAns / speed) * traffic;
            } else if (departureT >= 14 && departureT <= 16) {
                traffic = 0.5 * traffic;
                arrivalTime = (djksAns / speed) * traffic;
            } else if (departureT >= 16 && departureT <= 21) {
                traffic = 1 * traffic;
                arrivalTime = (djksAns /speed) * traffic;
            } else if (departureT >= 21 && departureT <= 24) {
                traffic = 0.35 * traffic;
                arrivalTime = (djksAns / speed) * traffic;
            } else if (departureT >= 0 && departureT <= 10) {
                traffic = 0.29 * traffic;
                arrivalTime = (djksAns / speed) * traffic;
            }
        }else{
            if (departureT >= 8 && departureT <= 10) {
                traffic = 0.29 * traffic;
                arrivalTime = (djksAns / speed) * traffic;
            } else if (departureT >= 10 && departureT <= 12) {
                traffic = 0.35 * traffic;
                arrivalTime = (djksAns / speed) * traffic;
            } else if (departureT >= 12 && departureT <= 14) {
                traffic = 1 * traffic;
                arrivalTime = (djksAns /speed) * traffic;
            } else if (departureT >= 14 && departureT <= 16) {
                traffic = 0.5 * traffic;
                arrivalTime = (djksAns / speed) * traffic;
            } else if (departureT >= 16 && departureT <= 21) {
                traffic = 0.8 * traffic;
                arrivalTime = (djksAns / speed) * traffic;
            } else if (departureT >= 21 && departureT <= 24) {
                traffic = 0.6 * traffic;
                arrivalTime = (djksAns / speed) * traffic;
            } else if (departureT >= 0 && departureT <= 10) {
                traffic = 1 * traffic;
                arrivalTime = (djksAns /speed) * traffic;
            }
        }

        return arrivalTime;
    }
}
